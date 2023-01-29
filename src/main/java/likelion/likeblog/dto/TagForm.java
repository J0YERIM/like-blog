package likelion.likeblog.dto;

import likelion.likeblog.entity.Category;
import likelion.likeblog.entity.Post;
import likelion.likeblog.entity.Tag;
import lombok.Data;

import java.util.List;

@Data
public class TagForm {

    private Long id;

    private String tag_name;

    private Post post;

    public Tag toEntity() {
        return Tag.builder()
                .id(id)
                .tag_name(tag_name)
                .post(post)
                .build();
    }
}
