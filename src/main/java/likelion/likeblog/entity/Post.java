package likelion.likeblog.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "post")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    public void setBlog(Blog blog) {
        this.blog = blog;
        if(!blog.getPosts().contains(this)) {
            blog.getPosts().add(this);
        }
    }

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<Heart> hearts = new ArrayList<Heart>();

    public void addHeart(Heart heart) {
        this.hearts.add(heart);
        if (heart.getPost() != this) {
            heart.setPost(this);
        }
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public void setCategory(Category category) {
        this.category = category;

        if (!category.getPosts().contains(this)) {
            category.getPosts().add(this);
        }
    }

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<Comment>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
        if (comment.getPost() != this) {
            comment.setPost(this);
        }
    }

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<Tag> tags = new ArrayList<Tag>();

    public void addTag(Tag tag) {
        this.tags.add(tag);
        if (tag.getPost() != this) {
            tag.setPost(this);
        }
    }
}
