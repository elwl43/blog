package com.pbj.blog.domain;

import groovy.lang.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Article {

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime regDate = LocalDateTime.now();

    private LocalDateTime updateDate = LocalDateTime.now();

    public static Article createArticle(String title, String body){

        Article article = new Article();

        article.title = title;
        article.body = body;

        return article;
    }

    public void setMember(Member member){
        this.member = member;
        member.getArticles().add(this);
    }

    public void modifyArticle( String title, String body ) {

        this.title = title;
        this.body = body;

    }
}