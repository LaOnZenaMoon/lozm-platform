package lozm.entity.auth;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity @Table(schema = "LOZM", name = "ROLE")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class Role implements Serializable {

    @Id @GeneratedValue
    @Column(name = "ROLE_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESC")
    private String description;

    @OneToMany(mappedBy = "role")
    private List<AccountRole> accountRoles;

    @OneToMany(mappedBy = "role")
    private List<ResourcesRole> resourcesRoles;

}
