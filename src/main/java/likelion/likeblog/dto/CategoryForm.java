package likelion.likeblog.dto;

import likelion.likeblog.entity.Category;
import likelion.likeblog.entity.Post;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryForm {

    private Long id;

    private String category_name;

    private List<Post> posts;

    public Category toEntity() {
        return Category.builder()
                .id(id)
                .category_name(category_name)
                .posts(posts)
                .build();
    }
}
