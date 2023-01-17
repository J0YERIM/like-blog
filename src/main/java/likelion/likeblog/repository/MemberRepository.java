package likelion.likeblog.repository;

import likelion.likeblog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Override
    ArrayList<Member> findAll();

    Optional<Member> findByEmail(String email);
}
