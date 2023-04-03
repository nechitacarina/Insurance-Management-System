package com.project.insurancems.controller;

import com.project.insurancems.payload.request.UpdatePasswordRequest;
import com.project.insurancems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/password/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public void updatePassword(@PathVariable("id") int id, @RequestBody UpdatePasswordRequest password){
        userService.updatePassword(id, password);
    }
}
