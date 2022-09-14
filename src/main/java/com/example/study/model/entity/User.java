package com.example.study.model.entity;

import com.example.study.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity  // == table
@ToString( exclude = {"orderGroup"} )
@EntityListeners( AuditingEntityListener.class )
@Builder // 생성자를 또 만들지 않아도 알아서 .build로 생성자를 만들어서 사용 가능
@Accessors( chain = true )
public class User {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Long id;

  private String account;

  private String password;

  @Enumerated( EnumType.STRING )
  private UserStatus status;

  private String email;

  private String phoneNumber;

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

  //User : OrderGroup 유저는 1명 order는 여러개 1 : N
  @OneToMany( fetch = FetchType.LAZY, mappedBy = "user" )
  private List<OrderGroup> orderGroupList;

  //User 1 : N
//  @OneToMany( fetch = FetchType.LAZY, mappedBy = "user" )  // <= OrderDeatil 의 User user
//  private List<OrderDetail> orderDetailList;
//  public User() {
//
//  }
}
