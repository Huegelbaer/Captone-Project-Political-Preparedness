package com.example.android.politicalpreparedness.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.politicalpreparedness.domain.models.Election
import java.util.Date

@Entity(tableName = "election_table")
data class ElectionEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "electionDay") val electionDay: Date,
    @Embedded(prefix = "division_") val division: DivisionEntity
) {
    fun toModel(): Election {
        return Election(id, name, electionDay, division.toModel())
    }

    companion object {
        fun fromModel(election: Election): ElectionEntity {
            return ElectionEntity(
                election.id,
                election.name,
                election.electionDay,
                DivisionEntity.fromModel(election.division)
            )
        }
    }
}