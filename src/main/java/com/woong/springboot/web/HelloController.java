package com.woong.springboot.web;

import com.woong.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러 자체가 JSON을 return하도록 선언
public class HelloController {

    @GetMapping("/hello") //GET 요청에 대한 라우트
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) { //넘겨받은 파라미터를 각각 변수에 할당
        return new HelloResponseDto(name, amount);
    }
}
