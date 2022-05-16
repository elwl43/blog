package com.pbj.blog.service;

import com.pbj.blog.dao.MyBlogRepository;
import com.pbj.blog.domain.Member;
import com.pbj.blog.domain.MyBlog;
import com.pbj.blog.dto.myblog.MyBlogSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyBlogService {

    private final MemberService memberService;
    private final MyBlogRepository myBlogRepository;

    public boolean ownBlog(String loginId){

        Member findMember = memberService.findByLoginId(loginId);

        boolean isExistMember = myBlogRepository.existsByMember(findMember);

        return isExistMember;

    }

    @Transactional
    public void createBlog(MyBlogSaveForm myBlogSaveForm, String loginId) {

        MyBlog myBlog = MyBlog.createMyBLog(myBlogSaveForm);
        Member findMember = memberService.findByLoginId(loginId);
        myBlog.setMember(findMember);

        myBlogRepository.save(myBlog);

    }
}