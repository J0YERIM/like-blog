package likelion.likeblog.service;

import likelion.likeblog.dto.PostForm;
import likelion.likeblog.entity.Blog;
import likelion.likeblog.entity.Member;
import likelion.likeblog.entity.Post;
import likelion.likeblog.repository.BlogRepository;
import likelion.likeblog.repository.MemberRepository;
import likelion.likeblog.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Post save(PostForm form, Member member) {
        Post post = Post.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .build();

        Member member1 = memberRepository.findByEmail(member.getEmail()).orElse(null);
        Blog blog = blogRepository.findById(member1.getId()).orElse(null);
        if (blog != null) {
            post.setBlog(blog);
        }
        // post.setCategory(null); // -> 나중에 수정

        Post saved = postRepository.save(post);
        return saved;
    }

    public void list(Model model) {
        List<Post> postEntityList = postRepository.findAll();
        model.addAttribute("postList", postEntityList);
    }

    public void detail(Long id, Model model) {
        Post postEntity = postRepository.findById(id).orElse(null);
        model.addAttribute("post", postEntity);
    }

    public void edit(Long id, Model model) {
        Post postEntity = postRepository.findById(id).orElse(null);
        model.addAttribute("post", postEntity);
    }

    public Post update(PostForm form) {
        Post target = postRepository.findById(form.getId()).orElse(null);
        if(target != null) {
            target.setTitle(form.getTitle());
            target.setContent(form.getContent());
            postRepository.save(target);
        }
        return target;
    }

    public void delete(Long id) {
        Post target = postRepository.findById(id).orElse(null);
        if (target != null) {
            target.getBlog().getPosts().remove(this);
            postRepository.delete(target);
        }
    }
}
