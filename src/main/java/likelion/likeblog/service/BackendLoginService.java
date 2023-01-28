package likelion.likeblog.service;

import likelion.likeblog.entity.Member;
import likelion.likeblog.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service("member")
public class BackendLoginService implements UserDetailsService {

    private MemberRepository memberRepository;

    @Autowired
    public BackendLoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        log.info(member.toString());
        return member;
        // return new User(member.getEmail(), member.getPassword(), Arrays.asList(new SimpleGrantedAuthority(member.getRole())));
    }
}
