package ticketing.infrastructure.client;


import com.tw.travel.ticketing.TicketingApplication;
import com.tw.travel.ticketing.infrastructure.feignclient.PaymentClient;
import com.tw.travel.ticketing.infrastructure.feignclient.PaymentResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@AutoConfigureWireMock(stubs = "classpath:/stubs", port = 8083)
@SpringBootTest(classes = TicketingApplication.class)
public class PaymentClientTest {

    @Autowired
    private PaymentClient paymentClient;

    @Test
    void should_find_flight_by_flight_number_success() {
        PaymentResponse paymentResponse = paymentClient.payment("Flight001");
        assertNotNull(paymentResponse);
    }
}
