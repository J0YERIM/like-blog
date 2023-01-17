package likelion.likeblog.dto;

import likelion.likeblog.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberForm {
    private Long id;
    private String email;
    private String password;
    private String role;
    private String username;
    private String nickname;
    private String phone;
    public Member toEntity() { return new Member(id, email, password, role, username, nickname, phone); }
}
