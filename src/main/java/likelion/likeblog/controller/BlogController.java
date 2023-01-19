package likelion.likeblog.controller;

import likelion.likeblog.dto.BlogForm;
import likelion.likeblog.entity.Blog;
import likelion.likeblog.entity.Member;
import likelion.likeblog.service.BlogService;
import likelion.likeblog.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs/new")
    public String newBlogForm() {
        return "blogs/new";
    }

    @PostMapping("/blogs/new")
    public String create(BlogForm form, @AuthenticationPrincipal Member member) {
        Blog created = blogService.save(form, member);
        return "redirect:/blogs/" + created.getId();
    }

    @GetMapping("/blogs")
    public String list(Model model) {
        blogService.list(model);
        return "blogs/list";
    }

    @GetMapping("/blogs/{id}")
    public String detail(@PathVariable Long id, Model model) {
        blogService.detail(id, model);
        return "blogs/detail";
    }

    @GetMapping("/blogs/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        blogService.edit(id, model);
        return "blogs/edit";
    }

    @PostMapping("/blogs/update")
    public String update(BlogForm form) {
        Blog updated = blogService.update(form);
        return "redirect:/blogs/" + updated.getId();
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/blogs";
    }
}
