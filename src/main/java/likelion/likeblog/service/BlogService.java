package likelion.likeblog.service;

import likelion.likeblog.dto.BlogForm;
import likelion.likeblog.entity.Blog;
import likelion.likeblog.entity.Member;
import likelion.likeblog.repository.BlogRepository;
import likelion.likeblog.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Slf4j
@Service
public class BlogService {

    private BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog save(BlogForm form, Member member) {
        Blog blog = Blog.builder()
                .blog_name(form.getBlog_name())
                .build();
        blog.setMember(member);
        Blog saved = blogRepository.save(blog);
        return saved;
    }

    public void list(Model model) {
        List<Blog> blogEntityList = blogRepository.findAll();
        model.addAttribute("blogList", blogEntityList);
    }

    public void detail(Long id, Model model) {
        Blog blogEntity = blogRepository.findById(id).orElse(null);
        model.addAttribute("blog", blogEntity);
    }

    public void edit(Long id, Model model) {
        Blog blogEntity = blogRepository.findById(id).orElse(null);
        model.addAttribute("blog", blogEntity);
    }

    public Blog update(BlogForm form) {
        Blog target = blogRepository.findById(form.getId()).orElse(null);
        log.info(target.toString());
        if (target != null) {
            target.setBlog_name(form.getBlog_name());
            blogRepository.save(target);
        }
        return target;
    }

    public void delete(Long id) {
        Blog target = blogRepository.findById(id).orElse(null);
        if (target != null) {
            target.getMember().setBlog(null);
            blogRepository.delete(target);
        }
    }
}
