package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.repository.UserRepository;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OrderGroupApiLogicService orderGroupApiLogicService;

  @Autowired
  private ItemApiLogicService itemApiLogicService;

  // 1. request data
  // 2. user 생성
  // 3. 생성된 데이터 -> UserApiResponse return
  @Override
  public Header<UserApiResponse> create(Header<UserApiRequest> request) {

    // 1. request data
    UserApiRequest userApiRequest = request.getData();

    // 2. user 생성
    User user = User.builder()
            .account( userApiRequest.getAccount() )
            .password( userApiRequest.getPassword() )
            .status( UserStatus.REGISTERED )
            .phoneNumber( userApiRequest.getPhoneNumber() )
            .email( userApiRequest.getEmail() )
            .registeredAt(LocalDateTime.now() )
            .build();

    User newUser = userRepository.save( user );

    // 3. 생성된 데이터 -> userApiResponse return

    return Header.OK( response( newUser ) );
  }

  @Override
  public Header<UserApiResponse> read(Long id) {

    // id -> repository getOne, getById
    Optional<User> optional = userRepository.findById( id );

    // user -> userApiResponse return
    return optional.map( user ->
      response( user ) )
            .map( userApiResponse -> Header.OK( userApiResponse ) )
            //.map( Header::OK )
            .orElseGet(()->Header.ERROR("데이터 없음"));
  }

  @Override
  public Header<UserApiResponse> update(Header<UserApiRequest> request) {

    // 1.data
    UserApiRequest userApiRequest = request.getData();

    // 2.id -> user
    Optional<User> optional = userRepository.findById( userApiRequest.getId() );

    return optional.map( user -> {
      // 3.update
      user.setAccount( userApiRequest.getAccount() )
              .setPassword( userApiRequest.getPassword() )
              .setStatus( userApiRequest.getStatus() )
              .setPhoneNumber( userApiRequest.getPhoneNumber() )
              .setEmail( userApiRequest.getEmail() )
              .setRegisteredAt( userApiRequest.getRegisteredAt() )
              .setUnregisteredAt( userApiRequest.getUnregisteredAt() );

      return user;
      // 4. userApiResponse
    }).map( user -> userRepository.save(user))
            .map( updateUser -> response( updateUser ))
            .map( userApiResponse -> Header.OK( userApiResponse ) )
            .orElseGet(() -> Header.ERROR("데이터 없음"));
  }

  @Override
  public Header delete(Long id) {
    // id -> repository -> user
    Optional<User> optional = userRepository.findById( id );

    // repository -> delete
    return optional.map( user -> {
      userRepository.delete( user );
      return Header.OK();
    }).orElseGet( () -> Header.ERROR( "데이터 없음" ));
  }

//  private Header<UserApiResponse> response( User user ) {
//    // user -> userApiResponse -> return
//    UserApiResponse userApiResponse = UserApiResponse.builder()
//            .id( user.getId() )
//            .account( user.getAccount() )
//            .password( user.getPassword() )
//            .phoneNumber( user.getPhoneNumber() )
//            .email( user.getEmail() )
//            .status( user.getStatus() )
//            .registeredAt( user.getRegisteredAt() )
//            .unregisteredAt( user.getUnregisteredAt() )
//            .build();
//
//    // Header + data return
//    return Header.OK( userApiResponse );
//  }

  private UserApiResponse response( User user ) {
    // user -> userApiResponse -> return
    UserApiResponse userApiResponse = UserApiResponse.builder()
            .id( user.getId() )
            .account( user.getAccount() )
            .password( user.getPassword() )
            .phoneNumber( user.getPhoneNumber() )
            .email( user.getEmail() )
            .status( user.getStatus() )
            .registeredAt( user.getRegisteredAt() )
            .unregisteredAt( user.getUnregisteredAt() )
            .build();

    // Header + data return
    return userApiResponse;
  }

  public Header<List<UserApiResponse>> search(Pageable pageable) {

    Page<User> users = userRepository.findAll( pageable );

    List<UserApiResponse> userApiResponseList = users.stream()
            .map( user -> response(user ) )
            .collect(Collectors.toList() );

    Pagination pagination = Pagination.builder()
            .totalPages( users.getTotalPages() )
            .totalElements( users.getTotalElements() )
            .currentPage( users.getNumber() )
            .currentElements( users.getNumberOfElements() )
            .build();

    return Header.OK( userApiResponseList, pagination );
  }

  public Header<UserOrderInfoApiResponse> orderInfo(Long id) {

    // user
    User user = userRepository.getReferenceById( id );
    UserApiResponse userApiResponse = response( user );

    // orderGroup
    List<OrderGroup> orderGroupList = user.getOrderGroupList();
    List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
            .map( orderGroup -> {
              OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response( orderGroup ).getData();

              List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                      .map( detail -> detail.getItem() )
                      .map( item -> itemApiLogicService.response( item ).getData() )
                      .collect(Collectors.toList());

              orderGroupApiResponse.setItemApiResponseList( itemApiResponseList );
              return orderGroupApiResponse;
            })
            .collect( Collectors.toList() );

    userApiResponse.setOrderGroupApiResponsesList( orderGroupApiResponseList );

    UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
            .userApiResponse( userApiResponse )
            .build();

    return Header.OK( userOrderInfoApiResponse );
  }
}
