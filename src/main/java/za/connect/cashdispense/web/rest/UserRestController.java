package za.connect.cashdispense.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.connect.cashdispense.domain.UserResponse;
import za.connect.cashdispense.services.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("cash/dispense")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register",
            method = POST)
    private UserResponse registerUser(
            @RequestParam(required = true, name = "name") final String name,
            @RequestParam(required = true, name = "password") final String password,
            @RequestParam(required = true, name = "username") final String username){
            return userService.saveNewUser(name, password, username);
    }

    @RequestMapping(value = "/authenticate",
            method = GET)
    private UserResponse authenticateUser(
            @RequestParam(required = true, name = "password") final String password,
            @RequestParam(required = true, name = "username") final String username) {
            return userService.authenticateUser(username.trim(), password.trim());
    }

}
