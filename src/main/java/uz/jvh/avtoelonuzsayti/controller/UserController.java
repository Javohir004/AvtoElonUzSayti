package uz.jvh.avtoelonuzsayti.controller;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.jvh.avtoelonuzsayti.domain.enums.UserRole;
import uz.jvh.avtoelonuzsayti.domain.request.UserCreateDTO;
import uz.jvh.avtoelonuzsayti.domain.request.UserSearchRequest;
import uz.jvh.avtoelonuzsayti.domain.response.UserResponse;
import uz.jvh.avtoelonuzsayti.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/return")
    public String index() {
        return "owner-page";
    }
    @GetMapping("/return2")
    public String index2() {
        return "/user/user-menu";
    }


    @GetMapping("/create-user")
    public String createUser() {
        return "create-user";
    }


    @GetMapping("/show-users-by-role")
    public String getUsersByRole(@RequestParam("role") UserRole role, Model model) {
        List<UserResponse> users = userService.findByRole(role);
        model.addAttribute("users", users);
        return "show-users";
    }


    @PostMapping("/create-user")
    public String createUser(UserCreateDTO userCreateDTO) {
        userService.save(userCreateDTO);
        return "owner-page";
    }


    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long userId, Model model) {
        UserResponse user = userService.deleteUser(userId);
        List<UserResponse> users = userService.findByRole(user.getRole());
        model.addAttribute("users", users);
        return "show-users";
    }

    ///user/update-user
    @GetMapping("/update-user")
    public String update(@RequestParam(name = "userId") Long userID , Model model) {
        UserResponse user = userService.findById(userID);
        model.addAttribute("user", user);
        return "update-user";
    }


    @PostMapping( "/update-user")
    public String update(@RequestParam(name = "userId") Long userID , UserCreateDTO updatedUser, Model model) {
        userService.update(updatedUser,userID);
        List<UserResponse> users = userService.findByRole(updatedUser.getRole());
        model.addAttribute("users", users);
        return "show-users";
    }

    @PostMapping("/UnBlock-user")
    public String unBlockUser(@RequestParam(name = "userId") Long userID , Model model) {
        userService.unblockUser(userID);
        return "owner-page";
    }

    @PostMapping("/block-user")
    public String blockUser(@RequestParam(name = "userId") Long userID , Model model) {
        userService.blockUser(userID);
        return "owner-page";
    }

    @GetMapping("/get-blocked-users")
    public String getBlockedUsers(Model model) {
        List<UserResponse> blockedUsers = userService.getBlockedUsers();
        model.addAttribute("blockedUsers", blockedUsers);
        return "blocked-users";
    }

    @GetMapping("/search-user")
    public String searchUser() {
        return "search-users";
    }

    @PostMapping("/search-user")
    public String searchUser(UserSearchRequest userSearchRequest, Model model) {
        List<UserResponse> userResponses = userService.searchUser(userSearchRequest);
        model.addAttribute("users", userResponses);
        return "search-users";
    }



}
