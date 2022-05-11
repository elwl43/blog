package com.pbj.blog.controller;

import com.pbj.blog.domain.Member;
import com.pbj.blog.dto.MemberSaveForm;
import com.pbj.blog.dto.member.MemberModifyForm;
import com.pbj.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // html을 보여주는 url show
    // 실제 기능이 실행될 url do

    // localhost:8085/members/join
    @GetMapping("/members/join")
    public String showJoin(Model model, MemberSaveForm memberSaveForm){

        model.addAttribute("memberSaveForm", memberSaveForm);
        return "usr/member/join";
    }

    @PostMapping("/members/join")
    public String doJoin(@Validated MemberSaveForm memberSaveForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "usr/member/join";
        }

        memberService.save(memberSaveForm);

        return "redirect:/";
    }

    @GetMapping("/members/login")
    public String showLogin(){
        return "usr/member/login";
    }

    @GetMapping("/members/modify/{loginId}")
    public String showModify(@PathVariable(name = "loginId") String loginId, Principal principal, Model model){

        Member findMember = memberService.findByLoginId(loginId);

        if(!findMember.getLoginId().equals(principal.getName())){
            return "redirect:/";
        }

        model.addAttribute("memberModifyForm", new MemberModifyForm(findMember));
        model.addAttribute("loginId", loginId);

        return "usr/member/modify";
    }

    @PostMapping("/members/modify/{loginId}")
    public String doModify(@PathVariable(name = "loginId") String loginId, @Validated MemberModifyForm memberModifyForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "usr/member/modify";
        }

        memberService.modifyMember(memberModifyForm, loginId);

        return "redirect:/";

    }

}
