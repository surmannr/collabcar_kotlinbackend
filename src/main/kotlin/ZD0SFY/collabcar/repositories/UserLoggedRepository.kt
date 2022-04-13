package ZD0SFY.collabcar.repositories

import ZD0SFY.collabcar.models.UserLogged
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface UserLoggedRepository : JpaRepository<UserLogged, UUID> {
    @Modifying
    @Query("DELETE FROM UserLogged c WHERE c.user.id = ?1")
    fun deleteAllByUserId(id: Int)
    fun findFirstByUserId(id: Int) : UserLogged?
}