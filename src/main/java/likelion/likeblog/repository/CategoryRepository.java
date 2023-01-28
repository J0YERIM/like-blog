package likelion.likeblog.repository;

import likelion.likeblog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    ArrayList<Category> findAll();
}
