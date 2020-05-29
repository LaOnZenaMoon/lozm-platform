package lozm.bulkInsertTestData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lozm.dto.store.PostStoreDto;
import lozm.dto.user.PostUserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class StoreBulkInsert {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void setStore() {
        try {
            Faker faker = new Faker();
            for(int i=0; i<100; i++) {
                String telNumber = String.valueOf(ThreadLocalRandom.current().nextInt(10000000, 99999999));
                PostStoreDto.Request reqDto = PostStoreDto.Request.setRequestTestData(faker.company().name(), telNumber, faker.company().catchPhrase());
                postStore(reqDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postStore(PostStoreDto.Request reqDto) throws Exception {
        ResultActions result = mockMvc.perform(
                post("/api/store")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqDto))
        );

        result.andExpect(status().is(200));
        System.out.println("result = " + result);
    }


}
