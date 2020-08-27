package lozm.entity.auth;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity @Table(schema = "LOZM", name = "RESOURCES")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class Resources implements Serializable {

    @Id @GeneratedValue
    @Column(name = "RESOURCES_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "HTTP_METHOD")
    private String httpMethod;

    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;

    @OneToMany(mappedBy = "resources")
    private List<ResourcesRole> resourcesRoles;

}
