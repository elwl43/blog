package com.pbj.blog.controller;

import com.pbj.blog.dto.myblog.MyBlogSaveForm;
import com.pbj.blog.service.MyBlogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MyBlogController {

    private final MyBlogService myBlogService;

    @GetMapping("/{loginId}/blog")
    private String showMyBlog(@PathVariable( name = "loginId") String loginId, Principal principal){

        if(!principal.getName().equals(loginId)){
            return "redirect:/";
        }

        // myblog가 없을 때
        if(!myBlogService.ownBlog(loginId)){
            return "usr/blog/createBlog";
        }

        return "usr/member/myblog";
    }

    @PostMapping("/{loginId}/blog")
    private String createBlog(
            @PathVariable(name = "loginId") String loginId, @Validated MyBlogSaveForm myBlogSaveForm, BindingResult bindingResult, Principal principal){

        if(!principal.getName().equals(loginId)){
            return "redirect:/";
        }

        if(myBlogService.ownBlog(loginId)){
            return "redirect:/";
        }

        myBlogService.createBlog(myBlogSaveForm, loginId);

        return "redirect:/";

    }

}