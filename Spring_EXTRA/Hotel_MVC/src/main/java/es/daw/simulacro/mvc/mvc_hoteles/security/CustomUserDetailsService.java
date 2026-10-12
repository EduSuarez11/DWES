package es.daw.simulacro.mvc.mvc_hoteles.security;

import es.daw.simulacro.mvc.mvc_hoteles.entity.AppUser;
import es.daw.simulacro.mvc.mvc_hoteles.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(login)
//                .orElseThrow(() -> new UsernameNotFoundException(login));
        System.out.println("loadUserByUsername: " + login);
        AppUser user = appUserRepository
                .findByUsername(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + login));

        return user;
    }



}

