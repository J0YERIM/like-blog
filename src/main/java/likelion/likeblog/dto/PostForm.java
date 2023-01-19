package likelion.likeblog.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
public class PostForm {

    private Long id;

    private String title;

    private String content;
}
