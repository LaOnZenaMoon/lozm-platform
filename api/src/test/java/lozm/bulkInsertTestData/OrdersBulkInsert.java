package lozm.bulkInsertTestData;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lozm.coupon.CouponService;
import lozm.delivery.DeliveryService;
import lozm.dto.coupon.GetCouponDto;
import lozm.dto.delivery.GetDeliveryDto;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
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
    @Autowired
    private DeliveryService deliveryService;


    @Test
    public void setOrders() throws Exception {
        try {
            //Get user
            List<GetUserDto> userList = userService.getUserList();
            List<Long> userIdList = new ArrayList<>();
            for (GetUserDto userDto : userList) {
                userIdList.add(userDto.getId());
            }

            //Get item
            List<GetItemDto> itemList = itemService.getItemList();

            //Get coupon
            List<GetCouponDto> couponList = couponService.getCouponList();
            List<Long> couponIdList = new ArrayList<>();
            for (GetCouponDto getCouponDto : couponList) {
                couponIdList.add(getCouponDto.getId());
            }

            //Get delivery
            List<GetDeliveryDto> deliveryList = deliveryService.getDeliveryList();
            List<Long> deliveryIdList = new ArrayList<>();
            for (GetDeliveryDto getDeliveryDto : deliveryList) {
                deliveryIdList.add(getDeliveryDto.getId());
            }

            int errorCount = 0;
            //Set orders
            for (GetItemDto itemDto : itemList) {
                try {
                    Long userId = ThreadLocalRandom.current().nextLong(Collections.min(userIdList), Collections.max(userIdList));
                    Long couponId = ThreadLocalRandom.current().nextLong(Collections.min(couponIdList), Collections.max(couponIdList));
                    Long deliveryId = ThreadLocalRandom.current().nextLong(Collections.min(deliveryIdList), Collections.max(deliveryIdList));
                    Long quantity = ThreadLocalRandom.current().nextLong(1, 5);

                    PostOrdersDto.Request reqDto = PostOrdersDto.Request.setRequestTestData(userId, itemDto.getId(), deliveryId, couponId, quantity);

                    ResultActions result = mockMvc.perform(
                            post("/api/orders")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(reqDto))
                    );

                    result.andExpect(status().is(200));
                } catch (Exception e) {
                    errorCount++;
                    log.debug(e.getMessage());
                }
            }

            System.out.println("errorCount = " + errorCount);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
