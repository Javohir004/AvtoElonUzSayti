package uz.jvh.avtoelonuzsayti.controller;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.jvh.avtoelonuzsayti.domain.enums.UserRole;
import uz.jvh.avtoelonuzsayti.domain.enums.UserState;
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
        return "/owner/owner-page";
    }

    @GetMapping("/return2")
    public String index2() {
        return "/user/user-menu";
    }


    @GetMapping("/create-user")
    public String createUser() {
        return "/owner/create-user";
    }


    @GetMapping("/show-users-by-role")
    public String getUsersByRole(@RequestParam("role") UserRole role, Model model) {
        List<UserResponse> users = userService.findByRole(role);
        model.addAttribute("users", users);
        return "/owner/show-users";
    }


    @PostMapping("/create-user")
    public String createUser(UserCreateDTO userCreateDTO) {
        userService.save(userCreateDTO);
        return "/owner/owner-page";
    }


    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long userId, Model model) {
        UserResponse user = userService.deleteUser(userId);
        List<UserResponse> users = userService.findByRole(user.getRole());
        model.addAttribute("users", users);
        return "/owner/show-users";
    }

    ///user/update-user
    @GetMapping("/update-user")
    public String update(@RequestParam(name = "userId") Long userID , Model model) {
        UserResponse user = userService.findById(userID);
        model.addAttribute("user", user);
        return "/owner/update-user";
    }

    @GetMapping("/add-balance")
    public String addBalance(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        UserResponse user = userService.findById(userId);
        model.addAttribute("money", user.getBalance());
        return "/user/balance";
    }

    @GetMapping("/user-profile")
    public String userProfile(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        UserResponse user = userService.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("state", UserState.values());
        return "/user/user-profile";
    }


    @PostMapping("/user-profile")
    public String userProfile(UserCreateDTO userCreateDTO, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        userService.update(userCreateDTO, userId);
        return "/user/user-menu";
    }

    @PostMapping("/add-balance")
    public String addBalance(@RequestParam Double amount , Model model , HttpSession session) {
        Long userId =(Long) session.getAttribute("userId");
        if (amount <= 0) {
            model.addAttribute("message", "Iltimos, ijobiy miqdorni kiriting!");
        }
        Double v = userService.addBalance(amount, userId);
        model.addAttribute("money", v);
        return "/user/balance";
    }

    @PostMapping( "/update-user")
    public String update(@RequestParam(name = "userId") Long userID , UserCreateDTO updatedUser, Model model) {
        userService.update(updatedUser,userID);
        List<UserResponse> users = userService.findByRole(updatedUser.getRole());
        model.addAttribute("users", users);
        return "/owner/show-users";
    }

    @PostMapping("/UnBlock-user")
    public String unBlockUser(@RequestParam(name = "userId") Long userID , Model model) {
        userService.unblockUser(userID);
        return "/owner/owner-page";
    }

    @PostMapping("/block-user")
    public String blockUser(@RequestParam(name = "userId") Long userID , Model model) {
        userService.blockUser(userID);
        return "owner/owner-page";
    }

//    @GetMapping("/get-blocked-users")
//    public String getBlockedUsers(Model model) {
//        List<UserResponse> blockedUsers = userService.getBlockedUsers();
//        model.addAttribute("blockedUsers", blockedUsers);
//        return "/blocked-users";
//    }

    @GetMapping("/search-user")
    public String searchUser() {
        return "/owner/search-users";
    }



    @PostMapping("/search-user")
    public String searchUser(UserSearchRequest userSearchRequest, Model model) {
        List<UserResponse> userResponses = userService.searchUser(userSearchRequest);
        model.addAttribute("users", userResponses);
        return "/owner/search-users";
    }



}
