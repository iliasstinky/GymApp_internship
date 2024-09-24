package com.example.demo.Repositories;

import com.example.demo.entities.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionInterface extends JpaRepository<UserSession, Integer> {



}
