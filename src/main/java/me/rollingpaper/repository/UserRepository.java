package me.rollingpaper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.rollingpaper.model.UserEntity;

public interface UserRepository extends JpaRepository <UserEntity, Long> {
	Optional<UserEntity> findByUserId(String username);
	int countBySocialToken(String socialToken);
}
