package likelion.likeblog.controller;

import likelion.likeblog.dto.CommentForm;
import likelion.likeblog.entity.Comment;
import likelion.likeblog.entity.Member;
import likelion.likeblog.entity.Post;
import likelion.likeblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/posts/{post_id}/comments/new")
    public String newCommentForm(@PathVariable Long post_id) {
        return "comments/new";
    }

    @PostMapping("/posts/{post_id}/comments/new")
    public String create(@PathVariable Long post_id, CommentForm form, @AuthenticationPrincipal Member member) {
        Comment created = commentService.save(post_id, form, member);
        return "redirect:/comments/" + created.getId();
    }

    @GetMapping("/posts/{post_id}/comments")
    public String list(@PathVariable Long post_id, Model model) {
        commentService.list(post_id, model);
        return "comments/list";
    }

    @GetMapping("/comments/{id}")
    public String detail(@PathVariable Long id, Model model) {
        commentService.detail(id, model);
        return "comments/detail";
    }

    @GetMapping("/comments/{id}/edit")
    public String edit(@PathVariable Long id, Model model, @AuthenticationPrincipal Member member) {
        Comment edited = commentService.edit(id, model, member);
        if (edited == null) {
            return "redirect:/";
        }
        return "comments/edit";
    }

    @PostMapping("/comments/update")
    public String update(CommentForm form) {
        Comment updated = commentService.update(form);
        return "redirect:/comments/" + updated.getId();
    }

    @GetMapping("/posts/{post_id}/comments/{id}/delete")
    public String delete(@PathVariable Long post_id, @PathVariable Long id, @AuthenticationPrincipal Member member) {
        Comment deleted = commentService.delete(id, member);
        if (deleted == null) {
            return "redirect:/";
        }
        return "redirect:/posts/" + post_id + "/comments";
    }
}
