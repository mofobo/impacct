package ch.mofobo.impacct.dtos

import ch.mofobo.impacct.entities.Category

class CategoryDto {

    constructor()

    constructor(id: Int, name: String, description: String) {
        this.id = id
        this.name = name
        this.description = description
    }

    constructor(entity: Category) {
        this.id = entity.id
        this.name = entity.name
        this.description = entity.description
    }

    var id: Int? = null
    var name: String? = null
    var description: String? = null
}