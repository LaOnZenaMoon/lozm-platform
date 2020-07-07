package lozm.api.delivery;

import lombok.RequiredArgsConstructor;

import lozm.global.exception.ServiceException;
import lozm.object.dto.delivery.GetDeliveryDto;
import lozm.repository.RepositorySupport;
import lozm.object.vo.delivery.DeliveryVo;
import lozm.entity.delivery.Delivery;
import lozm.repository.delivery.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static net.logstash.logback.encoder.org.apache.commons.lang3.ObjectUtils.isEmpty;
//import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final RepositorySupport repositorySupport;


    public List<GetDeliveryDto> getDeliveryList() {
        List<Delivery> deliveryList = repositorySupport.selectDeliveryList();
        List<GetDeliveryDto> rtnList = new ArrayList<>();
        for (Delivery delivery : deliveryList) {
            GetDeliveryDto dto = GetDeliveryDto.builder()
                    .id(delivery.getId())
                    .status(delivery.getStatus())
                    .country(isEmpty(delivery.getAddress()) ? null : delivery.getAddress().getCountry())
                    .zipCode(isEmpty(delivery.getAddress()) ? null : delivery.getAddress().getZipCode())
                    .city(isEmpty(delivery.getAddress()) ? null : delivery.getAddress().getCity())
                    .street(isEmpty(delivery.getAddress()) ? null : delivery.getAddress().getStreet())
                    .etc(isEmpty(delivery.getAddress()) ? null : delivery.getAddress().getEtc())
                    .build();

            rtnList.add(dto);
        }

        return rtnList;
    }

    @Transactional
    public void save(DeliveryVo deliveryVo) throws Exception {
        Delivery delivery = new Delivery();
        delivery.insertDelivery(deliveryVo);

        deliveryRepository.save(delivery);
    }

    @Transactional
    public void update(DeliveryVo deliveryVo) throws Exception {
        Optional<Delivery> findDelivery = findDelivery(deliveryVo.getId());
        findDelivery.get().updateDelivery(deliveryVo);
    }

    @Transactional
    public void delete(DeliveryVo deliveryVo) {
        Optional<Delivery> findDelivery = findDelivery(deliveryVo.getId());
        findDelivery.get().deleteDelivery(deliveryVo);
    }

    private Optional<Delivery> findDelivery(Long deliveryId) {
        Optional<Delivery> findDelivery = deliveryRepository.findById(deliveryId);
        findDelivery.orElseThrow(() -> {
            throw new ServiceException("DELIVERY_0002", "Delivery doesn't exist.");
        });
        return findDelivery;
    }

}
