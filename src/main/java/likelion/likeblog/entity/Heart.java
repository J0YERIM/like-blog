package likelion.likeblog.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Table(name = "heart")
@Entity
public class Heart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getHearts().remove(this);
        }
        this.member = member;
        if (!member.getHearts().contains(this)) {
            member.getHearts().add(this);
        }
    }

    public void setPost(Post post) {
        if (this.post != null) {
            this.post.getHearts().remove(this);
        }
        this.post = post;
        if (!post.getHearts().contains(this)) {
            post.getHearts().add(this);
        }
    }

    @Builder
    public Heart(Member member, Post post) {
        this.member = member;
        this.post = post;
    }
}
