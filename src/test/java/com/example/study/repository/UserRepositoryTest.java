package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {
  // Dependency Injection ( DI ) 의존성
  @Autowired
  private UserRepository userRepository;

  @Test
  public void create() {
    String account  = "Test04";
    String password = "Test04";
    String status   = "REGISTERED";
    String email    = "Test03@gmail.com";
    String phoneNumber = "010-1111-3333";
    LocalDateTime registeredAt = LocalDateTime.now();
    LocalDateTime createdAt    = LocalDateTime.now();
    String createdBy = "AdminServer";

    User user = new User();
    user.setAccount( account );
    user.setPassword( password );
    user.setStatus( status );
    user.setEmail( email );
    user.setPhoneNumber( phoneNumber );
    user.setRegisteredAt( registeredAt );
    //user.setCreatedAt( createdAt );
    //user.setCreatedBy( createdBy );

    //객체 생성을 할때 빌더
    User u = User.builder()
            .account( account )
            .password( password )
            .status( status )
            .build();

    User newUser = userRepository.save( user );
    Assert.assertNotNull( newUser );

//    // String sql = insert into user ( ~~~~ ) value ( ~~~~ );
//    User user = new User(); // 싱글톤패턴
//
//    user.setACCOUNT( "TestUser03" );
//    user.setEMAIL( "TestUser03@gmail.com" );
//    user.setPHONE_NUMBER( "010-3333-3333" );
//    user.setCREATED_BY( "TestUser03" );
//    user.setCREATED_DATE( LocalDateTime.now() );
//
//    User newUser = userRepository.save( user );
//    System.out.println( "newUser = " + newUser );
  }

  @Test
  @Transactional
  public void read() {

    User user = userRepository.findFirstByPhoneNumberOrderByIDDesc( "010-1111-2222" );

    //Accessors.chain
    //User uu = new User().setAccount().setEmail().setPassword(); 체인패턴  업데이트를 칠때

    if( user != null ) {
      user.getOrderGroupList().stream().forEach( orderGroup -> {
        System.out.println( "====================주문묶음====================" );
        System.out.println( orderGroup.getTotalPrice() );
        System.out.println( orderGroup.getRevAddress() );
        System.out.println( orderGroup.getRevName() );

        System.out.println( "====================주문상세====================" );

        orderGroup.getOrderDetailList().forEach( orderDetail -> {
          System.out.println( orderDetail.getStatus() );
          System.out.println( orderDetail.getArrivalDate() );
          System.out.println( "파트너사 이름 : " + orderDetail.getItem().getPartner().getName() );
          System.out.println( "파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle() );
          System.out.println( "주문 상품 : " + orderDetail.getItem().getName() );
          System.out.println( "콜센터    : " + orderDetail.getItem().getPartner().getCallCenter() );
        });

      });
    }

    Assert.assertNotNull( user );

    //select * from user where id = ?
    //Optional<User> user = userRepository.findById(3);
//    Optional<User> user = userRepository.findByACCOUNT( "TestUser03" );
//
//    user.ifPresent( selectUser -> {
//      selectUser.getOrderDetailList().stream().forEach( detail -> {
//        //System.out.println( detail.getItemId() );
//        Item item = detail.getItem();
//        System.out.println( item );
//      });
//      System.out.println( "user  : " + selectUser );
//      System.out.println( "emial : " + selectUser.getEMAIL() );
//    });
  }

//  @Test
//  public void update() {
//    Optional<User> user = userRepository.findById(2);
//
//    user.ifPresent( selectUser -> {
//      selectUser.setACCOUNT( "PPPP" );
//      selectUser.setUPDATED_DATE( LocalDateTime.now() );
//      selectUser.setUPDATED_BY( "update method()" );
//
//      userRepository.save( selectUser );
//    });
//  }

  @Test
  public void  delete() {
//    Optional<User> user = userRepository.findById(1);
//
//    Assert.assertTrue( user.isPresent() ); //true  -> DB에 있는 데이터가 잇는지 판별 잇다면 true / false면 에러
//
//    user.ifPresent( selectUser -> {
//      userRepository.delete( selectUser );
//    });
//
//    Optional<User> deleteUser = userRepository.findById(2);
//
//    Assert.assertFalse( deleteUser.isPresent() ); //false DB에 있는 데이터가 없는지 판별 없다면 false / true면 에러

//    if( deleteUser.isPresent() ) {
//      System.out.println( "데이터 존재 : " + deleteUser.get() );
//    } else {
//      System.out.println( "데이터 삭제" );
//    }
  }
}
