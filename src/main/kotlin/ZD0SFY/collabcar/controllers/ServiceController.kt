package ZD0SFY.collabcar.controllers

import ZD0SFY.collabcar.dto.CarDto
import ZD0SFY.collabcar.dto.ServiceDto
import ZD0SFY.collabcar.models.Passenger
import ZD0SFY.collabcar.models.User
import ZD0SFY.collabcar.services.PassengerService
import ZD0SFY.collabcar.models.Service as MyService
import ZD0SFY.collabcar.services.ServiceService
import ZD0SFY.collabcar.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("services")
class ServiceController(private val serviceService: ServiceService, private val passengerService: PassengerService, private val userService: UserService) {
    @GetMapping
    fun getAllServices() : List<MyService> {
        return serviceService.getAllService()
    }

    @GetMapping("/{id}")
    fun getServiceById(@PathVariable id: Int) : ResponseEntity<MyService?> {
        return try {
            var service = serviceService.getById(id)
            ResponseEntity.ok(service)
        } catch (err: Exception) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @PostMapping
    fun addNewService(@RequestParam loggedId: String, @RequestParam selectedCarId: Int,
                      @RequestBody service: ServiceDto): ResponseEntity<Nothing> {
        val user: User? = userService.existLoggedInUser(loggedId) ?: return ResponseEntity.badRequest().body(null)
        val isSuccessful = serviceService.addNewService(user, selectedCarId, service)
        return if (isSuccessful) {
            ResponseEntity.ok(null)
        } else {
            ResponseEntity.badRequest().body(null)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteServiceById(@PathVariable id: Int, @RequestParam loggedId: String): ResponseEntity<Nothing> {
        val user: User? = userService.existLoggedInUser(loggedId) ?: return ResponseEntity.badRequest().body(null)
        serviceService.deleteById(id)
        return ResponseEntity.ok(null)
    }

    @RequestMapping("/{id}", method = [RequestMethod.PUT])
    fun updateServiceById(@PathVariable id: Int, @RequestParam loggedId: String,
                          @RequestBody service: ServiceDto, @RequestParam selectedCarId: Int): ResponseEntity<Nothing> {
        val user: User? = userService.existLoggedInUser(loggedId) ?: return ResponseEntity.badRequest().body(null)
        val isSuccessful = serviceService.updateService(id, service, selectedCarId, user)
        return if (!isSuccessful) {
            ResponseEntity.badRequest().body(null)
        } else {
            ResponseEntity.ok(null)
        }
    }

    @GetMapping("/passengers")
    fun getAllPassengers() : List<Passenger> {
        return passengerService.getAllPassenger()
    }

    @GetMapping("/{id}/passengers")
    fun getPassengersByServiceId(@PathVariable id: Int) : List<Passenger> {
        return passengerService.getAllByServiceId(id)
    }

    @GetMapping("/passenger/ids")
    fun getPassengerById(@RequestParam userId: Int, @RequestParam serviceId: Int,) : ResponseEntity<Passenger?> {
        return try {
            var passenger = passengerService.getByServiceIdAndUserId(serviceId, userId)
            ResponseEntity.ok(passenger)
        } catch (err: Exception) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @PostMapping("/newPassenger")
    fun userApply(@RequestParam loggedId: String, @RequestParam serviceId: Int,): ResponseEntity<Nothing> {
        val user: User? = userService.existLoggedInUser(loggedId) ?: return ResponseEntity.badRequest().body(null)
        val isSuccessful = passengerService.userApplyForService(serviceId, user)
        return if (!isSuccessful) {
            ResponseEntity.badRequest().body(null)
        } else {
            ResponseEntity.ok(null)
        }
    }

    @RequestMapping("/acceptPassenger", method = [RequestMethod.PUT])
    fun acceptPassenger(@RequestParam loggedId: String, @RequestParam serviceId: Int, @RequestParam userId: Int): ResponseEntity<Nothing> {
        val user: User? = userService.existLoggedInUser(loggedId) ?: return ResponseEntity.badRequest().body(null)
        val isSuccessful = passengerService.acceptServiceForUser(creatorUserId = user!!.id, serviceId = serviceId, userId = userId )
        return if (!isSuccessful) {
            ResponseEntity.badRequest().body(null)
        } else {
            ResponseEntity.ok(null)
        }
    }

    @DeleteMapping("/deletePassenger")
    fun deletePassengerById(@RequestParam userId: Int, @RequestParam serviceId: Int): ResponseEntity<Nothing> {
        passengerService.deletePassenger(userId, serviceId)
        return ResponseEntity.ok(null)
    }
}