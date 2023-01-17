package likelion.likeblog.repository;

import likelion.likeblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Override
    ArrayList<Blog> findAll();
}
