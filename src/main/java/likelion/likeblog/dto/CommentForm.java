package likelion.likeblog.dto;

import likelion.likeblog.entity.Comment;
import likelion.likeblog.entity.Member;
import likelion.likeblog.entity.Post;
import lombok.Builder;
import lombok.Data;

@Data
public class CommentForm {

    private Long id;

    private String content;

    private Member member;

    private Post post;
}
