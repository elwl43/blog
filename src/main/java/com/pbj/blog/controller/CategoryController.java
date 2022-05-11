package com.pbj.blog.controller;

import com.pbj.blog.domain.Category;
import com.pbj.blog.domain.Member;
import com.pbj.blog.dto.category.CategoryDTO;
import com.pbj.blog.dto.category.CategoryListDTO;
import com.pbj.blog.dto.category.CategoryModifyForm;
import com.pbj.blog.dto.category.CategorySaveForm;
import com.pbj.blog.service.CategoryService;
import com.pbj.blog.service.MemberService;
import lombok.Getter;
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
public class CategoryController {
    private final CategoryService categoryService;
    private final MemberService memberService;

    @GetMapping("/categories/create")

        public String showCreate(Model model, CategorySaveForm categorySaveForm){
            return "usr/category/create";
        }

        @PostMapping("/categories/create")
            public String doCreate(@Validated CategorySaveForm categorySaveForm, BindingResult bindingResult, Principal principal){

                if(bindingResult.hasErrors()){
                    return "usr/category/create";
                }

                Member findMember = memberService.findByLoginId(principal.getName());

                categoryService.save(categorySaveForm, findMember);
                return "redirect:/";
            }
            @GetMapping("/categories/modify/{id}")
            public String showModify(@PathVariable(name = "id") Long id, CategoryModifyForm categoryModifyForm, Model model){
                Category findCategory = categoryService.findById(id);
                model.addAttribute("id", id);
                model.addAttribute("categoryModifyForm", new CategoryModifyForm(findCategory));
                return "usr/category/modify";
            }

            @PostMapping("/categories/modify/{id}")
                public String doModify(@PathVariable(name = "id") Long id, @Validated CategoryModifyForm categoryModifyForm, BindingResult bindingResult, Principal principal){

                    if(bindingResult.hasErrors()){
                        return "usr/category/modify";
                    }

                    Category findCategory = categoryService.findById(id);

                    if(!findCategory.getMember().getLoginId().equals(principal.getName())){
                        return "redirect:/";
                    }
                    categoryService.modifyCategory(categoryModifyForm, id);
                    return "redirect:/";
                }
                @GetMapping("/categories")
                public String showCategory(Model model){
                    List<CategoryListDTO> categoryList = categoryService.findAll();
                    model.addAttribute("categoryList", categoryList);
                    return "usr/category/list";
                }
                @GetMapping("/categories/{id}")
                public String showDetail(@PathVariable(name = "id") Long id, Model model){
                    CategoryDTO category = categoryService.getCategory(id);
                    model.addAttribute("name", category.getName());
                    model.addAttribute("articles", category.getArticles());
                    return "usr/category/detail";
                }
                @GetMapping("/categories/delete/{id}")
                public String doDelete(@PathVariable(name = "id") Long id, Principal principal){
                    Category findCategory = categoryService.findById(id);
                    if(!findCategory.getMember().getLoginId().equals(principal.getName())){
                        return "redirect:/";
                    }
                    categoryService.delete(id);
                    return "redirect:/categories";
                }
            }