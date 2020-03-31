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


    public List<GetDeliveryDto.Response> findAllDeliveries() {
        List<Delivery> deliveryList = DeliveryRepository.findAll();

        return deliveryList.stream().map(d -> new GetDeliveryDto.Response(
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
    public void save(PostDeliveryDto.Request reqDto) throws Exception {
        Delivery Delivery = new Delivery();
        Delivery.insertDelivery(reqDto);
    }

    @Transactional
    public void update(PutDeliveryDto.Request reqDto) throws Exception {
        Optional<Delivery> findDelivery = DeliveryRepository.findById(reqDto.getId());
        findDelivery.orElseThrow(() -> new APIException("DELIVERY_0002", "Delivery doesn't exist."));
        findDelivery.get().updateDelivery(reqDto);
    }
    
}
