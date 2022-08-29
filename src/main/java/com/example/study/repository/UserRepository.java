package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findFirstByPhoneNumberOrderByIDDesc( String phoneNumber );

  //  //쿼리 메소드 처음 공부
//  //select * from user where account = ?
//  Optional<User> findByACCOUNT( String account );
//  //select * from user where email = ?
//  Optional<User> findByEMAIL( String email );
//
//  Optional<User> findByACCOUNTAndEMAIL( String account, String email );
}
