package ch.mofobo.impacct.repositories

import ch.mofobo.impacct.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String>