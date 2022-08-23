package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

//  public User() {
//
//  }
}
