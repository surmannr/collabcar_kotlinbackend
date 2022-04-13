package ZD0SFY.collabcar.services

import ZD0SFY.collabcar.dto.RegisterForm
import ZD0SFY.collabcar.models.User
import ZD0SFY.collabcar.models.UserLogged
import ZD0SFY.collabcar.repositories.UserLoggedRepository
import ZD0SFY.collabcar.repositories.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class UserService(private val userRepository: UserRepository, private val userLoggedRepository: UserLoggedRepository) {

    fun getAllUsers() : List<User>{
        return userRepository.findAll()
    }

    fun login(name: String, password: String) : Boolean {
        var users = userRepository.findByNameAndPassword(name, password)

        if (users.isNotEmpty()) {
            var user = users.first()
            var loggedUser = userLoggedRepository.findFirstByUserId(user.id)
            if (loggedUser == null) {
                userLoggedRepository.saveAndFlush(UserLogged(user = user))
                return true
            }
        }

        return false;
    }

    fun logout(id: Int) {
        userLoggedRepository.deleteAllByUserId(id)
    }

    fun getAllLoggedInUsers() : List<UserLogged>{
        return userLoggedRepository.findAll()
    }

    fun registerUser(registerForm: RegisterForm) : Boolean {
        if (registerForm.password != registerForm.passwordAgain) {
            return false
        }

        var existUser = userRepository.findFirstByName(registerForm.name)
        if (existUser != null) {
            return false
        }

        var user = User(name = registerForm.name, password = registerForm.password, email = registerForm.email, birthDate = registerForm.birthDate)

        userRepository.saveAndFlush(user)

        return true
    }

    fun existLoggedInUser(id: String) : User? {
        var uuid = UUID.fromString(id)
        var loggedInUser = userLoggedRepository.findById(uuid)
        if (loggedInUser.isEmpty) {
            return null
        }
        return loggedInUser.get().user
    }
}