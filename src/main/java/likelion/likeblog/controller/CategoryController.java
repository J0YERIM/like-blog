package likelion.likeblog.controller;

import likelion.likeblog.dto.CategoryForm;
import likelion.likeblog.entity.Category;
import likelion.likeblog.entity.Comment;
import likelion.likeblog.entity.Member;
import likelion.likeblog.entity.Post;
import likelion.likeblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categorys/new")
    public String newCategoryForm() { return "categorys/new"; }

    @PostMapping("/categorys/new")
    public String create(CategoryForm form) {
        Category category = categoryService.save(form);
        return "redirect:/categorys/" + category.getId();
    }

    @GetMapping("/categorys")
    public String list(Model model) {
        List<Category> categoryList = categoryService.list();
        model.addAttribute("categoryList", categoryList);
        return "categorys/list";
    }

    @GetMapping("/categorys/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Category category = categoryService.detail(id);
        List<Post> postList = category.getPosts();
        model.addAttribute("category", category);
        model.addAttribute("postList", postList);
        return "categorys/detail";
    }

    @GetMapping("/categorys/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Category category = categoryService.edit(id);
        model.addAttribute("category", category);
        return "categorys/edit";
    }

    @PostMapping("/categorys/update")
    public String update(CategoryForm form) {
        Category category = categoryService.update(form);
        return "redirect:/categorys/" + category.getId();
    }

    @GetMapping("/categorys/{id}/delete")
    public String delete(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categorys";
    }
}
