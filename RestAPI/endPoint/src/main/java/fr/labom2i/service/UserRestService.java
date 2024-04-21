package fr.labom2i.service;

import fr.labom2i.config.jwt.JwtTokenProvider;
import fr.labom2i.domainEntity.RoleUser;
import fr.labom2i.domainEntity.User;
import fr.labom2i.dto.UserDto;
import fr.labom2i.model.UserRest;
import fr.labom2i.repository.UserBddRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRestService extends UserDomainService implements UserDetailsService {

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRestService() {
        super(new UserBddRepository());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserRest(user);
    }

    public boolean createUser(UserDto userDto) {
        User user = new User.Builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(RoleUser.valueOf(userDto.getRole()))
                .build();

        boolean ret = userRepository.save(user);

        return ret;

    }

    public boolean verifyUser(String pseudo, String password) {

        return userRepository.findByPseudo(pseudo)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Id"));
    }

    public boolean checkUserPseudoExists(String pseudo) {
        return userRepository.findByPseudo(pseudo).isPresent();
    }

    public Object generateToken(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication);

    }
}
