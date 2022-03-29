package ZD0SFY.collabcar.seed

import ZD0SFY.collabcar.models.User
import java.util.*

class UserDataSeed {
    companion object {
        val users : List<User> = listOf(
            User(
                id = 1,
                name = "testuser",
                email = "test@test.hu",
                password = "asd123ASD",
                birthDate = Date(1998,8,18),
                hasCarpoolService = false,
            ),
            User(
                id = 2,
                name = "rick",
                email = "rick@test.hu",
                password = "asd123ASD",
                birthDate = Date(2012,1,12),
                hasCarpoolService = false,
            ),
            User(
                id = 3,
                name = "morty",
                email = "morty@test.hu",
                password = "asd123ASD",
                birthDate = Date(2022,2,22),
                hasCarpoolService = false,
            ),
        )
   }
}