package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Long id;

  private String status;

  private String name;

  private String title;

  private Integer price;

  private String content;

  private String brandName;

  private LocalDateTime registeredAt;

  private LocalDateTime unregisteredAt;

  private LocalDateTime createdAt;

  private String createdBy;

  private LocalDateTime updatedAt;

  private String updatedBy;

  private Long partnerId;
  // 1 : N
  // Fetch Type 은 2개의 종료 LAZY = 지연로딩 / EAGER = 즉시로딩
  // 즉 LAZY = SELECT * FROM item WHERE id = ?
  //    EAGE = 은 조인 item_id = order_dtail.item_id , user_id = order_detail.user_id / where item.id = ?
  // EAGER = 1 : 1 추천
  // 이 외는 LAZY
//  @OneToMany( fetch = FetchType.LAZY, mappedBy = "item" )
//  private List<OrderDetail> orderDetailList;
}
