package ZD0SFY.collabcar.models

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.util.Date

@Entity(name = "service")
class Service(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 1,

    @Column
    @NotNull
    var placeFrom: String = "",

    @Column
    @NotNull
    var placeTo: String = "",

    @Column
    @NotNull
    var date: Date = Date(),

    @Column
    var price: Int = 0,

    @Column
    var canTransportPets: Boolean = false,

    @Column
    var canTransportBicycle: Boolean = false,

    @Column
    var isGoingHighway: Boolean = false,

    @ManyToOne
    var creatorUser: User = User(),

    @ManyToOne
    var selectedCar: Car = Car(),
)