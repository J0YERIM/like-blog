package likelion.likeblog.service;

import likelion.likeblog.dto.CategoryForm;
import likelion.likeblog.dto.TagForm;
import likelion.likeblog.entity.Category;
import likelion.likeblog.entity.Member;
import likelion.likeblog.entity.Post;
import likelion.likeblog.entity.Tag;
import likelion.likeblog.repository.MemberRepository;
import likelion.likeblog.repository.PostRepository;
import likelion.likeblog.repository.TagRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TagService {

    private TagRepository tagRepository;
    private MemberRepository memberRepository;
    private PostRepository postRepository;

    @Autowired
    public TagService(TagRepository tagRepository, MemberRepository memberRepository, PostRepository postRepository) {
        this.tagRepository = tagRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    public Tag save(Long post_id, TagForm form, Member member) {
        Tag tag = form.toEntity();
        Post post = postRepository.findById(post_id).orElseThrow(() -> new NoSuchElementException());
        tag.setPost(post);
        Member target = post.getBlog().getMember();
        if (member.getId() != target.getId())
            return null;
        return tagRepository.save(tag);
    }

    public List<Tag> list(Long post_id) {
        List<Tag> tagList = tagRepository.findByPostId(post_id);
        return tagList;
    }

    public Tag detail(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public Tag edit(Long id, Member member) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        Member target = tag.getPost().getBlog().getMember();
        if (member.getId() != target.getId())
            return null;
        return tag;
    }

    public Tag update(TagForm form) {
        Tag tag = tagRepository.findById(form.getId()).orElseThrow(() -> new NoSuchElementException());
        tag.setTag_name(form.getTag_name());
        return tagRepository.save(tag);
    }

    public void delete(Long id, Member member) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        Member target = tag.getPost().getBlog().getMember();
        if (member.getId() == target.getId())
            tagRepository.delete(tag);
    }
}

