package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners( AuditingEntityListener.class )
@Builder // 생성자를 또 만들지 않아도 알아서 .build로 생성자를 만들어서 사용 가능
@Accessors( chain = true )
public class AdminUser {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Long id;

  private String account;

  private String password;

  private String status;

  private String role;

  private LocalDateTime lastLoginAt;

  private LocalDateTime passwordUpdatedAt;

  private int loginFailCount;

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
}
