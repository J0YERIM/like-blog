package likelion.likeblog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Table(name = "category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column
    private String category_name;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Post> posts = new ArrayList<Post>();

    public void addPost(Post post) {
        this.posts.add(post);
        if (post.getCategory() != this) {
            post.setCategory(this);
        }
    }

    @Builder
    public Category(Long id, String category_name, List<Post> posts) {
        this.id = id;
        this.category_name = category_name;
        this.posts = posts;
    }
}
