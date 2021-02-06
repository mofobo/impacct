package ch.mofobo.impacct.dtos

import ch.mofobo.impacct.entities.Record

data class RecordsCreationDto(var records: MutableList<Record> = mutableListOf())