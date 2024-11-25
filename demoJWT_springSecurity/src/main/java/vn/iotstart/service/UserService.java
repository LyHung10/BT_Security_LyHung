package vn.iotstart.service;

import org.springframework.stereotype.Service;

import vn.iotstart.entity.user;
import vn.iotstart.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection cho UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Phương thức lấy tất cả người dùng
    public List<user> allUsers() {
        List<user> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); // Thêm từng user vào danh sách
        return users;
    }
}
