package com.woong.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)  //SpringRunner 실행자를 사용 (JUnit과 SpringBoot를 연결)
@WebMvcTest(controllers = HelloController.class)  //Spring Mvc에 대한 어노테이션
public class HelloControllerTest {

    @Autowired  //Bean 주입
    private MockMvc mvc;  //웹 api 테스트 용. 스프링 mvc 테스트의 시작점

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))  //Mockmvc객체를 통해 /hello로 GET 요청
                .andExpect(status().isOk())  //결과검증. HTTP Status가 200인지 아닌지 검증
                .andExpect(content().string(hello));  //결과검증. 응답 본문이 hello인지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                .param("name", name)  //요청할 파라미터 값 세팅 (string으로만 가능)
                .param("amount", String.valueOf(amount))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))  //Json 형태에 대한 데이터 조회
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
