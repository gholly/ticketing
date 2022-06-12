package ticketing.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.tw.travel.ticketing.controller.TicketingController;
import com.tw.travel.ticketing.controller.dto.CreateTicketingOrderRequest;
import com.tw.travel.ticketing.service.TicketingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class TicketingOrderControllerTest {

    @Mock
    private TicketingService ticketingService;

    @InjectMocks
    private TicketingController ticketingController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ticketingController).build();
    }

    @Test
    void should_create_order_success() throws Exception {
        CreateTicketingOrderRequest createOrderDTO = CreateTicketingOrderRequest.builder()
                                                                   .code("A01")
                                                                    .quantity(1).build();

        when(ticketingService.createOrder(any())).thenReturn("O01");
        mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createOrderDTO))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void should_cancel_order_success() throws Exception {
        mockMvc.perform(
                        post("/orders/O01/cancellation"))
                .andExpect(status().isOk());
    }
}