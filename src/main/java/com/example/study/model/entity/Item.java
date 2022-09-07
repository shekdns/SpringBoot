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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString( exclude = {"orderDetailList", "partner"} )
@EntityListeners( AuditingEntityListener.class )
@Builder // 생성자를 또 만들지 않아도 알아서 .build로 생성자를 만들어서 사용 가능
@Accessors( chain = true )
public class Item {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Long id;

  private String status;

  private String name;

  private String title;

  private BigDecimal price;

  private String content;

  private String brandName;

  private LocalDateTime registeredAt;

  private LocalDateTime unregisteredAt;

  @CreatedBy
  private String createdBy;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedBy
  private String updatedBy;

  @LastModifiedDate
  private LocalDateTime updatedAt;
  // Item N : 1 Partner
  @ManyToOne
  private Partner partner;

  // Item 1 : N OrderDetail
  @OneToMany( fetch = FetchType.LAZY, mappedBy = "item" )
  private List<OrderDetail> orderDetailList;
  // 1 : N
  // Fetch Type 은 2개의 종료 LAZY = 지연로딩 / EAGER = 즉시로딩
  // 즉 LAZY = SELECT * FROM item WHERE id = ?
  //    EAGE = 은 조인 item_id = order_dtail.item_id , user_id = order_detail.user_id / where item.id = ?
  // EAGER = 1 : 1 추천
  // 이 외는 LAZY
//  @OneToMany( fetch = FetchType.LAZY, mappedBy = "item" )
//  private List<OrderDetail> orderDetailList;
}
