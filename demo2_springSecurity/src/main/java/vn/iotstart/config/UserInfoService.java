package vn.iotstart.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.iotstart.entity.UserInfo;
import vn.iotstart.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository repository;

    @Autowired
    public UserInfoService(UserInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm kiếm UserInfo trong cơ sở dữ liệu bằng username
        Optional<UserInfo> userInfo = repository.findByName(username);

        // Kiểm tra xem có tìm thấy người dùng không
        if (userInfo.isPresent()) {
            // Nếu có, trả về UserInfoUserDetails tương ứng
            return new UserInfoUserDetails(userInfo.get()); 
        } else {
            // Nếu không, ném exception UsernameNotFoundException
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
