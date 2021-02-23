package ch.mofobo.impacct.services

import ch.mofobo.impacct.dtos.CategoryDto
import ch.mofobo.impacct.entities.Category
import ch.mofobo.impacct.repositories.CategoryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun findPaginated(pageNo: Int, pageSize: Int, sortField: String, sortDirection: Sort.Direction): Page<Category> {
        val sort = when (sortDirection) {
            Sort.Direction.ASC -> Sort.by(sortField).ascending()
            Sort.Direction.DESC -> Sort.by(sortField).descending()
        }

        val pageable: Pageable = PageRequest.of(pageNo - 1, pageSize, sort)
        return this.categoryRepository.findAll(pageable)
    }

    fun get(id: Int): Category {
        return categoryRepository.getOne(id)
    }

    fun save(categoryDto: CategoryDto) {
        categoryDto.let {
            categoryRepository.save(Category(
                    id = it.id,
                    name = it.name!!,
                    description = it.description!!))
        }
    }

    fun delete(id: Int) {
        categoryRepository.deleteById(id)
    }
}