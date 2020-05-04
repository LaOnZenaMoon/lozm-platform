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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor
public class OrdersBulkInsert {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final ItemService itemService;
    private final CouponService couponService;


    @Test
    public void setOrders() throws Exception {
        //Get user
        List<GetUserDto> userList = userService.getUserList();


        //Get item
        List<GetItemDto> itemList = itemService.getItemList();

        //Get coupon
        List<GetCouponDto> couponList = couponService.getCouponList();

        int itemIndex = 0;
        int couponIndex = 0;
        //Set orders
        for (GetUserDto getUserDto : userList) {
            try {
                Long itemId = itemList.get(itemIndex).getId();
                Long couponId = couponList.get(couponIndex).getId();

                PostOrdersDto.Request reqDto = PostOrdersDto.Request.setRequestTestData();

                ResultActions result = mockMvc.perform(
                        post("/api/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reqDto))
                );

                result.andExpect(status().is(200));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
