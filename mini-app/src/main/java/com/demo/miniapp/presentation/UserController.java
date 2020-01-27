package com.demo.miniapp.presentation;

import com.demo.miniapp.dto.UserDTO;
import com.demo.miniapp.dto.UserWrapperDTO;
import com.demo.miniapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ApiResponse<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        return new ApiResponse<>(HttpStatus.OK, userService.createUser(userDTO));
    }

    @PostMapping(value="/delete/{id}", consumes = "application/json", produces = "application/json")
    public ApiResponse<Boolean> update(@PathVariable("id") @NotBlank long id){
        return new ApiResponse<>(HttpStatus.OK, userService.deleteUser(id));
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ApiResponse<UserWrapperDTO> retrieveAllUsers() {
        return new ApiResponse<>(HttpStatus.OK, userService.retrieveAllUsers());
    }

//    @CrossOrigin(value = "*", allowedHeaders = "*")
//    @PutMapping(value="/{id}", consumes = "application/json", produces = "application/json")
    @PostMapping(value="/{id}", consumes = "application/json", produces = "application/json")
    public ApiResponse<UserDTO> update(@PathVariable("id") @NotBlank long id,
                                          @RequestBody @Valid UserDTO userDTO){
        return new ApiResponse<>(HttpStatus.OK, userService.updateUser(id, userDTO));
    }

    @GetMapping(value="/{id}", consumes = "application/json", produces = "application/json")
    public ApiResponse<UserDTO> update(@PathVariable("id") @NotBlank Long id){
        return new ApiResponse<>(HttpStatus.OK, userService.retrieveUser(id));
    }
}
