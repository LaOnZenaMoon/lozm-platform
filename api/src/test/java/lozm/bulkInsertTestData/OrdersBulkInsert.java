package lozm.bulkInsertTestData;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lozm.coupon.CouponService;
import lozm.dto.coupon.GetCouponDto;
import lozm.dto.item.GetItemDto;
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
        List<GetCouponDto> result = couponService.getCouponList();

        //Set orders
    }

}
