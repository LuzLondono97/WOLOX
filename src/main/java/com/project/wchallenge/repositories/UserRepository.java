package com.project.wchallenge.repositories;

import com.project.wchallenge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
