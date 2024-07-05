package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Category;
import pja.edu.pl.s26772.restaurantmanagement.models.MenuItem;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.CategoryRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.CategoryResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MenuItemResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.CategoryRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.MenuItemRepository;
import pja.edu.pl.s26772.restaurantmanagement.services.mappers.CategoryMapper;
import pja.edu.pl.s26772.restaurantmanagement.services.mappers.MenuItemMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryResponseDto> getCategoriesWithMenuItems(){
        List<Category> categories = categoryRepository.getAllCategories();
        return categories.stream().map(categoryMapper::categoryToResponseDto).toList();
    }

    public CategoryRequestDto getRequestDto(Category category){
        return categoryMapper.categoryToRequestDto(category);
    }

    @Transactional
    public CategoryResponseDto addCategory(CategoryRequestDto requestDto){
        return categoryMapper.categoryToResponseDto(categoryRepository.save(categoryMapper.requestDtoToCategory(requestDto)));
    }

    public Optional<Category> getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Transactional
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    @Transactional
    public void updateCategory(Category category, CategoryRequestDto patchedDto) {
        category.setName(patchedDto.getName());
        categoryRepository.save(category);
    }

    public Optional<Category> getCategoryByName(String categoryName) {
        return categoryRepository.getCategoryByName(categoryName);
    }

    public CategoryResponseDto getResponseDto(Category category){
        return categoryMapper.categoryToResponseDto(category);
    }
}
