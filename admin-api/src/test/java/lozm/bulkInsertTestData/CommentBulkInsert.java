package lozm.bulkInsertTestData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lozm.api.board.BoardService;
import lozm.api.user.UserService;
import lozm.object.code.ContentType;
import lozm.object.dto.board.GetBoardDto;
import lozm.object.dto.board.PostCommentDto;
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
public class CommentBulkInsert {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;


    @Test
    public void setComment() throws Exception {
        String[] boardTypeArr = {"NEWS", "DIARY", "FREE CONTENTS", "BASKETBALL", "SOCCER"};

        List<GetUserDto> userList = userService.getUserList();
        List<GetBoardDto> newsList = boardService.getBoardList(boardTypeArr[0]);
        List<GetBoardDto> diaryList = boardService.getBoardList(boardTypeArr[1]);
        List<GetBoardDto> freeContentsList = boardService.getBoardList(boardTypeArr[2]);
        List<GetBoardDto> basketballList = boardService.getBoardList(boardTypeArr[3]);
        List<GetBoardDto> soccerList = boardService.getBoardList(boardTypeArr[4]);

        try {
            for (int i = 0; i <newsList.size() ; i++) {
                GetUserDto getUser = userList.get(ThreadLocalRandom.current().nextInt(0, userList.size()));
                postComment(getUser, newsList.get(i));
            }

            for (int i = 0; i <diaryList.size() ; i++) {
                GetUserDto getUser = userList.get(ThreadLocalRandom.current().nextInt(0, userList.size()));
                postComment(getUser, diaryList.get(i));
            }

            for (int i = 0; i <freeContentsList.size() ; i++) {
                GetUserDto getUser = userList.get(ThreadLocalRandom.current().nextInt(0, userList.size()));
                postComment(getUser, freeContentsList.get(i));
            }

            for (int i = 0; i <basketballList.size() ; i++) {
                GetUserDto getUser = userList.get(ThreadLocalRandom.current().nextInt(0, userList.size()));
                postComment(getUser, basketballList.get(i));
            }

            for (int i = 0; i <soccerList.size() ; i++) {
                GetUserDto getUser = userList.get(ThreadLocalRandom.current().nextInt(0, userList.size()));
                postComment(getUser, soccerList.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postComment(GetUserDto getUser, GetBoardDto getBoardDto) throws Exception {
        Faker faker = new Faker();
        String[] contentTypeArr = {ContentType.GENERAL.name(), ContentType.NOTICE.name(), ContentType.EVENT.name()};
        String contentType = contentTypeArr[ThreadLocalRandom.current().nextInt(0, 2)];
        PostCommentDto.Request reqDto = PostCommentDto.Request.setRequestTestData(
                getBoardDto.getId(),
                contentType,
                faker.lorem().sentences(ThreadLocalRandom.current().nextInt(0, 2)).toString(),
                getUser.getId()
        );

        ResultActions result = mockMvc.perform(
                post("/api/board/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reqDto))
        );
    }

}
