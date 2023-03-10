package likelion.likeblog.repository;

import likelion.likeblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Override
    ArrayList<Blog> findAll();
}
