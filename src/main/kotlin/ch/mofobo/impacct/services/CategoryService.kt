package ch.mofobo.impacct.services

import ch.mofobo.impacct.entities.Transaction
import ch.mofobo.impacct.repositories.CategoryRepository
import ch.mofobo.impacct.repositories.TransactionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class CategoryService(private val categoryRepository: CategoryRepository)