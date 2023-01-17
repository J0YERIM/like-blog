package likelion.likeblog.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Setter
@Table(name = "member")
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "username")
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "phone")
    private String phone;

    @OneToOne(mappedBy = "member")
    @ToString.Exclude
    private Blog blog;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Heart> hearts = new ArrayList<Heart>();

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<Comment>();

    public void addHeart(Heart heart) {
        this.hearts.add(heart);
        if (heart.getMember() != this) {
            heart.setMember(this);
        }
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        if (comment.getMember() != this) {
            comment.setMember(this);
        }
    }

    @Builder
    public Member(String email, String password, String role, String username, String nickname, String phone) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.username = username;
        this.nickname = nickname;
        this.phone = phone;
    }

    public Member(Long id, String email, String password, String role, String username, String nickname, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.username = username;
        this.nickname = nickname;
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(role));
        return auth;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
