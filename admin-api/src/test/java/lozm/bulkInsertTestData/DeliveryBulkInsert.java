package lozm.bulkInsertTestData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lozm.object.code.DeliveryStatus;
import lozm.object.dto.delivery.PostDeliveryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class DeliveryBulkInsert {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void setDelivery() {
        try {
            Faker faker = new Faker();
            List<String> deliveryList = new ArrayList<>();
            deliveryList.add(DeliveryStatus.PREPARATION.name());
            deliveryList.add(DeliveryStatus.PROGRESS.name());
            deliveryList.add(DeliveryStatus.COMPLETE.name());

            for(int i=0; i<100; i++) {
                PostDeliveryDto.Request reqDto = PostDeliveryDto.Request.setRequestTestData(
                    faker.company().industry(),
                    String.valueOf(ThreadLocalRandom.current().nextInt(100000,999999)),
                    faker.company().name(),
                    faker.team().name(),
                    faker.lorem().word(),
                    deliveryList.get(ThreadLocalRandom.current().nextInt(0,2))
                );
                postDelivery(reqDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postDelivery(PostDeliveryDto.Request reqDto) throws Exception {
        ResultActions result = mockMvc.perform(
                post("/api/delivery")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqDto))
        );

        result.andExpect(status().is(200));
        System.out.println("result = " + result);
    }


}
