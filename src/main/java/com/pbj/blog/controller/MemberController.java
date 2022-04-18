package com.pbj.blog.controller;

import com.pbj.blog.dto.MemberSaveForm;
import com.pbj.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // html을 보여주는 url show
    // 실제 기능이 실행될 url do

    // localhost:8085/members/join
    @GetMapping("members/join")
    public String showJoin(){
        return "/usr/member/join";
    }

    @PostMapping("members/join")
    public String doJoin(MemberSaveForm memberSaveForm){

        memberService.save(memberSaveForm);

        return "redirect:/";
    }

}
