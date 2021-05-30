package me.rollingpaper.security.social;

import java.io.Serializable;

import lombok.Getter;
import me.rollingpaper.model.UserEntity;

@Getter
public class SessionUser implements Serializable{
	private String userId;
	private String name;
	private String socialToken;
	private String type;
	
	public SessionUser(UserEntity userEntity) {
		this.userId = userEntity.getUserId();
		this.name = userEntity.getName();
		this.socialToken = userEntity.getSocialToken();
		this.type = userEntity.getType();
	}
}

