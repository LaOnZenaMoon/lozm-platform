package lozm.bulkInsertTestData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lozm.code.ClothingSizeType;
import lozm.code.ItemType;
import lozm.dto.item.PostItemDto;
import lozm.dto.store.GetStoreDto;
import lozm.store.StoreService;
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
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ItemBulkInsert {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StoreService storeService;


    @Test
    public void setItems() {
        for (GetStoreDto findStore : storeService.findAllStores()) {
            setItem(ItemType.OUTER.name(), findStore.getId());
            setItem(ItemType.TOP.name(), findStore.getId());
            setItem(ItemType.BOTTOM.name(), findStore.getId());
            setItem(ItemType.SHOES.name(), findStore.getId());
        };
    }

    public void setItem(String itemType, Long storeId) {
        try {
            Faker faker = new Faker();
            List<String> sizeList = new ArrayList<>();
            sizeList.add(ClothingSizeType.XS.name());
            sizeList.add(ClothingSizeType.S.name());
            sizeList.add(ClothingSizeType.M.name());
            sizeList.add(ClothingSizeType.L.name());
            sizeList.add(ClothingSizeType.XL.name());
            sizeList.add(ClothingSizeType.XXL.name());

            for(int i=0; i<50; i++) {
                String size = null;
                if(ItemType.SHOES.name().equals(itemType)) {
                    size = String.valueOf(ThreadLocalRandom.current().nextInt(220, 310));
                } else {
                    size = sizeList.get(ThreadLocalRandom.current().nextInt(0, 5));
                }

                PostItemDto.Request reqDto = PostItemDto.Request.setRequestTestData(
                        faker.university().name(),
                        ThreadLocalRandom.current().nextLong(10, 999) * 1000,
                        ThreadLocalRandom.current().nextLong(1, 200),
                        itemType,
                        faker.lorem().word(),
                        size,
                        storeId
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
