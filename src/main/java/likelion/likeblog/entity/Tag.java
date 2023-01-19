package likelion.likeblog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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
}
