package com.pbj.blog.dto.category;

import com.pbj.blog.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModifyForm {

    private Long id;
    @NotBlank(message = "수정할 카테고리 이름을 입력해 주세요.")

    private String name;

    public CategoryModifyForm(Category category){

        this.id = category.getId();
        this.name = category.getName();

    }

}
