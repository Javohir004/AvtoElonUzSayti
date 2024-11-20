package uz.jvh.avtoelonuzsayti.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jvh.avtoelonuzsayti.domain.DTO.request.LoginDto;
import uz.jvh.avtoelonuzsayti.domain.DTO.request.UserCreateDTO;
import uz.jvh.avtoelonuzsayti.domain.DTO.response.UserResponse;
import uz.jvh.avtoelonuzsayti.domain.entity.User;
import uz.jvh.avtoelonuzsayti.exception.UsernameNotFoundException;
import uz.jvh.avtoelonuzsayti.repository.UserRepository;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public void save(UserCreateDTO userDto) {
        User user = mapToUser(userDto);
        userRepository.save(user);
    }



    public Boolean checkByUsernameAndEmail(String username , String email) {
        return userRepository.existsByUsernameAndEmail(username, email);
    }

    public UserResponse login(LoginDto loginDto) {
        User user = userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        return mapToResponse(user);
    }


    public UserResponse update(UserCreateDTO userDto , Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with ID " + id + " not found"));
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setBalance(userDto.getBalance());
        user.setBirthDate(userDto.getBirthDate());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        user.setVerificationToken(userDto.getVerificationToken());
        user.setEnabled(userDto.isEnabled());
        userRepository.save(user);
        return mapToResponse(user);
    }

    public UserResponse deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with ID " + userId + " not found"));
        user.setActive(false);
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
        userResponse.setIsActive(user.isActive());
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
        user.setVerificationToken(userDto.getVerificationToken());
        user.setEnabled(userDto.isEnabled());
        return user;
    }

}
