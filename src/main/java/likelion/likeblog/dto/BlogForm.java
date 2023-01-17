package likelion.likeblog.dto;

import likelion.likeblog.entity.Blog;
import likelion.likeblog.entity.Member;
import likelion.likeblog.entity.Post;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class BlogForm {

    private Long id;

    private String blog_name;

    public Blog toEntity() { return new Blog(blog_name); }
}
