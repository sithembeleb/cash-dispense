package za.connect.cashdispense.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.connect.cashdispense.domain.UserResponse;
import za.connect.cashdispense.persistance.entity.UserEntity;
import za.connect.cashdispense.persistance.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private static final String LOGIN_SUCCESSFUL = "YOU HAVE LOGGED ON SUCCESSFULLY";
    private static final String LOGIN_FAILED = "INVALID PASSWORD OR USERNAME";
    private static final String USER_CREATION_FAILED = "User creation failed";
    private static final String USER_CREATION_SUCCESSFUL = "User created successfully";
    private UserRepository repository;

    @Autowired
    public UserService(final UserRepository repository) {
        this.repository = repository;
    }

    public UserResponse authenticateUser(final String username, final String password) {
        Optional<UserEntity> userEntity = repository.findOneByUsernameAndPassword(username, password);
        return userEntity.isPresent() ? new UserResponse(LOGIN_SUCCESSFUL) : new UserResponse(LOGIN_FAILED);

    }

    public UserResponse saveNewUser(String name, String password, String username) {
        UserEntity entity = repository.save(new UserEntity(name.trim(), password.trim(), username.trim()));
        return entity.getId() == null ? new UserResponse(USER_CREATION_FAILED) : new UserResponse(USER_CREATION_SUCCESSFUL);
    }
}
