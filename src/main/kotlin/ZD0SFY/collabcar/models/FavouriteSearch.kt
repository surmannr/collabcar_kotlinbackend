package ZD0SFY.collabcar.models

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.util.Date

@Entity(name = "favourite_search")
class FavouriteSearch(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 1,

    @Column
    @NotNull
    var placeFrom:String = "",

    @Column
    @NotNull
    var placeTo:String = "",

    @Column
    @NotNull
    var date: Date = Date(),

    @Column
    var minSeatingCapacity: Int? = null,

    @Column
    var maxPrice: Int? = null,

    @Column
    var driverName: String? = null,

    @Column
    var canTransportPets: Boolean? = null,

    @Column
    var canTransportBicycle: Boolean?= null,

    @Column
    var isGoingHighway: Boolean?= null,

    @ManyToOne
    var user: User = User(),

    @ManyToOne
    var driver: User = User(),
)