package ticketing.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rabbitmq.http.client.HttpException;
import java.time.LocalDateTime;
import java.util.Optional;
import com.tw.travel.ticketing.exception.FlightHasDepartedException;
import com.tw.travel.ticketing.exception.FlightNoSeatException;
import com.tw.travel.ticketing.infrastructure.feignclient.FlightClient;
import com.tw.travel.ticketing.infrastructure.feignclient.FlightResponse;
import com.tw.travel.ticketing.infrastructure.feignclient.FlightStatus;
import com.tw.travel.ticketing.infrastructure.repository.TicketingOrderRepository;
import com.tw.travel.ticketing.infrastructure.repository.entity.TicketingOrder;
import com.tw.travel.ticketing.infrastructure.repository.entity.TicketingOrderStatus;
import com.tw.travel.ticketing.service.TicketingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TicketingOrderServiceTest {

    @Mock
    private FlightClient flightClient;

    @Mock
    private TicketingOrderRepository ticketingOrderRepository;


    @InjectMocks
    private TicketingService ticketingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_create_order_success() {
        when(flightClient.findByFlightNumber(any())).thenReturn(
                FlightResponse.builder().code("Flight001")
                              .status(FlightStatus.READY_FOR_DEPART).build());
        when(ticketingOrderRepository.save(any())).thenReturn(TicketingOrder.builder()
                                                                            .flightNumber("Flight001")
                                                                            .orderNo("ORDER001")
                                                                            .flightDepartTime(LocalDateTime.now().plusDays(2))
                                                                            .build());

        TicketingOrder ticketingOrder = TicketingOrder.builder()
                                                      .flightNumber("Flight001")
                                                      .orderNo("ORDER001")
                                                      .flightDepartTime(LocalDateTime.now().plusDays(2))
                                                      .build();
        String orderNumber = ticketingService.createOrder(ticketingOrder);
        assertNotNull(orderNumber);
    }


    @Test
    void should_create_order_failed_when_flight_have_no_seat_to_sold_out() {
        when(flightClient.findByFlightNumber(any())).thenReturn(
                FlightResponse.builder().code("001").status(FlightStatus.SEAT_HAS_SOLD_OUT).build());

        TicketingOrder ticketingOrder = TicketingOrder.builder()
                                 .flightNumber("001").build();

        assertThrows(FlightNoSeatException.class, () -> ticketingService.createOrder(ticketingOrder));
    }

    @Test
    void should_create_order_failed_when_flight_server_is_not_available() {
        when(flightClient.findByFlightNumber(any())).thenThrow(
                new HttpException("503 food server not available"));

        TicketingOrder ticketingOrder = TicketingOrder.builder()
                                                      .flightNumber("001").build();

        assertThrows(HttpException.class, () -> ticketingService.createOrder(ticketingOrder));
    }

    @Test
    void should_cancel_order_success() {
        String orderNo = "O01";
        TicketingOrder ticketingOrder = TicketingOrder.builder()
                                                      .flightNumber("001")
                                                      .flightDepartTime(LocalDateTime.now().plusDays(2))
                                                      .status(TicketingOrderStatus.CREATED)
                                                      .build();

        when(ticketingOrderRepository.findByOrderNumber(orderNo)).thenReturn(Optional.of(ticketingOrder));
        when(ticketingOrderRepository.save(any())).thenReturn(ticketingOrder);
        ticketingService.cancelOrder(orderNo);

        verify(ticketingOrderRepository, times(1)).save(any());
    }

    @Test
    void should_cancel_order_failed_when_order_have_departed() {
        String orderNo = "O01";
        TicketingOrder ticketingOrder = TicketingOrder.builder()
                                                      .flightNumber("001")
                                                      .flightDepartTime(LocalDateTime.now().minusDays(2))
                                                      .status(TicketingOrderStatus.CREATED)
                                                      .build();
        when(ticketingOrderRepository.findByOrderNumber(orderNo)).thenReturn(Optional.of(ticketingOrder));

        assertThrows(FlightHasDepartedException.class, () -> ticketingService.cancelOrder(orderNo));
    }
}