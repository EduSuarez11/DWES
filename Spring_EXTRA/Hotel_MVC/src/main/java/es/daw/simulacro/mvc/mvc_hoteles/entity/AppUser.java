package es.daw.simulacro.mvc.mvc_hoteles.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class AppUser implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=60)
    private String username;

    @Column(nullable=false, length=100)
    private String password; // BCrypt

    @Column(length = 100)
    private String fullName;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable=false)
    private boolean enabled = true;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name="user_id"),
//            inverseJoinColumns = @JoinColumn(name="role_id")
//    )



//    public void addRole(Role role) {
//        roles.add(role);
//        role.getUsers().add(this);
//    }
//
//    public void removerRole(Role role) {
//        roles.remove(role);
//        role.getUsers().remove(this);
//    }

    // ------------------------------------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (roles == null) return List.of();
//
//        return roles.stream()
//                .map(role -> role.getName())
//                //.map(name -> "ROLE_" + name)
//                .map(name -> name.startsWith("ROLE_") ? name : "ROLE_" + name)
//                .map(SimpleGrantedAuthority::new)
//                .toList();
        System.out.println("getAuthorities: "+ role.getName());
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }


    /**
     * Indica si la cuenta del usuario ha expirado.
     * Devuelve true si la cuenta no ha expirado.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
        // // Devuelve true si la fecha actual es anterior o igual a la de expiración
        //    return !LocalDate.now().isAfter(accountExpirationDate);
    }

    /**
     * Indica si la cuenta del usuario está bloqueada.
     * Devuelve true si la cuenta no está bloqueada.
     */
    @Override
    public boolean isAccountNonLocked() { return true; }

    /**
     * Indica si las credenciales del usuario (contraseña) han expirado.
     * Devuelve true si las credenciales no han expirado.
     */
    @Override
    public boolean isCredentialsNonExpired() { return true; }

    /**
     * Indica si la cuenta del usuario está habilitada.
     * Devuelve true si la cuenta está activa.
     */
    @Override
    public boolean isEnabled() { return true; }


}
