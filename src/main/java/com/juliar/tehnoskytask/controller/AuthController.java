package com.juliar.tehnoskytask.controller;

import com.juliar.tehnoskytask.dto.CustomUserDetails;
import com.juliar.tehnoskytask.dto.LoginDto;
import com.juliar.tehnoskytask.dto.UserReadDto;
import com.juliar.tehnoskytask.service.LoginService;
import com.juliar.tehnoskytask.service.UserHolder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class AuthController {

    private final LoginService service;
    private final UserHolder userHolder;

    /**
     * {
     * "mail": "ivanovii@gmail.com",
     * "password": "123"
     * }
     */
    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserReadDto authentication(@RequestBody LoginDto dto) {
        var user = service.findByMail(dto);
        return user;
    }

    @GetMapping(value = "/hi")
    public String printHi() {
        return "Hi!";
    }


    @GetMapping(value = "/me")
    @ResponseStatus(HttpStatus.OK)
    public CustomUserDetails details() {
        return (CustomUserDetails) userHolder.getUser();
    }


}
