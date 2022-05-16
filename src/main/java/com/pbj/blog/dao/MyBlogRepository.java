package com.pbj.blog.dao;

import com.pbj.blog.domain.Member;
import com.pbj.blog.domain.MyBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBlogRepository  extends JpaRepository<MyBlog, Long> {

    boolean existsByMember(Member member);

}