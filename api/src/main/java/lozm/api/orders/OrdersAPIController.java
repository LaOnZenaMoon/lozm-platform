package lozm.api.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/orders")
@RestController
@RequiredArgsConstructor
public class OrdersAPIController {

    private final OrdersService ordersService;



}
