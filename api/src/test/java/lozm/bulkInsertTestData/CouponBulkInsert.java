package lozm.bulkInsertTestData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lozm.code.CouponType;
import lozm.dto.coupon.PostCouponDto;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static lozm.util.DateUtility.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CouponBulkInsert {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void setCoupons() {
        setCoupon(CouponType.PRICE.name());
        setCoupon(CouponType.RATIO.name());
    }

    public void setCoupon(String itemType) {
        try {
            Faker faker = new Faker();
            for(int i=0; i<100; i++) {
                LocalDateTime startDt = getStartDt(null);
                LocalDateTime endDt = getEndDt(startDt);

                Long amount = 0L;
                if(CouponType.RATIO.name().equals(itemType)) {
                    amount = ThreadLocalRandom.current().nextLong(0, 100);
                } else {
                    amount = ThreadLocalRandom.current().nextLong(100, 999) * 10;
                }

                PostCouponDto.Request reqDto = PostCouponDto.Request.setRequestTestData(
                        faker.book().title(),
                        faker.book().publisher(),
                        itemType,
                        amount,
                        ThreadLocalRandom.current().nextLong(1, 200),
                        startDt,
                        endDt
                );
                postCoupon(reqDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postCoupon(PostCouponDto.Request reqDto) throws Exception {
        ResultActions result = mockMvc.perform(
                post("/api/coupon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqDto))
        );

        result.andExpect(status().is(200));
        System.out.println("result = " + result);
    }



}
