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

    @NotBlank(message = "수정할 비밀번호를 입력해 주세요.")
    private String loginPw;

    private String name;

    @NotBlank(message = "수정할 닉네임을 입력해 주세요.")
    private String nickname;

    @NotBlank(message = "수정할 이메일을 입력해 주세요.")
    private String email;

    public MemberModifyForm(Member findMember){

        this.loginId = findMember.getLoginId();
        this.loginPw = findMember.getLoginPw();
        this.name = findMember.getName();
        this.nickname = findMember.getNickname();
        this.email = findMember.getEmail();
    }

}