package likelion.likeblog.repository;


import likelion.likeblog.entity.Comment;
import likelion.likeblog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Override
    ArrayList<Tag> findAll();

    @Query(value = "SELECT * FROM tag WHERE post_id = :post_id", nativeQuery = true)
    List<Tag> findByPostId(@Param("post_id") Long post_id);
}
