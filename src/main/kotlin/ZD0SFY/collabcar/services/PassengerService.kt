package ZD0SFY.collabcar.services

import ZD0SFY.collabcar.models.Passenger
import ZD0SFY.collabcar.models.PassengerKey
import ZD0SFY.collabcar.models.User
import ZD0SFY.collabcar.repositories.PassengerRepository
import ZD0SFY.collabcar.repositories.ServiceRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PassengerService(private val serviceRepository: ServiceRepository, private val passengerRepository: PassengerRepository) {
    fun getAllPassenger() : List<Passenger> {
        return passengerRepository.findAll()
    }

    fun getAllByServiceId(serviceId: Int) : List<Passenger>{
        return passengerRepository.findByServiceId(serviceId)
    }

    fun getByServiceIdAndUserId(serviceId: Int, userId: Int): Passenger {
        return passengerRepository.findByServiceIdAndUserId(serviceId, userId) ?: throw NotFoundException()
    }

    fun userApplyForService(serviceId: Int, user: User?): Boolean {
        val service = serviceRepository.findById(serviceId)
        if(service.isEmpty) {
            return false
        }

        if(user == null) {
            return false
        }

        // A szolgáltatás létrehozója nem jelentkezhet
        if(user.id == service.get().creatorUser.id) {
            return false
        }

        var passenger = Passenger(id = PassengerKey(userId = user.id, serviceId = service.get().id),
                        service = service.get(), user = user)

        try {
            passengerRepository.saveAndFlush(passenger)
        } catch (err: Exception) {
            return  false
        }
        return true
    }

    fun acceptServiceForUser(creatorUserId: Int, userId: Int, serviceId: Int) : Boolean{
        var passenger: Passenger? = passengerRepository.findByServiceIdAndUserId(serviceId, userId) ?: return false
        if(passenger?.service?.creatorUser?.id != creatorUserId) {
            return false
        }
        passenger.isAccepted = true
        try {
            passengerRepository.saveAndFlush(passenger)
        } catch (err: Exception) {
            return  false
        }
        return true
    }

    fun deletePassenger(userId: Int, serviceId: Int) {
        passengerRepository.deleteByServiceIdAndUserId(serviceId, userId)
    }
}