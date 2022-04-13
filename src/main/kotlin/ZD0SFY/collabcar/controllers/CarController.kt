package ZD0SFY.collabcar.controllers

import ZD0SFY.collabcar.dto.CarDto
import ZD0SFY.collabcar.models.Car
import ZD0SFY.collabcar.models.User
import ZD0SFY.collabcar.services.CarService
import ZD0SFY.collabcar.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("cars")
class CarController(private val carService: CarService, private val userService: UserService) {

    @GetMapping
    fun getAllCars() : List<Car> {
        return carService.getAllCars()
    }

    @GetMapping("/user")
    fun getAllCarsByUser(@RequestParam loggedId: String) : ResponseEntity<List<Car>> {
        val user = userService.existLoggedInUser(loggedId) ?: return ResponseEntity.badRequest().body(null)
        val cars = carService.getAllCarsByUserId(user.id)
        return ResponseEntity.ok(cars)
    }

    @DeleteMapping("/{id}")
    fun deleteCarById(@PathVariable id: Int, @RequestParam loggedId: String): ResponseEntity<Nothing> {
        var user: User? = userService.existLoggedInUser(loggedId) ?: return ResponseEntity.badRequest().body(null)
        carService.deleteById(id)
        return ResponseEntity.ok(null)
    }

    @PostMapping
    fun addNewCar(@RequestParam loggedId: String, @RequestBody car: CarDto): ResponseEntity<Nothing> {
        val user: User? = userService.existLoggedInUser(loggedId) ?: return ResponseEntity.badRequest().body(null)
        val isSuccessful = carService.addNewCar(user, car)

        if(!isSuccessful) {
            return ResponseEntity.badRequest().body(null)
        }

        return ResponseEntity.ok(null)
    }

    @RequestMapping("/{id}", method = [RequestMethod.PUT])
    fun updateCarById(@PathVariable id: Int, @RequestParam loggedId: String, @RequestBody car: CarDto): ResponseEntity<Nothing> {
        val user: User? = userService.existLoggedInUser(loggedId) ?: return ResponseEntity.badRequest().body(null)

        val isSuccessful =carService.updateCar(id, car)

        if(!isSuccessful) {
            return ResponseEntity.badRequest().body(null)
        }

        return ResponseEntity.ok(null)
    }
}