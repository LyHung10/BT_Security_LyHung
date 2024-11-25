package vn.iotstart.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.iotstart.entity.UserInfo;
import vn.iotstart.repository.UserInfoRepository;


@Service
public record UserService(UserInfoRepository repository, PasswordEncoder passwordEndcoder) {
	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEndcoder.encode(userInfo.getPassword()));
		repository.save(userInfo);
		return "Thêm user thanh công!";

		}
}