package com.woong.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After  //단위 테스트가 끝날때마다 실행됨
    public void cleanup() {
        postsRepository.deleteAll();
    }  //H2 테스트 DB에 데이터가 그대로 남기 때문에 비워줌

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("test@mail.com")
        .build());  //insert/update 쿼리를 실행. id가 있다면 update 없으면 insert

        //when
        List<Posts> postsList = postsRepository.findAll();  //posts 테이블에 있는 모든 데이터 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}
