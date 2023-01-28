package likelion.likeblog.service;

import likelion.likeblog.dto.MemberForm;
import likelion.likeblog.entity.Member;
import likelion.likeblog.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Slf4j
@Service
public class MemberService {

    private MemberRepository memberRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member save(MemberForm form) {
        log.info(form.toString());

        Member member = Member.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .role("user")
                .username(form.getUsername())
                .nickname(form.getNickname())
                .phone(form.getPhone())
                .build();

        // Member user = form.toEntity();
        Member saved = memberRepository.save(member);

        log.info(saved.toString());
        return saved;
    }

    public void detail(Long id, Model model) {
        Member userEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("user", userEntity);
    }

    public void list(Model model) {
        List<Member> userEntityList = memberRepository.findAll();
        model.addAttribute("userList", userEntityList);
    }

    public void edit(Long id, Model model) {
        Member userEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("user", userEntity);
    }

    public Member update(MemberForm form) {
        Member userEntity = form.toEntity();
        Member target = memberRepository.findById(userEntity.getId()).orElse(null);
        if(target != null) {
            memberRepository.save(userEntity);
        }
        return userEntity;
    }

    public void delete(Long id) {
        Member target = memberRepository.findById(id).orElse(null);
        if(target != null) {
            memberRepository.delete(target);
        }
    }
}
