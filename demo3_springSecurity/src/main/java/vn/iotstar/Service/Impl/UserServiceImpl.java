package vn.iotstar.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.iotstar.Repository.UserRepository;
import vn.iotstar.entity.Users;
@Service
public class UserServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.getUserByUsername(username);
		
		if (user == null)
		{
			throw new UsernameNotFoundException("Could not find user");
		}
		
		return new MyUserService(user);
	}
	
}
