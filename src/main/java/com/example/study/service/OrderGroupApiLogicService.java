package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.repository.OrderGroupRepository;
import com.example.study.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

//  @Autowired
//  private OrderGroupRepository orderGroupRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {

    OrderGroupApiRequest body = request.getData();

    OrderGroup orderGroup = OrderGroup.builder()
            .status( body.getStatus() )
            .orderType( body.getOrderType() )
            .revAddress( body.getRevAddress() )
            .revName( body.getRevName() )
            .paymentType( body.getPaymentType() )
            .totalPrice( body.getTotalPrice() )
            .totalQuantity( body.getTotalQuantity() )
            .orderAt(LocalDateTime.now() )
            .user(  userRepository.getReferenceById(body.getUserId() ) )
            .build();

    OrderGroup newOrderGroup = baseRepository.save( orderGroup );

    return response( newOrderGroup );
  }

  @Override
  public Header<OrderGroupApiResponse> read(Long id) {
// 1.
//    orderGroupRepository.findById( id )
//            .map( this::response )
//            .orElseGet
// 2.
//    orderGroupRepository.findById( id )
//            .map( orderGroup -> response(orderGroup) )
//            .orElseGet(() -> Header.ERROR( "데이터 없음") );
// 3.
     return baseRepository.findById( id )
             .map( orderGroup -> response(orderGroup))
             .orElseGet(() -> Header.ERROR( "데이터 없음") );
  }

  @Override
  public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {

    OrderGroupApiRequest body = request.getData();

    return baseRepository.findById( body.getId() )
            .map( orderGroup -> {
              orderGroup
                      .setStatus( body.getStatus() )
                      .setOrderType(body.getOrderType() )
                      .setRevName(body.getRevName())
                      .setRevAddress(body.getRevAddress())
                      .setTotalPrice( body.getTotalPrice() )
                      .setTotalQuantity( body.getTotalQuantity() )
                      .setOrderAt( body.getOrderAt() )
                      .setArrivalDate( body.getArrivalDate() )
                      .setUser( userRepository.getReferenceById( body.getUserId() ) );

              return orderGroup;
            })
            .map( newOrderGroup -> baseRepository.save( newOrderGroup ) )
            .map( changedOrderGroup -> response( changedOrderGroup))
            .orElseGet(() -> Header.ERROR( "데이터 없음") );
  }

  @Override
  public Header delete(Long id) {

    return baseRepository.findById( id )
            .map( orderGroup -> {
              baseRepository.delete( orderGroup );
              return Header.OK();
            })
            .orElseGet(() -> Header.ERROR("데이터 없음"));
  }

  private Header<OrderGroupApiResponse> response( OrderGroup orderGroup ) {

    OrderGroupApiResponse body = OrderGroupApiResponse.builder()
            .id(orderGroup.getId())
            .status(orderGroup.getStatus())
            .orderType(orderGroup.getOrderType())
            .revAddress(orderGroup.getRevAddress())
            .revName(orderGroup.getRevName())
            .paymentType(orderGroup.getPaymentType())
            .totalPrice(orderGroup.getTotalPrice())
            .totalQuantity(orderGroup.getTotalQuantity())
            .orderAt(orderGroup.getOrderAt())
            .arrivalDate(orderGroup.getArrivalDate())
            .userId(orderGroup.getUser().getId())
            .build();

    return Header.OK(body);
  }
}
