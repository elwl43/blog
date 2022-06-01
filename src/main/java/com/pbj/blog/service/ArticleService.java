package com.pbj.blog.service;

import com.pbj.blog.dao.ArticleRepository;
import com.pbj.blog.domain.Article;
import com.pbj.blog.domain.Category;
import com.pbj.blog.domain.Member;
import com.pbj.blog.dto.article.ArticleDTO;
import com.pbj.blog.dto.article.ArticleListDTO;
import com.pbj.blog.dto.article.ArticleModifyForm;
import com.pbj.blog.dto.article.ArticleSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public void save(ArticleSaveForm articleSaveForm, Category category, Member member) {

        Article article = Article.createArticle(
                articleSaveForm.getTitle(),
                articleSaveForm.getBody()
        );

        article.setMember(member);
        article.setCategory(category);

        articleRepository.save(article);
    }

    public Article findById(Long id) {

        Optional<Article> articleOptional = articleRepository.findById(id);

        articleOptional.orElseThrow(
                () -> new IllegalStateException("존재하지 않는 게시글 입니다.")
        );

        return articleOptional.get();
    }

    @Transactional
    public void modifyArticle(ArticleModifyForm articleModifyForm, Long id, Category category) {

        Article findArticle = findById(id);

        findArticle.modifyArticle(
                articleModifyForm.getTitle(),
                articleModifyForm.getBody()
        );

        findArticle.setCategory(category);

    }

    public List<ArticleListDTO> getArticleList() {

        List<Article> articleList = articleRepository.findAll();

        List<ArticleListDTO> articleDTOList = new ArrayList<>();

        for (Article article : articleList){

            ArticleListDTO articleDTO = new ArticleListDTO(article);
            articleDTOList.add(articleDTO);

        }
        return articleDTOList;

    }

    public List<ArticleDTO> getArticleDTOList(){

        List<Article> articleList = articleRepository.findAll();

        List<ArticleDTO> articleListDto = new ArrayList<>();

        for(Article article : articleList){
            ArticleDTO articleDTO = new ArticleDTO(article);
            articleListDto.add(articleDTO);
        }

        return articleListDto;

    }

    public ArticleDTO getArticle(Long id) {

        Article findArticle = findById(id);

        ArticleDTO articleDTO = new ArticleDTO(findArticle);

        return articleDTO;

    }

    @Transactional
    public void delete(Long id) {

        Article findArticle = findById(id);
        articleRepository.delete(findArticle);

    }

    public Long articleCount(){
        return articleRepository.count();
    }
}