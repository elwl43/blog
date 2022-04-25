package com.pbj.blog.dto.member;

import com.pbj.blog.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberModifyForm {

    private String loginId;

    private String loginPw;

    private String name;

    private String nickname;

    private String email;

    public MemberModifyForm(Member findMember){

        this.loginId = findMember.getLoginId();
        this.loginPw = findMember.getLoginPw();
        this.name = findMember.getName();
        this.nickname = findMember.getNickname();
        this.email = findMember.getEmail();
    }

}