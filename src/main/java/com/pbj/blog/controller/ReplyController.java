package com.pbj.blog.controller;

import com.pbj.blog.domain.Article;
import com.pbj.blog.domain.Member;
import com.pbj.blog.dto.reply.ReplySaveForm;
import com.pbj.blog.service.ArticleService;
import com.pbj.blog.service.MemberService;
import com.pbj.blog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final MemberService memberService;
    private final ArticleService articleService;

    @PostMapping("/articles/{article_id}/reply")
    public String writeReply(@PathVariable(name = "article_id") Long articleId, ReplySaveForm replySaveForm, Principal principal){

        Member findMember = memberService.findByLoginId(principal.getName());
        Article findArticle = articleService.findById(articleId);

        replyService.writeReply(replySaveForm, findMember, findArticle);
        return "redirect:/articles/" + articleId;
    }

}
