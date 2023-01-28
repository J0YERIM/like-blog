package likelion.likeblog.repository;

import likelion.likeblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Override
    ArrayList<Comment> findAll();

    @Query(value = "SELECT * FROM comment WHERE post_id = :post_id", nativeQuery = true)
    List<Comment> findByPostId(@Param("post_id") Long post_id);
}
