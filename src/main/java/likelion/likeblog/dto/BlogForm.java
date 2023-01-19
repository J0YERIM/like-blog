package likelion.likeblog.dto;

import likelion.likeblog.entity.Blog;
import lombok.*;

@AllArgsConstructor
@Getter
@ToString
public class BlogForm {

    private Long id;

    private String blog_name;

    public Blog toEntity() { return new Blog(blog_name); }
}
