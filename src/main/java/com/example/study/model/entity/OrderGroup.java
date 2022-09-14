package com.example.study.model.entity;

import com.example.study.model.enumclass.OrderTypeStatus;
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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString( exclude = {"user", "orderGroup"} )
@EntityListeners( AuditingEntityListener.class )
@Builder // 생성자를 또 만들지 않아도 알아서 .build로 생성자를 만들어서 사용 가능
@Accessors( chain = true )
public class OrderGroup {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY ) //mysql
  private Long id;

  private String status;

  @Enumerated( EnumType.STRING )
  private OrderTypeStatus orderType; // 주문의 형태 - 일괄 / 개별

  private String revAddress;

  private String revName;

  private String paymentType; // 카드 결재  or 현금

  private BigDecimal totalPrice;

  private Integer totalQuantity;

  private LocalDateTime orderAt;

  private LocalDateTime arrivalDate;

  @CreatedBy
  private String createdBy;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedBy
  private String updatedBy;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  //Ordergroup N : 1 User
  @ManyToOne
  private User user;

  //OderGroup 1 : N OrderDetail
  @OneToMany( fetch = FetchType.LAZY, mappedBy = "orderGroup" )
  private List<OrderDetail> orderDetailList;
}
