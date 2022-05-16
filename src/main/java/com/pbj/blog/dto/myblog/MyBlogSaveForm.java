package com.pbj.blog.dto.myblog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyBlogSaveForm {

    @NotBlank
    private String blogName;

    @NotBlank
    private String blogDesc;

}