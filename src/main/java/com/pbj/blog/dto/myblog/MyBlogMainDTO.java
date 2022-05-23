package com.pbj.blog.dto.myblog;

import com.pbj.blog.domain.Member;
import com.pbj.blog.dto.category.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyBlogMainDTO {

    private String nickname;
    private String blogName;

    private List<CategoryDTO> categoryDTOList;

    public MyBlogMainDTO(Member member){

        this.nickname = member.getNickname();
        this.blogName = member.getMyBlog().getBlogName();

    }

}
