
package com.example.casestudy_movieproject.service.request;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.model.enums.ERole;
import com.example.casestudy_movieproject.repository.UserRepository;
import com.example.casestudy_movieproject.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request){
        var user = AppUtils.mapper.map(request, User.class);
        user.setRole(ERole.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean checkUsernameOrEmail(RegisterRequest request, BindingResult result){
        boolean check = false;
        if(userRepository.existsByUsernameIgnoreCase(request.getUsername())){
            result.reject("username", null,
                    "đã có một tài khoảng đăng ký trùng với tên người dùng .");
            check = true;
        }

        if(userRepository.existsByEmailIgnoreCase(request.getEmail())){
            result.reject("email", null,
                    "đã có một tài khoảng đăng ký với cùng một mail ");
            check = true;
        }
        return check;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("tài khoảng không tồn tại") );
        var role = new ArrayList<SimpleGrantedAuthority>();
        role.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), role);
    }

}

