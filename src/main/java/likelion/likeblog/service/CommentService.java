package likelion.likeblog.service;

import likelion.likeblog.dto.CommentForm;
import likelion.likeblog.entity.Comment;
import likelion.likeblog.entity.Member;
import likelion.likeblog.entity.Post;
import likelion.likeblog.repository.CommentRepository;
import likelion.likeblog.repository.MemberRepository;
import likelion.likeblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    private MemberRepository memberRepository;

    private PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    public Comment save(Long post_id, CommentForm form, Member member) {
        Comment comment = Comment.builder()
                .content(form.getContent())
                .build();

        Post post = postRepository.findById(post_id).orElse(null);
        if (post != null) {
            comment.setPost(post);
        }

        Member member1 = memberRepository.findByEmail(member.getEmail()).orElse(null);
        if (member1 != null) {
            comment.setMember(member1);
        }

        Comment saved = commentRepository.save(comment);
        return saved;
    }

    public void list(Long post_id, Model model) {
        List<Comment> commentEntityList = commentRepository.findByPostId(post_id);
        model.addAttribute("commentList", commentEntityList);
    }

    public void detail(Long id, Model model) {
        Comment commentEntity = commentRepository.findById(id).orElse(null);
        model.addAttribute("comment", commentEntity);
    }

    public Comment edit(Long id, Model model, Member member) {
        Comment commentEntity = commentRepository.findById(id).orElse(null);
        if (commentEntity.getMember().getId() != member.getId()) {
            commentEntity = null;
        }
        else if (commentEntity != null) {
            model.addAttribute("comment", commentEntity);
        }
        return commentEntity;
    }

    public Comment update(CommentForm form) {
        Comment target = commentRepository.findById(form.getId()).orElse(null);
        if (target != null) {
            target.setContent(form.getContent());
            commentRepository.save(target);
        }
        return target;
    }

    public Comment delete(Long id, Member member) {
        Comment target = commentRepository.findById(id).orElse(null);
        if (target.getMember().getId() != member.getId()) {
            target = null;
        }
        else if (target != null) {
            target.getPost().getComments().remove(this);
            commentRepository.delete(target);
        }
        return target;
    }
}
