package com.clockwise.api.service;

import com.clockwise.api.config.jwt.JwtTokenProvider;
import com.clockwise.api.dto.UserDto;
import com.clockwise.api.model.RoleUser;
import com.clockwise.api.model.User;
import com.clockwise.api.repository.UserRepository;
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

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public boolean verifyUser(String email, String password) {

        return userRepository.findByEmail(email)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Id"));
    }


    public String generateToken(String email, String password) {
        Authentication authentication1  = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(authentication1);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        return token;
    }

    public boolean checkUserEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean createUser(UserDto userDto) {

        if (userDto.getEmail() == null || userDto.getEmail().isEmpty() || userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            return false;
        }

        if (!userDto.getRole().startsWith("ROLE_")) {
            userDto.setRole("ROLE_" + userDto.getRole());
        }

        RoleUser roleUser = RoleUser.valueOf(userDto.getRole());

        if (roleUser == null) {
            return false;
        }

        User newUser = new User.Builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(roleUser.toString())
                .isEnabled(true)
                .build();

        return userRepository.save(newUser);

    }

    public List<User> getUsersByRole(String role) {
        String roleStr;
        if (role.startsWith("ROLE_")) {
            roleStr = role;
        } else {
            roleStr = "ROLE_" + role;
        }

        List<User> users = null;

        try {
            users = userRepository.findUsersByRoles(roleStr.toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }


    public int getAllAdminCount() {

        List<User> users = getUsersByRole("ROLE_ADMIN");
        if (users == null) {return 0;}
        return users.size();


    }


}
