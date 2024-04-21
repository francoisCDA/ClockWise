package fr.labom2i.spi.port;

import fr.labom2i.domainEntity.User;
import io.micrometer.observation.ObservationFilter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends BaseCrudRepo<User> {

    Optional<User> findByEmail(String username);

    Optional<User> findByPseudo(String pseudo);
}
