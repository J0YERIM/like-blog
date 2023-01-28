package likelion.likeblog.controller;

import likelion.likeblog.dto.PostForm;
import likelion.likeblog.entity.Blog;
import likelion.likeblog.entity.Member;
import likelion.likeblog.entity.Post;
import likelion.likeblog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/new")
    public String newPostForm() {
        return "posts/new";
    }

    @PostMapping("/posts/new")
    public String create(PostForm form, @AuthenticationPrincipal Member member) {
        Post created = postService.save(form, member);
        return "redirect:/posts/" + created.getId();
    }

    @GetMapping("/posts")
    public String list(Model model) {
        postService.list(model);
        return "posts/list";
    }

    @GetMapping("/posts/{id}")
    public String detail(@PathVariable Long id, Model model) {
        postService.detail(id, model);
        return "posts/detail";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        postService.edit(id, model);
        return "posts/edit";
    }

    @PostMapping("/posts/update")
    public String update(PostForm form) {
        Post updated = postService.update(form);
        return "redirect:/posts/" + updated.getId();
    }

    @GetMapping("/posts/{id}/delete")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }
}