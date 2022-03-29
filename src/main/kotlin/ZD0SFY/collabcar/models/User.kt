package ZD0SFY.collabcar.models

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.util.Date

@Entity(name = "user")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 1,

    @Column
    @NotNull
    var name: String = "",

    @Column
    @NotNull
    var email: String = "",

    @Column
    @NotNull
    var password: String = "",

    @Column
    @NotNull
    var birthDate: Date = Date(),

    @Column
    @NotNull
    var hasCarpoolService: Boolean = false,
)