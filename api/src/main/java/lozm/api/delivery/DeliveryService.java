package lozm.api.delivery;

import lombok.RequiredArgsConstructor;

import lozm.core.dto.delivery.GetDeliveryDto;
import lozm.core.dto.delivery.PostDeliveryDto;
import lozm.core.dto.delivery.PutDeliveryDto;
import lozm.core.exception.APIException;
import lozm.core.vo.delivery.DeliveryVo;
import lozm.domain.entity.delivery.Delivery;
import lozm.domain.repository.delivery.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;


    public List<GetDeliveryDto> findAllDeliveries() {
        List<Delivery> deliveryList = deliveryRepository.findAll();

        return deliveryList.stream().map(d -> new GetDeliveryDto(
                    d.getId(),
                    d.getStatus(),
                    d.getAddress().getCountry(),
                    d.getAddress().getZipCode(),
                    d.getAddress().getCity(),
                    d.getAddress().getStreet(),
                    d.getAddress().getEtc()
                ))
                .collect(toList());
    }

    @Transactional
    public void save(DeliveryVo deliveryVo) throws Exception {
        Delivery delivery = new Delivery();
        delivery.insertDelivery(deliveryVo);

        deliveryRepository.save(delivery);
    }

    @Transactional
    public void update(DeliveryVo deliveryVo) throws Exception {
        Optional<Delivery> findDelivery = deliveryRepository.findById(deliveryVo.getId());
        findDelivery.orElseThrow(() -> new APIException("DELIVERY_0002", "Delivery doesn't exist."));
        findDelivery.get().updateDelivery(deliveryVo);
    }
    
}
