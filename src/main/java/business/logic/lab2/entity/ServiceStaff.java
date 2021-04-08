package business.logic.lab2.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;



@Entity
@Table(name = "SERVICE_STAFF")
@Getter
@Setter
public class ServiceStaff {
    @Column(name = "service_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
