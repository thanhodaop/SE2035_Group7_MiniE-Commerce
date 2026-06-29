package com.nguyenle.ecommerce.service;

import com.nguyenle.ecommerce.entities.Role;
import com.nguyenle.ecommerce.entities.User;
import com.nguyenle.ecommerce.repository.RoleRepository;
import com.nguyenle.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(User user) {
        // Mã hoá mật khẩu trước khi lưu
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Gán vai trò mặc định là CUSTOMER
        Role customerRole = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Chưa có role CUSTOMER trong DB"));
        user.setRole(customerRole);

        userRepository.save(user);
    }
}