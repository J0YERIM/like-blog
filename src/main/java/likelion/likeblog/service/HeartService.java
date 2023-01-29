package likelion.likeblog.service;

import likelion.likeblog.entity.Heart;
import likelion.likeblog.entity.Member;
import likelion.likeblog.entity.Post;
import likelion.likeblog.entity.Tag;
import likelion.likeblog.repository.HeartRepository;
import likelion.likeblog.repository.MemberRepository;
import likelion.likeblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HeartService {

    private HeartRepository heartRepository;

    private MemberRepository memberRepository;

    private PostRepository postRepository;

    @Autowired
    public HeartService(HeartRepository heartRepository, MemberRepository memberRepository, PostRepository postRepository) {
        this.heartRepository = heartRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    public Heart save(Long post_id, Member member) {
        Post post = postRepository.findById(post_id).orElseThrow(() -> new NoSuchElementException());

        List<Heart> heartList = heartRepository.findByPostId(post_id);
        Heart target = heartRepository.findByMemberId(member.getId());

        if (target != null)
            return null;

        Heart heart = Heart.builder()
                .post(post)
                .member(member)
                .build();

        return heartRepository.save(heart);
    }
}
