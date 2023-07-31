package com.example.casestudy_movieproject.service.user;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.repository.UserRepository;
import com.example.casestudy_movieproject.service.request.UserSaveRequest;
import com.example.casestudy_movieproject.service.user.request.SaveAvatarRequest;
import com.example.casestudy_movieproject.service.user.request.SaveUserInfoRequest;
import com.example.casestudy_movieproject.service.user.response.ShowUserInfoResponse;
import com.example.casestudy_movieproject.service.user.response.UserListResponse;
import com.example.casestudy_movieproject.util.AppUtils;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void UserCreate(UserSaveRequest request) {
        User newUser = AppUtils.mapper.map(request, User.class);
        userRepository.save(newUser);
    }

    public Optional<User> findByUsernameIgnoreCase(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();

    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }

    public Page<UserListResponse> finAllpaging(Pageable pageable) {
        return userRepository.findAll(pageable).map(user -> AppUtils.mapper.map(user, UserListResponse.class));
    }

    public ShowUserInfoResponse showInfoUserClient() {
        User user = getUser();
        if (user != null) {
            return AppUtils.mapper.map(getUser(), ShowUserInfoResponse.class);
        }
        return null;
    }


    public void changeAvatar(SaveAvatarRequest request) {
        System.out.println(request);
    }

    public void editInfo(SaveUserInfoRequest request) {
        User user = userRepository.findById(Integer.parseInt(request.getId()));
        if (!request.getPassword().equals(""))
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        userRepository.save(user);
    }
}