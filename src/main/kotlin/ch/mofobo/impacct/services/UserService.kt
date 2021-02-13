package ch.mofobo.impacct.services

import ch.mofobo.impacct.entities.User
import ch.mofobo.impacct.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) {
    fun doesUserExist(token: String): Boolean {
        return userRepository.existsById(token)
    }

    fun create(user: User): User {
        return userRepository.save(user)
    }

    fun get(token: String): User? {
        return userRepository.findByIdOrNull(token)
    }
}