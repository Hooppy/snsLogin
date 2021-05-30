package me.rollingpaper.security.social;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import me.rollingpaper.model.UserEntity;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	
	private String userId;
	private String name;
	private String socialToken;
	private String type;
	
	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String userId, String name, String socialToken, String type) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.userId = userId;
		this.name = name;
		this.socialToken = socialToken;
		this.type = type;
	}
	
	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
		if(registrationId.equals("naver")) {
			return ofNaver(registrationId, userNameAttributeName, attributes);
		}else if(registrationId.equals("kakao")){
			return ofKakao(registrationId, userNameAttributeName, attributes);
		}
		return ofGoogle(registrationId, userNameAttributeName, attributes);
	}
	
	private static OAuthAttributes ofGoogle(String registrationId, String userNameAttibuteName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
				.attributes(attributes)
				.nameAttributeKey(userNameAttibuteName)
				.type(registrationId)
				.userId((String) attributes.get("email"))
				.name((String) attributes.get("name"))
				.socialToken((String) attributes.get("sub"))
				.build();
	}
	
	private static OAuthAttributes ofKakao(String registrationId, String userNameAttibuteName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
				.attributes(attributes)
				.nameAttributeKey(userNameAttibuteName)
				.type("KAKAO")
				.userId((String) attributes.get("email"))
				.name((String) attributes.get("nickname"))
				.socialToken(String.valueOf(attributes.get("id")))
				.build();
	}
	
	private static OAuthAttributes ofNaver(String registrationId, String userNameAttibuteName, Map<String, Object> attributes) {
		Map<String, Object> response = (Map<String, Object>) attributes.get("response");
		
		return OAuthAttributes.builder()
				.attributes(response)
				.nameAttributeKey("id")
				.type("NAVER")
				.userId((String) response.get("email"))
				.name((String) response.get("nickname"))
				.socialToken(String.valueOf(response.get("id")))
				.build();
	}
	
	public UserEntity toEntity() {
		return UserEntity.builder()
				.userId(userId)
				.name(name)
				.socialToken(socialToken)
				.type(type)
				.build();
	}
}
