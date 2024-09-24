package com.example.demo.Repositories;

import com.example.demo.entities.Members;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MembersInterface extends JpaRepository<Members, Integer> {
    Members findByUsername(String username);

}
