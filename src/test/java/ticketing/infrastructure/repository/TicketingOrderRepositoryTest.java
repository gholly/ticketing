package ticketing.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.time.LocalDateTime;

import com.tw.travel.ticketing.TicketingApplication;
import com.tw.travel.ticketing.infrastructure.repository.TicketingOrderRepository;
import com.tw.travel.ticketing.infrastructure.repository.entity.TicketingOrder;
import com.tw.travel.ticketing.infrastructure.repository.entity.TicketingOrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TicketingApplication.class)
class TicketingOrderRepositoryTest {

    @Autowired
    private TicketingOrderRepository ticketingOrderRepository;

    @Test
    void should_save_order_success() {
        TicketingOrder savedOrder = ticketingOrderRepository.save(
                TicketingOrder.builder()
                              .flightNumber("001")
                              .flightDepartTime(LocalDateTime.now().minusDays(2))
                              .status(TicketingOrderStatus.CREATED)
                              .build());

        assertNotNull(savedOrder.getId());
    }
}