package com.pbj.blog.dto.article;

import com.pbj.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {

    private Long id;

    private String title;

    private String body;

    private String nickname;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public ArticleDTO(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.body = article.getBody();
        this.nickname = article.getMember().getNickname();
        this.regDate = article.getRegDate();
        this.updateDate = article.getUpdateDate();

    }


}