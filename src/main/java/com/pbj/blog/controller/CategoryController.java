package com.pbj.blog.controller;

import com.pbj.blog.domain.Member;
import com.pbj.blog.dto.category.CategorySaveForm;
import com.pbj.blog.service.CategoryService;
import com.pbj.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final MemberService memberService;

    @GetMapping("/categories/create")
    public String showCreate(){
        return "usr/category/create";
    }

    @PostMapping("/categories/create")
    public String doCreate(CategorySaveForm categorySaveForm, Principal principal){

        Member findMember = memberService.findByLoginId(principal.getName());

        categoryService.save(categorySaveForm, findMember);


        return "redirect:/";
    }

}