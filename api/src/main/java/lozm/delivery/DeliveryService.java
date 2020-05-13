package lozm.delivery;

import lombok.RequiredArgsConstructor;

import lozm.dto.delivery.GetDeliveryDto;
import lozm.exception.APIException;
import lozm.repository.RepositorySupport;
import lozm.vo.delivery.DeliveryVo;
import lozm.entity.delivery.Delivery;
import lozm.repository.delivery.DeliveryRepository;
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
    private final RepositorySupport repositorySupport;


    public List<GetDeliveryDto> getDeliveryList() {
        List<Delivery> deliveryList = repositorySupport.selectDeliveryList();

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
        findDelivery.orElseThrow(() -> {
            throw new APIException("DELIVERY_0002", "Delivery doesn't exist.");
        });

        findDelivery.get().updateDelivery(deliveryVo);
    }

    public void delete(DeliveryVo deliveryVo) {
        Optional<Delivery> findDelivery = deliveryRepository.findById(deliveryVo.getId());
        findDelivery.orElseThrow(() -> {
            throw new APIException("DELIVERY_0002", "Delivery doesn't exist.");
        });

        findDelivery.get().deleteDelivery(deliveryVo);
    }
}
