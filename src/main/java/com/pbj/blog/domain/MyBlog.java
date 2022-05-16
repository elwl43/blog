package com.pbj.blog.domain;

import com.pbj.blog.dto.myblog.MyBlogSaveForm;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class MyBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String blogName;

    private String blogDesc;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    public static MyBlog createMyBLog(MyBlogSaveForm myBlogSaveForm){

        MyBlog myBlog = new MyBlog();
        myBlog.blogName = myBlogSaveForm.getBlogName();
        myBlog.blogDesc = myBlogSaveForm.getBlogDesc();

        return myBlog;

    }

    public void setMember(Member member){
        this.member = member;
        member.setMyBlog(this);
    }

}
