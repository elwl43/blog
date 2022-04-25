package com.pbj.blog.service;

import com.pbj.blog.config.Role;
import com.pbj.blog.dao.MemberRepository;
import com.pbj.blog.domain.Member;
import com.pbj.blog.dto.MemberSaveForm;
import com.pbj.blog.dto.member.MemberModifyForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Transactional
    public void save(MemberSaveForm memberSaveForm) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Member member = Member.createMember(
                memberSaveForm.getLoginId(),
                bCryptPasswordEncoder.encode(memberSaveForm.getLoginPw()),
                memberSaveForm.getName(),
                memberSaveForm.getNickname(),
                memberSaveForm.getEmail(),
                Role.MEMBER
        );

        memberRepository.save(member);
    }

    public Member findByLoginId(String loginId) throws IllegalStateException{

        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);

        memberOptional.orElseThrow(
                () -> new IllegalStateException("존재하지 않는 회원입니다.")
        );

        return memberOptional.get();
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        return memberRepository.findByLoginId(loginId).orElseThrow(
                () -> new NoSuchElementException("해당 회원은 존재하지 않습니다.")
        );
    }

    @Transactional
    public Long modifyMember(MemberModifyForm memberModifyForm, String loginId) {

        Member findMember = findByLoginId(loginId);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        findMember.modifyMember(
                bCryptPasswordEncoder.encode(memberModifyForm.getLoginPw()),
                memberModifyForm.getNickname(),
                memberModifyForm.getEmail()
        );

        return findMember.getId();

    }
}