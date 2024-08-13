package com.example.smarttaskmanager.Controller;

import com.example.smarttaskmanager.Model.User;
import com.example.smarttaskmanager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> register(@RequestBody User user){
        User userNew = userService.register(user);
        return ResponseEntity.ok(userNew);
    }

    @PostMapping("/login/{userName}")
    public ResponseEntity<String> login(@PathVariable String userName, @RequestBody User user){
        String status = userService.login(userName, user);
        if(status.equals("Success")){
            return ResponseEntity.ok(status);
        }
        return ResponseEntity.status(401).body(status);
    }

    @PutMapping("/passWord/{userName}")
    public ResponseEntity<String> resetPassWord(@PathVariable String userName, @RequestBody User user){
        String status = userService.updatePassword(userName, user);
        if(status.equals("Success")){
            return ResponseEntity.ok(status);
        }
        return ResponseEntity.status(401).body(status);
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable String userName){
        boolean deleted = userService.deleteUser(userName);
        if (deleted){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
