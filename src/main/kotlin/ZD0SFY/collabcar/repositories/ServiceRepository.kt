package ZD0SFY.collabcar.repositories

import ZD0SFY.collabcar.models.Service
import org.springframework.data.jpa.repository.JpaRepository

interface ServiceRepository : JpaRepository<Service, Int> {
}