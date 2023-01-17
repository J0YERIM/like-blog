package likelion.likeblog.controller;

import likelion.likeblog.dto.MemberForm;
import likelion.likeblog.entity.Member;
import likelion.likeblog.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/users/new")
    public String newUserForm() {
        return "users/new";
    }

    @PostMapping("/users/create")
    public String create(MemberForm form) {
        Member created = memberService.save(form);
        return "redirect:/users/" + created.getId();
    }

    @GetMapping("/users/{id}")
    public String detail(@PathVariable Long id, Model model) {
        memberService.detail(id, model);
        return "users/detail";
    }

    @GetMapping("/users")
    public String list(Model model) {
        memberService.list(model);
        return "users/list";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        memberService.edit(id, model);
        return "users/edit";
    }

    @PostMapping("/users/update")
    public String update(MemberForm form) {
        Member updated = memberService.update(form);
        return "redirect:/users/" + updated.getId();
    }

    @GetMapping("/users/{id}/delete")
    public String delete(@PathVariable Long id) {
        memberService.delete(id);
        return "redirect:/users";
    }
}
