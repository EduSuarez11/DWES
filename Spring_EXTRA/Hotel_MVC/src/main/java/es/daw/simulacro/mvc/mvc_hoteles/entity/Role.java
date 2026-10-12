package es.daw.simulacro.mvc.mvc_hoteles.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=40, unique = true)
    private String name;

    @Column(length=100)
    private String description;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<AppUser> users = new HashSet<>();

//    public void addUser(AppUser user) {
//        users.add(user);
//        user.getRoles().add(this);
//    }
//
//    public void removeUser(AppUser user) {
//        users.remove(user);
//        user.getRoles().remove(this);
//    }

}
