package com.pbj.blog.domain;

import lombok.Getter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Article> articles = new ArrayList<>();

    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    public static Category createCategory(Long id, String name){

        Category category = new Category();

        category.id = id;
        category.name = name;

        return category;

    }

    public void setMember(Member member){

        this.member = member;
        member.getCategories().add(this);

    }
}