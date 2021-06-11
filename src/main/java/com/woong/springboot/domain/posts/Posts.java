package com.woong.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter  //클래스 내의 모든 필드에 Getter 메소드 자동생성. Setter 일부러 생성하지 않으며, Builder 통해서 생성자를 사용한 값 설정.
@NoArgsConstructor  //기본 생성자 자동 추가
@Entity  //테이블과 링크될 클래스임을 표시. 클래스의 카멜 케이스명을 언더스코어 네이밍 테이블명으로 매칭해줌
public class Posts {

    @Id  //PK값 임을 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //PK 생성 규칙. (IDENTITY = auto increment)
    private Long id;

    @Column(length = 500, nullable = false)  //필드가 컬럼임을 표시. 컬럼 옵션을 설정하기 위하여 명시
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder  //해당 클래스의 빌더 패턴 클래스를 생성. 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
