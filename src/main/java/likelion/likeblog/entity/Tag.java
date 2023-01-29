package likelion.likeblog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@ToString
@Getter
@Table(name = "tag")
@Entity
public class Tag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column
    private String tag_name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post) {
        if (this.post != null) {
            this.post.getTags().remove(this);
        }
        this.post = post;
        if (!post.getTags().contains(this)) {
            post.getTags().add(this);
        }
    }

    @Builder
    public Tag(Long id, String tag_name, Post post) {
        this.id = id;
        this.tag_name = tag_name;
        this.post = post;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }
}
