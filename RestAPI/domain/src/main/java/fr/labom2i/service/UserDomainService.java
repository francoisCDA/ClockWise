package fr.labom2i.service;

import fr.labom2i.domainEntity.User;
import fr.labom2i.spi.port.UserRepository;

public class UserDomainService {

    protected UserRepository userRepository;

    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        userRepository.save(user);

    }


    public boolean isEmailExist(String email) {
        return true;
    }

    public boolean isValidCredentials(String email, String password) {
        return true;
    }

    public User getUserByEmail(String email) {
        return null;
    }
}
