package likelion.likeblog.controller;

import likelion.likeblog.entity.Heart;
import likelion.likeblog.entity.Member;
import likelion.likeblog.service.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
}
