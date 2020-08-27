package lozm.bulkInsertTestData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lozm.object.dto.user.PostUserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("local")
public class AccountBulkInsert {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void setUser() {
        try {
            Faker faker = new Faker();
            for(int i=0; i<100; i++) {
                PostUserDto.Request reqDto = PostUserDto.Request.setRequestTestData(faker.name().fullName(), faker.pokemon().name());
                postUser(reqDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postUser(PostUserDto.Request reqDto) throws Exception {
        ResultActions result = mockMvc.perform(
                post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqDto))
        );

        result.andExpect(status().is(200));
        System.out.println("result = " + result);
    }

}
