package com.br.sarahaa.repositories;

import com.br.sarahaa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    List<User> findByJoiningDateAfter(LocalDateTime joiningDate);

    List<User> findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrUserNameIgnoreCase(String firstName, String lastName, String userName);

}
