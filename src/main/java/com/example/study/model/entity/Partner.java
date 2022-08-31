package com.example.study.model.entity;

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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString( exclude = {"itemList", "category"} )
@EntityListeners( AuditingEntityListener.class )
@Builder // 생성자를 또 만들지 않아도 알아서 .build로 생성자를 만들어서 사용 가능
@Accessors( chain = true )
public class Partner {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )  //mysql
  private Long id;

  private String name;

  private String status;

  private String address;

  private String callCenter;

  private String partnerNumber;

  private String businessNumber;

  private String ceoName;

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

  // Partner N : 1 Category
  @ManyToOne
  private Category category;

  // Partner 1 : N Item
  @OneToMany( fetch = FetchType.LAZY, mappedBy = "partner" )
  private List<Item> itemList;
}
