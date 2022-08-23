package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UserRepositoryTest extends StudyApplicationTests {
  // Dependency Injection ( DI ) 의존성
  @Autowired
  private UserRepository userRepository;

  @Test
  public void create() {
    // String sql = insert into user ( ~~~~ ) value ( ~~~~ );
    User user = new User(); // 싱글톤패턴

    user.setACCOUNT( "TestUser03" );
    user.setEMAIL( "TestUser03@gmail.com" );
    user.setPHONE_NUMBER( "010-3333-3333" );
    user.setCREATED_BY( "TestUser03" );
    user.setCREATED_DATE( LocalDateTime.now() );

    User newUser = userRepository.save( user );
    System.out.println( "newUser = " + newUser );
  }

  public void read() {

  }

  public void update() {

  }

  public void delete() {

  }
}
