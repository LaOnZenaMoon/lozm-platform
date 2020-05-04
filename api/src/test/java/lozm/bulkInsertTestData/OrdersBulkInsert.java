package lozm.bulkInsertTestData;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lozm.coupon.CouponService;
import lozm.dto.coupon.GetCouponDto;
import lozm.dto.item.GetItemDto;
import lozm.dto.orders.PostOrdersDto;
import lozm.dto.user.GetUserDto;
import lozm.item.ItemService;
import lozm.user.UserService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class OrdersBulkInsert {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CouponService couponService;


    @Test
    public void setOrders() throws Exception {
        try {
            //Get user
            List<GetUserDto> userList = userService.getUserList();

            //Get item
            List<GetItemDto> itemList = itemService.getItemList();

            //Get coupon
            List<GetCouponDto> couponList = couponService.getCouponList();

            int itemIndex = 0;
            int couponIndex = 0;
            int errorCount = 0;
            //Set orders
            for (GetUserDto userDto : userList) {
                try {
                    Long itemId = itemList.get(itemIndex).getId();
                    Long couponId = couponList.get(couponIndex).getId();
                    Long quantity = ThreadLocalRandom.current().nextLong(1, 5);

                    PostOrdersDto.Request reqDto = PostOrdersDto.Request.setRequestTestData(userDto.getId(), itemId, couponId, quantity);

                    ResultActions result = mockMvc.perform(
                            post("/api/orders")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(reqDto))
                    );

                    result.andExpect(status().is(200));
                } catch (Exception e) {
                    errorCount++;

                    e.printStackTrace();
                }
            }

            System.out.println("errorCount = " + errorCount);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
