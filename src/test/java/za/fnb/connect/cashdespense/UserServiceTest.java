package za.fnb.connect.cashdespense;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import za.connect.cashdispense.CashDispenseApplication;
import za.connect.cashdispense.persistance.repository.UserRepository;
import za.connect.cashdispense.services.UserService;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CashDispenseApplication.class)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    public void when_valid_parameters_expect_successful_user_creation(){
        userService.saveNewUser("testName", "password", "testUserName");
        assertTrue("Repository must return 1 record ", userRepository.findAll().size() == 1);
    }

    @Test
    @DirtiesContext
    public void when_valid_parameters_expect_successful_user_authentication(){
        userService.saveNewUser("testName", "password", "testUserName");
        userService.authenticateUser("Sthe", "password");
        System.out.println(userRepository.findAll().size());
    }

}
