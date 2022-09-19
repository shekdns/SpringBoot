package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.enumclass.ItemStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {

  @Autowired
  private ItemRepository itemRepository;

  @Test
  public void create() {
    Item item = new Item();
    LocalDateTime registeredAt = LocalDateTime.now();
    LocalDateTime createdAt    = LocalDateTime.now();
    String createdBy = "Partner01";
    ItemStatus status = ItemStatus.UNREGISTERED;

    item.setStatus( status );
    item.setName( "삼성 노트북" );
    item.setTitle( "삼성 노트북 A100" );
    item.setContent( "2019년형 노트북 입니다." );
    item.setPrice( BigDecimal.valueOf(900000) );
    item.setBrandName( "삼성" );
    item.setRegisteredAt( registeredAt );
    item.setCreatedAt( createdAt );
    item.setCreatedBy( createdBy );
    //item.setPartnerId( 1L );  Long -> Partner

    Item newItem = itemRepository.save( item  );
    Assert.assertNotNull( newItem );

//    Item item = new Item();
//    item.setName( "노트북" );
//    item.setPrice( 100000 );
//    item.setContent( "삼성 노트북" );
//
//    Item newItem = itemRepository.save( item );
//
//    Assert.assertNotNull( newItem );
  }

  @Test
  public void read() {
    Long id = 1L;

    Optional<Item> item = itemRepository.findById( id );

    Assert.assertTrue( item.isPresent() );

//    item.ifPresent( i -> {
//      System.out.println( i );
//    });
  }
}
