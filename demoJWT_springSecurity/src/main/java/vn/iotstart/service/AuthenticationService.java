package vn.iotstart.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.iotstart.entity.user;
import vn.iotstart.model.LoginUserModel;
import vn.iotstart.model.RegisterUserModel;
import vn.iotstart.repository.UserRepository;


@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // Constructor injection
    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    public user signup(RegisterUserModel input) {
        // Tạo mới đối tượng User từ thông tin đầu vào
        user user = new user();
        user.setFullName(input.getFullname());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword())); // Mã hóa mật khẩu

        // Lưu vào database
        return userRepository.save(user);}
    // Phương thức xác thực người dùng
    public user authenticate(LoginUserModel input) {
        // Thực hiện xác thực bằng AuthenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        
        // Tìm và trả về người dùng từ repository
        return userRepository.findbyEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    // Phương thức đăng ký người dùng
    
    
}
