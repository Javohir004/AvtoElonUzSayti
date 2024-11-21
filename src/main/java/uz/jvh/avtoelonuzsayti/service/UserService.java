package uz.jvh.avtoelonuzsayti.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jvh.avtoelonuzsayti.domain.enums.UserRole;
import uz.jvh.avtoelonuzsayti.domain.enums.UserState;
import uz.jvh.avtoelonuzsayti.domain.request.LoginDto;
import uz.jvh.avtoelonuzsayti.domain.request.UserCreateDTO;
import uz.jvh.avtoelonuzsayti.domain.request.UserSearchRequest;
import uz.jvh.avtoelonuzsayti.domain.response.UserResponse;
import uz.jvh.avtoelonuzsayti.domain.entity.User;
import uz.jvh.avtoelonuzsayti.exception.UsernameNotFoundException;
import uz.jvh.avtoelonuzsayti.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(UserCreateDTO userDto) {
        User user = mapToUser(userDto);
        userRepository.save(user);
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return mapToResponse(user);
    }

    public List<UserResponse> findByRole(UserRole role) {
        List<User> usersByRole = userRepository.findByRoleAndIsActiveTrue(role);
        return usersByRole.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Boolean checkByUsernameAndEmail(String username , String email) {
        return userRepository.existsByUsernameAndEmail(username, email);
    }

    public UserResponse login(LoginDto loginDto) {
        User user = userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return mapToResponse(user);
    }

    public List<UserResponse> searchUser(UserSearchRequest userSearchRequest) {
        List<User> users = userRepository.findUsersByUsernameEmailAndRole(userSearchRequest.getUsername(),
                userSearchRequest.getEmail(),
                userSearchRequest.getRole());
        return users.stream().map((user) -> mapToResponse(user)).
                collect(Collectors.toList());
    }

    public List<UserResponse> getBlockedUsers() {
        List<User> usersByState = userRepository.findUsersByState(UserState.BLOCKED);
        return usersByState.stream().
                map(u -> mapToResponse(u)).
                collect(Collectors.toList());
    }

    public UserResponse update(UserCreateDTO userDto , Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with ID " + id + " not found"));
        if(userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
        user.setState(userDto.getUserState());
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setBalance(userDto.getBalance());
        user.setBirthDate(userDto.getBirthDate());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        userRepository.save(user);
        return mapToResponse(user);
    }

    public UserResponse deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with ID " + userId + " not found"));
        user.setActive(false);
        user.setState(UserState.INACTIVE);
        userRepository.save(user);
        return mapToResponse(user);
    }

    public UserResponse mapToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setSurname(user.getSurname());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());
        userResponse.setBalance(user.getBalance());
        userResponse.setBirthDate(user.getBirthDate());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setAddress(user.getAddress());
        userResponse.setUserState(user.getState());
        return userResponse;
    }

    public User mapToUser(UserCreateDTO userDto) {
        User user = new User();
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setBalance(userDto.getBalance());
        user.setBirthDate(userDto.getBirthDate());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        return user;
    }

    public void blockUser(Long id){
        User user = userRepository.findById(id).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setState(UserState.BLOCKED);
        userRepository.save(user);
    }

    public void unblockUser(Long id){
        User user = userRepository.findById(id).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setState(UserState.BLOCKED);
        userRepository.save(user);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToResponse).
                collect(Collectors.toList());
    }

}
