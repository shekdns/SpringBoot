package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import jdk.vm.ci.meta.Local;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Test
  public void create() {
    OrderDetail orderDetail = new OrderDetail();

    orderDetail.setStatus( "WAITING" );
    orderDetail.setArrivalDate( LocalDateTime.now().plusDays(2) );
    orderDetail.setQuantity( 1 );
    orderDetail.setTotalPrice(BigDecimal.valueOf(900000) );
    orderDetail.setCreatedAt( LocalDateTime.now() );
    orderDetail.setCreatedBy( "AdminServer" );
    //사용자 ID
    orderDetail.setOrderGroupId(1L); // 어떠한 장바구니에
    //상품 ID
    orderDetail.setItemId(1L);  // 어떠한 상품

    OrderDetail newOrderDetail = orderDetailRepository.save( orderDetail );
    Assert.assertNotNull( newOrderDetail );
  }
}
