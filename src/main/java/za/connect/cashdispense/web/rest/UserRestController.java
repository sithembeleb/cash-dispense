package za.connect.cashdispense.web.rest;

import com.sun.org.apache.xml.internal.utils.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            @RequestParam( name = "name") final String name,
            @RequestParam( name = "password") final String password,
            @RequestParam( name = "username") final String username) {
        return userService.saveNewUser(name, password, username);
    }

    @RequestMapping(value = "/authenticate",
            method = GET)
    private ResponseEntity<UserResponse> authenticateUser(
            @RequestParam( name = "password") final String password,
            @RequestParam( name = "username") final String username) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setAccessControlAllowOrigin("http://localhost:4200");
        return new ResponseEntity<UserResponse>(userService.authenticateUser(username.trim(), password.trim()),
                responseHeaders, HttpStatus.OK);

    }

}
