package com.yube.controllers;

import com.yube.model.dto.User;
import com.yube.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User getById(@PathVariable("id") String id) {
        return userService.getById(id);
    }

    @PutMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }
}
