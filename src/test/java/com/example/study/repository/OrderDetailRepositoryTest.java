package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Test
  public void create() {
    OrderDetail orderDetail = new OrderDetail();

    orderDetail.setOrderAt( LocalDateTime.now() );
    //사용자 ID
    //orderDetail.setUserId(3L);
    //상품 ID
    //orderDetail.setItemId(1L);

    OrderDetail newOrderDetail = orderDetailRepository.save( orderDetail );
    Assert.assertNotNull( newOrderDetail );
  }
}
