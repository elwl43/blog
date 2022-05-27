package com.pbj.blog.controller;

import com.pbj.blog.dto.myblog.MyBlogMainDTO;
import com.pbj.blog.dto.myblog.MyBlogSaveForm;
import com.pbj.blog.service.MyBlogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/b")
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

    @GetMapping("/{loginId}")
    public String showMyBlog(@PathVariable(name = "loginId") String loginId,
                             @RequestParam(value = "category", required = false, defaultValue = "all") String categoryName,
                             Model model){

        MyBlogMainDTO myBlogMainDto = myBlogService.getMyBlogMainDto(loginId);

        if(categoryName.equals("all")){
            model.addAttribute("articleList", myBlogService.getArticles());
        }else{
            model.addAttribute("articleList", myBlogService.getArticleByCategoryName(categoryName));
        }

        model.addAttribute("loginId", loginId);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("info", myBlogMainDto);

        return "usr/blog/blogMain";

    }

}