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
@Entity  // == table
public class User {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private int ID;

  private String account;

  private String password;

  private String status;

  private String email;

  private String phoneNumber;

  private LocalDateTime registeredAt;

  private LocalDateTime unregisteredAt;

  private LocalDateTime createdAt;

  private String createdBy;

  private LocalDateTime updatedAt;

  private String updatedBy;

  //User 1 : N
//  @OneToMany( fetch = FetchType.LAZY, mappedBy = "user" )  // <= OrderDeatil 의 User user
//  private List<OrderDetail> orderDetailList;
//  public User() {
//
//  }
}
