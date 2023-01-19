package likelion.likeblog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "post")
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "blog_id")
    @ToString.Exclude
    private Blog blog;

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<Heart> hearts = new ArrayList<Heart>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<Tag> tags = new ArrayList<Tag>();

    public void setBlog(Blog blog) {
        /*if (this.blog != null) {
            this.blog.getPosts().remove(this);
        }*/
        this.blog = blog;
        if(!blog.getPosts().contains(this)) {
            blog.getPosts().add(this);
        }
    }

    public void setCategory(Category category) {
        this.category = category;
        if (!category.getPosts().contains(this)) {
            category.getPosts().add(this);
        }
    }

    public void addHeart(Heart heart) {
        this.hearts.add(heart);
        if (heart.getPost() != this) {
            heart.setPost(this);
        }
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        if (tag.getPost() != this) {
            tag.setPost(this);
        }
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        if (comment.getPost() != this) {
            comment.setPost(this);
        }
    }

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
