package lozm.bulkInsertTestData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lozm.api.user.UserService;
import lozm.object.code.ContentType;
import lozm.object.dto.board.PostBoardDto;
import lozm.object.dto.user.GetUserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class BoardBulkInsert {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;


    @Test
    public void setBoard() throws Exception {
        List<GetUserDto> userList = userService.getUserList();

        try {
            for (int i = 0; i <100 ; i++) {
                GetUserDto getUser = userList.get(ThreadLocalRandom.current().nextInt(0, userList.size()));
                postBoard(getUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postBoard(GetUserDto getUser) throws Exception {
        Faker faker = new Faker();
        String[] boardTypeArr = {"NEWS", "DIARY", "FREE CONTENTS", "BASKETBALL", "SOCCER"};
        String[] contentTypeArr = {ContentType.GENERAL.name(), ContentType.NOTICE.name(), ContentType.EVENT.name()};

        String boardType = boardTypeArr[ThreadLocalRandom.current().nextInt(0, 4)];
        String contentType = contentTypeArr[ThreadLocalRandom.current().nextInt(0, 2)];
        PostBoardDto.Request reqDto = PostBoardDto.Request.setRequestTestData(
                boardType,
                contentType,
                faker.book().title(),
                faker.lorem().sentences(ThreadLocalRandom.current().nextInt(0, 10)).toString(),
                getUser.getId()
        );

        ResultActions result = mockMvc.perform(
                post("/api/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reqDto))
        );
    }


}
