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

  private String ACCOUNT;

  private String EMAIL;

  private String PHONE_NUMBER;

  private LocalDateTime CREATED_DATE;

  private String CREATED_BY;

  private LocalDateTime UPDATED_DATE;

  private String UPDATED_BY;

  //User 1 : N
  @OneToMany( fetch = FetchType.LAZY, mappedBy = "user" )  // <= OrderDeatil 의 User user
  private List<OrderDetail> orderDetailList;
//  public User() {
//
//  }
}
