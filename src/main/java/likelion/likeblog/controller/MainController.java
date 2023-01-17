package likelion.likeblog.controller;

import likelion.likeblog.dto.MemberForm;
import likelion.likeblog.entity.Member;
import likelion.likeblog.repository.MemberRepository;
import likelion.likeblog.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String getIndex() { return "index"; }

    @GetMapping("/login")
    public String getLoginForm() { return "users/loginPage"; }

    @GetMapping("/signUp")
    public String getSignupForm() { return "users/signupPage"; }

    @PostMapping("/doSignUp")
    public String signUp(MemberForm form) {
        Member saved = memberService.save(form);
        return "redirect:/login";
    }
}
