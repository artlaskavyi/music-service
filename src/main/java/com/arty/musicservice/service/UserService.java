package com.arty.musicservice.service;

import com.arty.musicservice.exception.EntityNotFoundException;
import com.arty.musicservice.exception.FieldNotUniqueException;
import com.arty.musicservice.model.User;
import com.arty.musicservice.record.UserDTO;
import com.arty.musicservice.record.UserRequestDTO;
import com.arty.musicservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
}

    public UserDTO saveUser(UserRequestDTO userRequestDTO) {
        System.out.println("Attempting to save user: " + userRequestDTO.username());
        if (userRepository.findByUsername(userRequestDTO.username()).isPresent()) {
            System.out.println("Username already exists: " + userRequestDTO.username());
            throw new FieldNotUniqueException("Username", userRequestDTO.username());
        }
        User user = new User();
        user.setUsername(userRequestDTO.username());
        user.setEmail(userRequestDTO.email());
        user.setUserPassword(passwordEncoder.encode(userRequestDTO.userPassword()));
        user.setRole(User.Role.USER);

        userRepository.save(user);
        System.out.println("User saved successfully with username: " + user.getUsername());
        return new UserDTO(user.getUserId(), user.getUsername(), "email@hidden.com");
    }


    public UserDTO findUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));

        return new UserDTO(user.getUserId(), user.getUsername(), "email@hidden.com");
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(user.getUserId(), user.getUsername(), "email@hidden.com"))
                .toList();
    }

    public void deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("User", id);
        }
    }

    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));

        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());

        userRepository.save(user);
        return userDTO;
    }

    public UserDTO findUserByUsername(String username) throws EntityNotFoundException {
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            return new UserDTO(user.getUserId(), user.getUsername(), user.getEmail());
        }else{
            throw new EntityNotFoundException("User with username", username);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Loading user by username: " + username);
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if (maybeUser.isPresent()) {
            System.out.println("User found: " + username);
            return maybeUser.get();
        } else {
            System.out.println("User not found: " + username);
            throw new EntityNotFoundException("User with username", username);
        }
    }
}