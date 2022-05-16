package com.pbj.blog.domain;

import com.pbj.blog.dto.reply.ReplyModifyForm;
import com.pbj.blog.dto.reply.ReplySaveForm;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    public static Reply createReply(ReplySaveForm replySaveForm){

        Reply reply = new Reply();

        reply.body = replySaveForm.getBody();

        return reply;
    }

    public void modifyReply(ReplyModifyForm replyModifyForm){

        this.body = replyModifyForm.getUpdateValue();

    }

    public void setMember(Member member){
        member.getReplies().add(this);
        this.member = member;
    }

    public void setArticle(Article article){
        article.getReplies().add(this);
        this.article = article;
    }

}
