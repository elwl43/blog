package com.pbj.blog.controller;

import com.pbj.blog.domain.Article;
import com.pbj.blog.domain.Category;
import com.pbj.blog.domain.Member;
import com.pbj.blog.dto.article.ArticleDTO;
import com.pbj.blog.dto.article.ArticleListDTO;
import com.pbj.blog.dto.article.ArticleModifyForm;
import com.pbj.blog.dto.article.ArticleSaveForm;
import com.pbj.blog.dto.reply.ReplyListDTO;
import com.pbj.blog.service.ArticleService;
import com.pbj.blog.service.CategoryService;
import com.pbj.blog.service.MemberService;
import com.pbj.blog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final ReplyService replyService;


    @GetMapping("/articles/write")
    public String showWrite(Model model, ArticleSaveForm articleSaveForm){
        model.addAttribute("categoryList", categoryService.findAll());
        model.addAttribute("articleSaveForm", articleSaveForm);

        return "usr/article/write";
    }

    @PostMapping("/articles/write")
    public String doWrite(@Validated ArticleSaveForm articleSaveForm, BindingResult bindingResult, Principal principal, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("categoryList", categoryService.findAll());
            return "usr/article/write";
        }

        try {
            Category findCategory = categoryService.findById(articleSaveForm.getCategoryId());

            Member findMember = memberService.findByLoginId(principal.getName());

            articleService.save(
                    articleSaveForm,
                    findCategory,
                    findMember
            );
            return "redirect:/b/" + principal.getName() + "?category=" + findCategory.getName();
        }catch (IllegalStateException e){
            model.addAttribute("err_msg", e.getMessage());
            return "usr/article/write";
        }
    }

    @GetMapping("/articles/modify/{id}")
    public String showModify(@PathVariable(name = "id") int id, Principal principal, Model model){

//        Article findArticle = articleService.findById(id);

        Member findMember = memberService.findByLoginId(principal.getName());
        Article findArticle = findMember.getArticles().get(id);

        if(!findArticle.getMember().getLoginId().equals(principal.getName())){
            return "redirect:/";
        }

        model.addAttribute("selectedCategory", findArticle.getCategory().getId());
        model.addAttribute("categoryList", categoryService.findAll());
        model.addAttribute("articleModifyForm", new ArticleModifyForm(findArticle));
        model.addAttribute("id", id);

        return "usr/article/modify";
    }

    @PostMapping("/articles/modify/{id}")
    public String doModify(@PathVariable(name="id") Long id, @Validated ArticleModifyForm articleModifyForm, BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()){
            return "usr/article/modify";
        }
        Member findMember = memberService.findByLoginId(principal.getName());

        Category findCategory = categoryService.findById(articleModifyForm.getCategoryId());

        articleService.modifyArticle(articleModifyForm, id, findCategory);

        return "redirect:/b/" + principal.getName() + "?category=" + findCategory.getName();

    }

    @GetMapping("/articles")
    public String showList(Model model){
        List<ArticleListDTO> articles = articleService.getArticleList();

        model.addAttribute("articles", articles);

        return "usr/article/list";
    }

    @GetMapping("/articles/{id}")
    public String showDetail(@PathVariable(name = "id") Long id, Model model){

        ArticleDTO findArticle = articleService.getArticle(id);

        List<ReplyListDTO> replyList = replyService.getDtoList(findArticle.getId());

        model.addAttribute("article", findArticle);
        model.addAttribute("replyList", replyList);

        return "usr/article/detail";
    }

    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable(name = "id") Long id, Principal principal){

        // id번 게시글의 로그인 아이디 / principal
        Article findArticle = articleService.findById(id);

        if(!findArticle.getMember().getLoginId().equals(principal.getName())){
            return "redirect:/";
        }

        articleService.delete(id);

        return "redirect:/articles";

    }


}