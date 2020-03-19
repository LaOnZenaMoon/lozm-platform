package lozm.domain.entity;

import javax.persistence.*;

@Entity
@Table(schema = "LOZM", name = "USER")
public class User {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;



}