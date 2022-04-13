package ZD0SFY.collabcar.services

import ZD0SFY.collabcar.dto.CarDto
import ZD0SFY.collabcar.models.Car
import ZD0SFY.collabcar.models.User
import ZD0SFY.collabcar.repositories.CarRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CarService(private val carRepository: CarRepository) {
    fun getAllCars() : List<Car>{
        return carRepository.findAll()
    }

    fun getAllCarsByUserId(id: Int) : List<Car> {
        return carRepository.findAllByUserId(id)
    }

    fun deleteById(carId: Int) {
        carRepository.deleteById(carId)
    }

    fun addNewCar(user: User?, carInfo: CarDto) : Boolean {
        if (user == null) {
            return false
        }

        var car = Car(
            registrationNumber = carInfo.registrationNumber,
            year = carInfo.year,
            seatingCapacity = carInfo.seatingCapacity,
            trunkCapacity = carInfo.trunkCapacity,
            type = carInfo.type,
            technicalInspectionExpirationDate = carInfo.technicalInspectionExpirationDate,
            user = user,
        )

        try {
            carRepository.saveAndFlush(car)
        } catch (err: Exception) {
            return false
        }

        return true
    }

    fun updateCar(id: Int, carInfo: CarDto) : Boolean {
        var car = carRepository.findById(id)
        if (car.isEmpty) {
            return false
        }

        var existcar = car.get()
        existcar.registrationNumber = carInfo.registrationNumber
        existcar.year = carInfo.year
        existcar.seatingCapacity = carInfo.seatingCapacity
        existcar.trunkCapacity = carInfo.trunkCapacity
        existcar.type = carInfo.type
        existcar.technicalInspectionExpirationDate = carInfo.technicalInspectionExpirationDate

        carRepository.saveAndFlush(existcar)
        return true
    }
}