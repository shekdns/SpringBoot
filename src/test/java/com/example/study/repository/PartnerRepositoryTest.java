package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Partner;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class PartnerRepositoryTest extends StudyApplicationTests {

  @Autowired
  private PartnerRepository partnerRepository;

  @Test
  public void create() {
    String name       = "Partner01";
    String status     = "REGISTERED";
    String address    = "서울시 강남구";
    String callCenter = "070-1111-2222";
    String partnerNumber  = "010-1111-2222";
    String businessNumber = "123456780123";
    String ceoName = "홍길동";
    LocalDateTime registeredAt = LocalDateTime.now();
    LocalDateTime createdAt    = LocalDateTime.now();
    String createdBy = "AdminServer";
    Long categoryId  = 1L;

    Partner partner = new Partner();
    partner.setName( name );
    partner.setStatus( status );
    partner.setAddress( address );
    partner.setCallCenter( callCenter );
    partner.setPartnerNumber( partnerNumber );
    partner.setBusinessNumber( businessNumber );
    partner.setCeoName( ceoName );
    partner.setRegisteredAt( registeredAt );
    partner.setCreatedAt( createdAt );
    partner.setCreatedBy( createdBy );
    //partner.setCategoryId( categoryId ); Long -> Category

    Partner newParter = partnerRepository.save( partner );
    Assert.assertNotNull( newParter );
    Assert.assertEquals( newParter.getName(), name );
  }

  public void read() {

  }
}
