package ZD0SFY.collabcar.repositories

import ZD0SFY.collabcar.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
}