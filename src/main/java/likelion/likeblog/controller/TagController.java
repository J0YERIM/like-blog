package likelion.likeblog.controller;

import likelion.likeblog.dto.CategoryForm;
import likelion.likeblog.dto.TagForm;
import likelion.likeblog.entity.Category;
import likelion.likeblog.entity.Member;
import likelion.likeblog.entity.Post;
import likelion.likeblog.entity.Tag;
import likelion.likeblog.repository.MemberRepository;
import likelion.likeblog.repository.TagRepository;
import likelion.likeblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/posts/{post_id}/tags/new")
    public String newTagForm(@PathVariable Long post_id) { return "tags/new"; }

    @PostMapping("/posts/{post_id}/tags/new")
    public String create(@PathVariable Long post_id, TagForm form, @AuthenticationPrincipal Member member) {
        Tag tag = tagService.save(post_id, form, member);
        return "index";
    }

    @GetMapping("/posts/{post_id}/tags")
    public String list(@PathVariable Long post_id, Model model) {
        List<Tag> tagList = tagService.list(post_id);
        model.addAttribute("tagList", tagList);
        return "tags/list";
    }

    @GetMapping("/posts/{post_id}/tags/{id}")
    public String detail(@PathVariable Long post_id, @PathVariable Long id, Model model) {
        Tag tag = tagService.detail(id);
        model.addAttribute("tag", tag);
        return "tags/detail";
    }

    @GetMapping("/posts/{post_id}/tags/{id}/edit")
    public String edit(@PathVariable Long post_id, @PathVariable Long id, Model model, @AuthenticationPrincipal Member member) {
        Tag tag = tagService.edit(id, member);
        model.addAttribute("tag", tag);
        return "tags/edit";
    }

    @PostMapping("/posts/{post_id}/tags/update")
    public String update(@PathVariable Long post_id, TagForm form) {
        Tag tag = tagService.update(form);
        return "redirect:/posts/" + post_id + "/tags/" + tag.getId();
    }

    @GetMapping("/posts/{post_id}/tags/{id}/delete")
    public String delete(@PathVariable Long post_id, @PathVariable Long id, @AuthenticationPrincipal Member member) {
        tagService.delete(id, member);
        return "redirect:/posts/" + post_id + "/tags/";
    }
}
