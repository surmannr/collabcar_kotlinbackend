package ZD0SFY.collabcar.seed

import ZD0SFY.collabcar.repositories.UserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class Seeding(private val userRepository: UserRepository) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        userRepository.saveAllAndFlush(UserDataSeed.users)
    }
}