package lozm.api.delivery;

import lombok.RequiredArgsConstructor;

import lozm.core.dto.delivery.GetDeliveryDto;
import lozm.core.dto.delivery.PostDeliveryDto;
import lozm.core.dto.delivery.PutDeliveryDto;
import lozm.core.exception.APIException;
import lozm.domain.entity.Delivery;
import lozm.domain.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository DeliveryRepository;


    public List<GetDeliveryDto.Response> findAllDeliverys() {
        List<Delivery> DeliveryList = DeliveryRepository.findAll();

        return DeliveryList.stream().map(o -> new GetDeliveryDto.Response(o.getId(), o.getName(), o.getPrice(), o.getQuantity()))
                .collect(toList());
    }

    @Transactional
    public void save(PostDeliveryDto.Request reqDto) throws Exception {
        Delivery Delivery = new Delivery();
        Delivery.insertDelivery(reqDto);
    }

    @Transactional
    public void update(PutDeliveryDto.Request reqDto) throws Exception {
        Optional<Delivery> findDelivery = DeliveryRepository.findById(reqDto.getId());
        findDelivery.orElseThrow(() -> new APIException("Delivery_0002", "Delivery doesn't exist."));
        findDelivery.get().updateDelivery(reqDto);
    }
    
}
