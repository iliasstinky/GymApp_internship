package com.example.demo.Repositories;

import com.example.demo.entities.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionInterface extends JpaRepository<Subscriptions , Long>{

}
