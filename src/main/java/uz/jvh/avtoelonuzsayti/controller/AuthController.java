package uz.jvh.avtoelonuzsayti.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jvh.avtoelonuzsayti.domain.enums.UserState;
import uz.jvh.avtoelonuzsayti.domain.request.LoginDto;
import uz.jvh.avtoelonuzsayti.domain.request.UserCreateDTO;
import uz.jvh.avtoelonuzsayti.domain.response.UserResponse;
import uz.jvh.avtoelonuzsayti.domain.enums.UserRole;
import uz.jvh.avtoelonuzsayti.service.UserService;


@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {


    @Autowired
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "sign-in-page";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "sign-up-page";
    }



    @PostMapping("/register")
    public String register(UserCreateDTO user, Model model) {
        if (userService.checkByUsernameAndEmail(user.getUsername(), user.getEmail())) {
            model.addAttribute("message", "This email or username is already in use!");
            return "sign-up-page";
        }
        userService.save(user);
        return "home-page";
    }

    @PostMapping("/login")
    public String login(LoginDto loginDto, HttpSession session) {
        UserResponse login = userService.login(loginDto);
        if (login != null && loginDto.getPassword().equals(login.getPassword())) {
            if(login.getUserState() == UserState.BLOCKED){
                return "block-page";
            }
            session.setAttribute("userId", login.getId());
            if (login.getRole() == UserRole.OWNER) {
                return "/owner/owner-page";
            } else if (login.getRole() == UserRole.ADMIN) {
                return "admin-page";
            } else if (login.getRole() == UserRole.USER) {
                return "user/user-menu";
            }
        }
        return "home-page";
    }


}
