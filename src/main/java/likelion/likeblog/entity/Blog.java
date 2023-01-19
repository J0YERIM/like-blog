package likelion.likeblog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "blog")
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id;

    @Column
    @ToString.Exclude
    private String blog_name;

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "blog")
    @ToString.Exclude
    private List<Post> posts = new ArrayList<Post>();

    public void addPost(Post post) {
        this.posts.add(post);
        if (post.getBlog() != this) {
            post.setBlog(this);
        }
    }

    public void setMember(Member member) {
        this.member = member;
        member.setBlog(this);
    }
    @Builder
    public Blog(String blog_name) {
        this.blog_name = blog_name;
    }
}
