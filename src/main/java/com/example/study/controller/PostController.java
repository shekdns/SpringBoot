package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api" )
public class PostController {

  // Post 는 HTML의 FORM태그
  // ajax 검색
  // 검색 파라미터가 많을 때

  //http post body -> data
  //json 형태, xml, multipart-form 파일, text-plain 형태
  //@RequestMapping( method = RequestMethod.POST, path = "/postMethod" )
  @PostMapping( value = "/postMethod" ) //  produces = { "application/json"} 받을 형태를 지정 produces ex json
  public SearchParam postMethod(@RequestBody SearchParam searchParam ) {
    return searchParam;
  }

  //아래의 두개는 업데이트 용으로 사용은하는데 잘 사용은 안함
  @PutMapping( "/putMethod" )
  public void put() {

  }

  @PatchMapping( "/patchMethod" )
  public void patch() {

  }

  //delete(삭제용)
  @DeleteMapping( "/deleteMethod" )
  public void delete() {

  }
}
