package uz.jvh.avtoelonuzsayti.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import uz.jvh.avtoelonuzsayti.domain.DTO.request.UserCreateDTO;
import uz.jvh.avtoelonuzsayti.domain.DTO.response.UserResponse;
import uz.jvh.avtoelonuzsayti.service.UserService;

@Controller
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {


    private UserService userService;


    @PostMapping("/create-user")
    public String createUser(UserCreateDTO userCreateDTO) {
        userService.save(userCreateDTO);
        return "owner-page";
    }


    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long userId, Model model) {
        UserResponse userResponse = userService.deleteUser(userId);
        model.addAttribute("users", userResponse);
        return "show-users";
    }


    @PostMapping( "/update-user")
    public String update(@RequestParam(name = "userId") Long userID , UserCreateDTO updatedUser, Model model) {
        UserResponse update = userService.update(updatedUser, userID);
        model.addAttribute("users", update);
        return "show-users";
    }



}
