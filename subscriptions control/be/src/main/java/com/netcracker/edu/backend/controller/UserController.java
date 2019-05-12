package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.DTO.LogInUserDTO;
import com.netcracker.edu.backend.DTO.UserDTO;
import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{page}/{elements}")
    @ResponseBody
    public List<User> getUsers(@PathVariable(value = "page") int page,
                               @PathVariable(value = "elements") int perPage) {
        return userService.getUsers(page, perPage);
    }


    @GetMapping(value = "/totalPages")
    @ResponseBody
    public ResponseEntity<Integer> getTotalPages(@RequestParam(name = "perPage") int perPage){
        return ResponseEntity.ok(userService.getTotalPages(perPage));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.save(user).get());
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<LogInUserDTO> getUserByLogin(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @GetMapping(value = "/info")
    @ResponseBody
    public ResponseEntity<UserDTO> getUserInfoByEmail(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(userService.getUserInfoByEmail(email));
    }

    @GetMapping(value = "/username")
    @ResponseBody
    public ResponseEntity<String> getUsername(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(userService.getUsername(email));
    }

    @GetMapping(value = "/subscriptions")
    @ResponseBody
    public ResponseEntity<List<Subscription>> getUserSubscriptionsById(@RequestParam(value = "id") long id){
        return ResponseEntity.ok(userService.getUserSubscrById(id));
    }

    @GetMapping(value = "/search")
    @ResponseBody
    public ResponseEntity<List<User>> searchUser(@RequestParam(value = "type") String type,
                                           @RequestParam(value = "value") String value){
        return ResponseEntity.ok(userService.searchUser(type, value));
    }

    @PostMapping(value = "/subscriptions/block")
    @ResponseBody
    public ResponseEntity<String> blockSubscription(@RequestBody long [] id) {
        return ResponseEntity.ok(userService.blockSubscription(id));
    }

    @PostMapping(value = "/subscriptions/unblock")
    @ResponseBody
    public ResponseEntity<String> unblockSubscription(@RequestBody long [] id) {
        return ResponseEntity.ok(userService.unblockSubscription(id));
    }




}
