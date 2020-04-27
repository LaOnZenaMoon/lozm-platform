package lozm.entity.store;

import lombok.Getter;
import lozm.entity.BaseEntity;
import lozm.entity.item.Item;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "LOZM", name = "STORE")
@Getter
public class Store extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "STORE_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TEL_NUMBER")
    private String telNumber;

    @Column(name = "INFO")
    private String info;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Item> itemList;

}
