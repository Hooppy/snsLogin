package me.rollingpaper.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.rollingpaper.model.UserEntity;
import me.rollingpaper.repository.UserRepository;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService{
	
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> userEntityOptional = userRepository.findByUserId(username);
		UserEntity userEntity = userEntityOptional.orElse(null);
		
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
	
		return new User(userEntity.getUserId(), userEntity.getPwd(), authorities);
	}
}
