package me.rollingpaper.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="member")
public class UserEntity extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	private String userId;
	private String name;
	private String gender;
	private String phone;
	private String ci;
	private String pwd;
	private String socialToken;
	private String type;
	
	@Builder
	public UserEntity(String userId, String name, String gender, String phone,
				      String ci, String pwd, String socialToken, String type) {
		this.userId = userId;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.ci = ci;
		this.pwd = pwd;
		this.socialToken = socialToken;
		this.type = type;
	}
	
	/*
	 * public UserEntity update(String socialName, String socialPicture) {
	 * this.socialName = socialName; this.socialPicture = socialPicture;
	 * 
	 * return this; }
	 */
}
