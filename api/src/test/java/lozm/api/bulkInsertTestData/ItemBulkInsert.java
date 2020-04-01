package lozm.api.bulkInsertTestData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import lozm.core.code.ItemType;
import lozm.core.dto.item.PostItemDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ItemBulkInsert {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void setItems() {
        setItem(ItemType.OUTER.name());
        setItem(ItemType.TOP.name());
        setItem(ItemType.BOTTOM.name());
        setItem(ItemType.SHOES.name());
    }

    public void setItem(String itemType) {
        try {
            Faker faker = new Faker();

            for(int i=0; i<100; i++) {
                PostItemDto.Request reqDto = PostItemDto.Request.setRequestTestData(
                    faker.university().name(),
                    ThreadLocalRandom.current().nextLong(10, 999) * 1000,
                    ThreadLocalRandom.current().nextLong(1, 200),
                    itemType,
                    faker.lorem().word(),
                "L"
                );
                postItem(reqDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postItem(PostItemDto.Request reqDto) throws Exception {
        ResultActions result = mockMvc.perform(
                post("/api/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqDto))
        );

        result.andExpect(status().is(200));
        System.out.println("result = " + result);
    }


}
