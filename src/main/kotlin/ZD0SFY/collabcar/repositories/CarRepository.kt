package ZD0SFY.collabcar.repositories

import ZD0SFY.collabcar.models.Car
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository : JpaRepository<Car, Int> {
    fun findAllByUserId(id: Int) : List<Car>
}