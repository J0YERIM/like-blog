package likelion.likeblog.controller;

import likelion.likeblog.entity.Heart;
import likelion.likeblog.entity.Member;
import likelion.likeblog.entity.Post;
import likelion.likeblog.service.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HeartController {

    private HeartService heartService;

    @Autowired
    public HeartController(HeartService heartService) {
        this.heartService = heartService;
    }

    @PostMapping("/posts/{post_id}/hearts/new")
    public String create(@PathVariable Long post_id, @AuthenticationPrincipal Member member) {
        Heart heart = heartService.save(post_id, member);
        return "redirect:/posts/" + post_id;
    }

    @GetMapping("/posts/{post_id}/hearts")
    public String list(@PathVariable Long post_id, Model model) {
        List<Heart> heartList = heartService.list(post_id);
        model.addAttribute("heartList", heartList);
        return "hearts/list";
    }

    @GetMapping("/posts/{post_id}/hearts/delete")
    public String delete(@PathVariable Long post_id, @AuthenticationPrincipal Member member) {
        heartService.delete(post_id, member);
        return "redirect:/posts/" + post_id;
    }
}
