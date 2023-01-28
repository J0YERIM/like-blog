package likelion.likeblog.controller;

import likelion.likeblog.dto.CategoryForm;
import likelion.likeblog.entity.Category;
import likelion.likeblog.entity.Member;
import likelion.likeblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/new")
    public String newCategoryForm() { return "categorys/new"; }
}
