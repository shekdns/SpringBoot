package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.AdminUser;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AdminUserRepositoryTest extends StudyApplicationTests {

  @Autowired
  private AdminUserRepository adminUserRepository;

  @Test
  public void create() {
    AdminUser adminUser = new AdminUser();
    adminUser.setAccount( "AdminUser07" );
    adminUser.setPassword( "AdminUser07" );
    adminUser.setStatus( "REGISTERED" );
    adminUser.setRole( "PARTNER" );
    //adminUser.setCreatedAt( createdAt );
    //adminUser.setCreatedBy( "AdminServer" );

    AdminUser newAdminUser = adminUserRepository.save( adminUser );
    Assert.assertNotNull( newAdminUser );

    newAdminUser.setAccount( "CHANGE" );
    adminUserRepository.save( newAdminUser );
  }
}
