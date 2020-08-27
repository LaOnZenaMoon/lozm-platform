package lozm.entity.auth;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(schema = "LOZM", name = "RESOURCES_ROLE")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class ResourcesRole implements Serializable {

    @Id @GeneratedValue
    @Column(name = "RESOURCES_ROLE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESOURCES_ID")
    private Resources resources;

}
