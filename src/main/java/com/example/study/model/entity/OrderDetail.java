package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // db는 order_detail 자동적으로 연결 snake_case
//@ToString( exclude = {"user", "item"} )  //연관관계로 되어있는경우가 잇을경우 toString을 통해 제거를 해야됨
//@Table( name = "orderDetail ")
public class OrderDetail {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Long id;

  private String status;

  private LocalDateTime arrivalDate;

  private Integer quantity;

  private BigDecimal totalPrice;

  private LocalDateTime orderAt;

  private LocalDateTime createdAt;

  private String createdBy;

  private LocalDateTime updatedAt;

  private String updatedBy;
//  // N : 1  연관관계는 반듯이 객체 관계계
//  @ManyToOne
//  private User user;
//  //private Long userId < 이게 아님 연관관계일때는
//
//  @ManyToOne
//  private Item item;
//  //private Long itemId;
}
