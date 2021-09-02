package com.vnpt.eoffice.repository;

import com.vnpt.eoffice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByUsername(String username);

    @Query("UPDATE User u set u.password = ?1 where u.username= ?2 ")
    void resetPass(String pass, String userName);

    @Query("FROM User u WHERE u.email = ?1 or u.phoneNumber = ?1")
    Optional<User> findByPhoneNumberOrEmail(String emailOrPhone);

    Optional<User> findByIdAndStatus(String userId, Integer status);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password= ?1 WHERE u.phoneNumber =?2 OR u.email = ?2")
    void resetPassword(String password, String username);

}
