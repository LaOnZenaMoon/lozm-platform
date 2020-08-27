package lozm.entity.auth;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleSet")
//    @OrderBy("ordernum desc")
//    private Set<Resources> resourcesSet = new LinkedHashSet<>();
//
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
//    private Set<Account> accounts = new HashSet<>();

}
