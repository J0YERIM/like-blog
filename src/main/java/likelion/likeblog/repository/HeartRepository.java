package likelion.likeblog.repository;

import likelion.likeblog.entity.Heart;
import likelion.likeblog.entity.Post;
import likelion.likeblog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {

    @Override
    ArrayList<Heart> findAll();

    @Query(value = "SELECT * FROM heart WHERE post_id = :post_id", nativeQuery = true)
    List<Heart> findByPostId(@Param("post_id") Long post_id);

    @Query(value = "SELECT * FROM heart WHERE member_id = :member_id", nativeQuery = true)
    Heart findByMemberId(@Param("member_id") Long member_id);
}
