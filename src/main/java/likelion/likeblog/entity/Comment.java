package likelion.likeblog.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Table(name = "comment")
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getComments().remove(this);
        }
        this.member = member;
        if(!member.getComments().contains(this)) {
            member.getComments().add(this);
        }
    }

    public void setPost(Post post) {
        if (this.post != null) {
            this.post.getComments().remove(this);
        }
        this.post = post;
        if(!post.getComments().contains(this)) {
            post.getComments().add(this);
        }
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Builder
    public Comment(String content, Post post) {

        this.content = content;
    }
}
