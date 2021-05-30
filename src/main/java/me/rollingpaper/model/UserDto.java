package me.rollingpaper.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements UserDetails{
	private String userId;
	private String name;
	private String gender;
	private String phone;
	private String ci;
	private String pwd;
	private String socialToken;
	private String type;
	
	@Builder
	public UserDto(String userId, String name, String gender, String phone, String ci, String pwd, String socialToken, String type) {
		this.userId = userId;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.ci = ci;
		this.pwd = pwd;
		this.socialToken = socialToken;
		this.type = type;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.pwd;
	}

	@Override
	public String getUsername() {
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
