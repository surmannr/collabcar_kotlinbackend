package ZD0SFY.collabcar.controllers

import ZD0SFY.collabcar.dto.LoginForm
import ZD0SFY.collabcar.dto.RegisterForm
import ZD0SFY.collabcar.models.User
import ZD0SFY.collabcar.models.UserLogged
import ZD0SFY.collabcar.services.UserService
import jakarta.persistence.Id
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers() : List<User> {
        return userService.getAllUsers()
    }

    @GetMapping("/loggedInUsers")
    fun getAllLoggedInUsers() : List<UserLogged>{
        return userService.getAllLoggedInUsers()
    }

    @PostMapping("/login")
    fun login(@RequestBody loginForm: LoginForm): ResponseEntity<Boolean> {
        var isSuccessful = userService.login(loginForm.name, loginForm.password)

        if(!isSuccessful){
            return ResponseEntity.badRequest().body(null)
        }
        return ResponseEntity.ok(isSuccessful)
    }

    @PostMapping("/logout/{id}")
    fun logout(@PathVariable id: Int){
        userService.logout(id)
    }

    @PostMapping("/register")
    fun logout(@RequestBody registerForm: RegisterForm): ResponseEntity<Boolean> {
        var isSuccessful = userService.registerUser(registerForm)
        if(!isSuccessful){
            return ResponseEntity.badRequest().body(null)
        }
        return ResponseEntity.ok(isSuccessful)
    }
}