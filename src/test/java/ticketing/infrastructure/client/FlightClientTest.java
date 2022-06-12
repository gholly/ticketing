package ticketing.infrastructure.client;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tw.travel.ticketing.TicketingApplication;
import com.tw.travel.ticketing.infrastructure.feignclient.FlightClient;
import com.tw.travel.ticketing.infrastructure.feignclient.FlightResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@AutoConfigureWireMock(stubs = "classpath:/stubs", port = 8082)
@SpringBootTest(classes = TicketingApplication.class)
class FlightClientTest {

    @Autowired
    private FlightClient flightClient;

    @Test
    void should_find_flight_by_flight_number_success() {
        FlightResponse flightResponse = flightClient.findByFlightNumber("Flight001");
        assertNotNull(flightResponse);
    }
}