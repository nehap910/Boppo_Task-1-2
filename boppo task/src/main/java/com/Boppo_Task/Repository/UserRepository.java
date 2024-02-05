package com.Boppo_Task.Repository;
import com.Boppo_Task.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean findByName(String name);

    Boolean existsByName(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String username);

    User findByUsername(String username);

    User findByPassword(String password);

    @Query("SELECT u from User u ORDER BY u.id")
    Page<User> getAllUsersbyPage(Pageable pageable);
}
