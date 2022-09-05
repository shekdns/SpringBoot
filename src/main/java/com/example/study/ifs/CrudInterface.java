package com.example.study.ifs;

import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;

public interface CrudInterface<Request, Response> {

  Header<Response> create( Header<Request> request ); //todo request object 추가

  Header<Response> read( Long id );

  Header<Response> update( Header<Request> request );

  Header delete( Long id );
}
