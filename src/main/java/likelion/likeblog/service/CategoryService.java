package likelion.likeblog.service;

import likelion.likeblog.dto.CategoryForm;
import likelion.likeblog.entity.Category;
import likelion.likeblog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(CategoryForm form) {
        return categoryRepository.save(form.toEntity());
    }

    public List<Category> list() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public Category detail(Long id) { return categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException()); }

    public Category edit(Long id) { return categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException()); }

    public Category update(CategoryForm form) {
        Category category = form.toEntity();
        Category target = categoryRepository.findById(category.getId()).orElseThrow(() -> new NoSuchElementException());
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        categoryRepository.delete(category);
    }
}
