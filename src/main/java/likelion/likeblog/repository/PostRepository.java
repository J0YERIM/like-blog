package likelion.likeblog.repository;

import likelion.likeblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
public interface PostRepository extends JpaRepository<Post, Long> {

    @Override
    ArrayList<Post> findAll();
}
