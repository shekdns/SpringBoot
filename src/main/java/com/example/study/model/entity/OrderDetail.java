package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // db는 order_detail 자동적으로 연결 snake_case
//@ToString( exclude = {"user", "item"} )  //연관관계로 되어있는경우가 잇을경우 toString을 통해 제거를 해야됨
//@Table( name = "orderDetail ")
@ToString( exclude = {"orderGroup", "item"} )
@EntityListeners( AuditingEntityListener.class )
@Builder // 생성자를 또 만들지 않아도 알아서 .build로 생성자를 만들어서 사용 가능
@Accessors( chain = true )
public class OrderDetail {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Long id;

  private String status;

  private LocalDateTime arrivalDate;

  private Integer quantity;

  private BigDecimal totalPrice;

  @CreatedBy
  private String createdBy;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedBy
  private String updatedBy;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  // OrderDetail N : 1 Item
  @ManyToOne
  private Item item;

  // OrderDetail N : 1 Ordergroup
  @ManyToOne
  private OrderGroup orderGroup;
//  // N : 1  연관관계는 반듯이 객체 관계계
//  @ManyToOne
//  private User user;
//  //private Long userId < 이게 아님 연관관계일때는
//
//  @ManyToOne
//  private Item item;
//  //private Long itemId;
}
