package com.pbj.blog.dto.article;

import lombok.Data;

@Data
public class ArticleSaveForm {

    @NotBlank(message = "제목을 입력해 주세요.")

    private String title;

    @NotBlank(message = "내용을 입력해 주세요.")

    private String body;

    private Long categoryId;

}