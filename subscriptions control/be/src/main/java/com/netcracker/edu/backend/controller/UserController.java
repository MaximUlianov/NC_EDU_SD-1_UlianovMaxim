package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.DTO.UserDTO;
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
    public List getUsers(@PathVariable(value = "page") int page,
                               @PathVariable(value = "elements") int perPage) {
        return userService.getUsers(page, perPage);
    }


    @GetMapping(value = "/totalPages")
    @ResponseBody
    public ResponseEntity getTotalPages(@RequestParam(name = "perPage") int perPage){
        return ResponseEntity.ok(userService.getTotalPages(perPage));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity addUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.save(user).get());
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public ResponseEntity getUserByLogin(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity getUserInfoByEmail(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(userService.getUserInfoByEmail(email));
    }

    @GetMapping(value = "/username")
    @ResponseBody
    public ResponseEntity getUsername(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(userService.getUsername(email));
    }

    @GetMapping(value = "/subscriptions")
    @ResponseBody
    public ResponseEntity getUserSubscriptionsById(@RequestParam(value = "id") long id){
        return ResponseEntity.ok(userService.getUserSubscrById(id));
    }

    @GetMapping(value = "/search")
    @ResponseBody
    public ResponseEntity searchUser(@RequestParam(value = "type") String type,
                                           @RequestParam(value = "value") String value){
        return ResponseEntity.ok(userService.searchUser(type, value));
    }

    @PostMapping(value = "/subscriptions/block")
    @ResponseBody
    public ResponseEntity blockSubscription(@RequestBody long [] id) {
        return ResponseEntity.ok(userService.blockSubscription(id));
    }

    @PostMapping(value = "/subscriptions/unblock")
    @ResponseBody
    public ResponseEntity unblockSubscription(@RequestBody long [] id) {
        return ResponseEntity.ok(userService.unblockSubscription(id));
    }

    @GetMapping(value = "/audit")
    @ResponseBody
    public ResponseEntity unblockSubscription(@RequestParam(value = "id") long id) {
        return ResponseEntity.ok(userService.getUserHistory(id));
    }

    @GetMapping(value = "/wallets")
    @ResponseBody
    public ResponseEntity getUserWallets(@RequestParam(value = "id") long id){
        return ResponseEntity.ok(userService.getUserWallets(id));
    }

}
