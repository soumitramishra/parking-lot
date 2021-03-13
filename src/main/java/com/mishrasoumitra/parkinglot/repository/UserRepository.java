package com.mishrasoumitra.parkinglot.repository;

import com.mishrasoumitra.parkinglot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
