package main.cursova_integrate.Controllers;

import main.cursova_integrate.DAL.DTOs.Login;
import main.cursova_integrate.DAL.DTOs.UserToCreate;
import main.cursova_integrate.DAL.Models.User;
import main.cursova_integrate.DAL.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserRepository userRepository;
    public UserController(){}

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserToCreate signup){
        try {
            User newUser = new User();

            System.out.print(signup.BirthDate);

            newUser.Login = signup.Login;
            newUser.Email = signup.Email;
            newUser.Password = signup.Password;
            newUser.Phone = signup.Phone;
            newUser.BirthDate = signup.BirthDate;

            List<User> users = userRepository.findAll();
            boolean present = false;

            userRepository.save(newUser);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            System.out.print(ex.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Login login){
        try {
            Optional<List<User>> users = Optional.of(userRepository.findAll());

            Optional<User> neededUser = Optional.empty();

            for (User user : users.get()){
                if(user.Email.equals(login.Email) && user.Password.equals(login.Password)){
                    neededUser = Optional.of(user);
                }
            }
            System.out.print(neededUser.get());

            return new ResponseEntity<>(neededUser.get(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
