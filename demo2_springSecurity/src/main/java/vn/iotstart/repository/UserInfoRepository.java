package vn.iotstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstart.entity.UserInfo;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    // Phương thức tìm UserInfo bằng tên người dùng
    Optional<UserInfo> findByName(String username);
}
