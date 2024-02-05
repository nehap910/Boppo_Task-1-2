package com.Boppo_Task.Controller;

import com.Boppo_Task.Repository.UserRepository;
import com.Boppo_Task.entities.User;
import com.Boppo_Task.request.LoginRequest;
import com.Boppo_Task.response.MessageResponse;
import com.Boppo_Task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;


    @Autowired
    UserService userService;


    //User Registration
    @PostMapping("/registeruser")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is alreday Exist"));
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is alreday Exist"));
        }


        user.setConfirmpassword(user.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    //User Signin
    @PostMapping("/signin")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());

        if (user != null && loginRequest.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Login Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
        }
    }

    // to get the details of all user
    @GetMapping("/getallusers")
    public List<User> getAllUser(User user) {
        return userService.getAllUsers();
    }

    // to get the user details by their id
    @GetMapping("/getbyid/{id}")
    public User getUserById(@PathVariable int id){
        return userRepository.findById(id).orElse(null);
    }


    //to delete the user by id
    @DeleteMapping("/deletebyid/{id}")
    public String deleteUserById(@PathVariable int id){
         userRepository.deleteById(id);
         return "Deleted Id is "+id;
    }


    //to update the user details by its id
    @PutMapping("/updateuser/{id}")
    public User updateUser(@RequestBody User user,@PathVariable int id){
        User existingUser = userRepository.findById(id).orElse(null);
        existingUser.setName(user.getName());
        existingUser.setCity(user.getCity());
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setConfirmpassword(user.getPassword());

        return userRepository.save(existingUser);

    }


//Reset Password
    @PutMapping("/updatepassword/{id}")
    public String updatePassword(@RequestBody User user,@PathVariable int id){
        User existingUser = userRepository.findById(id).orElse(null);
        existingUser.setConfirmpassword(user.getPassword());
        existingUser.setPassword(user.getPassword());
        userRepository.save(existingUser);
        return "Password Changed Successfully!!! Newly Changed Password is "+existingUser.getPassword();

    }


}