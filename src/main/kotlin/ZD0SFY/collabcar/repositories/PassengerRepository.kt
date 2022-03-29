package ZD0SFY.collabcar.repositories

import ZD0SFY.collabcar.models.Passenger
import ZD0SFY.collabcar.models.PassengerKey
import org.springframework.data.jpa.repository.JpaRepository

interface PassengerRepository : JpaRepository<Passenger, PassengerKey> {
}