package ch.mofobo.impacct.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "users")
class User(
        @Id
        @Column(name = "user_id")
        var id: String,

        var name: String,
)