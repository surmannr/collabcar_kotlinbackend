package ZD0SFY.collabcar.services

import ZD0SFY.collabcar.dto.ServiceDto
import ZD0SFY.collabcar.models.User
import ZD0SFY.collabcar.repositories.CarRepository
import ZD0SFY.collabcar.repositories.ServiceRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ZD0SFY.collabcar.models.Service as MyService

@Service
@Transactional
class ServiceService(private val serviceRepository: ServiceRepository, private val carRepository: CarRepository) {
    fun getAllService() : List<MyService> {
        return serviceRepository.findAll()
    }

    fun getById(id: Int) : MyService{
        var service = serviceRepository.findById(id)
        if (service.isEmpty) {
            throw NotFoundException()
        }
        return service.get()
    }

    fun addNewService(user: User?, selectedCarId: Int, service: ServiceDto) : Boolean {
        if (user == null) {
            return false
        }

        var selectedCar = carRepository.findById(selectedCarId)

        if(selectedCar.isEmpty) {
            return false
        }

        var newService : MyService = MyService(
            placeFrom = service.placeFrom,
            placeTo = service.placeTo,
            date = service.date,
            canTransportBicycle = service.canTransportBicycle,
            canTransportPets = service.canTransportPets,
            isGoingHighway = service.isGoingHighway,
            price = service.price,
            creatorUser = user,
            selectedCar = selectedCar.get()
        )

        try {
            serviceRepository.saveAndFlush(newService)
        } catch (err: Exception) {
            return  false
        }
        return true
    }

    fun deleteById(id: Int) {
        serviceRepository.deleteById(id)
    }

    // selectedCarId = -1, ha nem akarjuk megváltoztatni
    fun updateService(id: Int, service: ServiceDto, selectedCarId: Int, user: User?) : Boolean{
        var existService = serviceRepository.findById(id)

        if(existService.isEmpty) {
            return false
        }

        var selectedCar = carRepository.findById(selectedCarId)

        if (selectedCarId >= 0 && selectedCar.isEmpty) {
            return false
        }

        if (user == null) {
            return false
        }

        var updatedService = existService.get()
        updatedService.date = service.date
        updatedService.placeTo = service.placeTo
        updatedService.placeFrom = service.placeFrom
        updatedService.canTransportBicycle = service.canTransportBicycle
        updatedService.canTransportPets = service.canTransportPets
        updatedService.isGoingHighway = service.isGoingHighway
        updatedService.price = service.price
        if (selectedCarId >= 0) {
            updatedService.selectedCar = selectedCar.get()
        }

        serviceRepository.saveAndFlush(updatedService)

        return true
    }
}