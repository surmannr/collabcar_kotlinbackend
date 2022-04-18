package ZD0SFY.collabcar.dto

import java.util.*

class ServiceDto(
    var placeFrom: String = "",
    var placeTo: String = "",
    var date: Date = Date(),
    var price: Int = 0,
    var canTransportPets: Boolean = false,
    var canTransportBicycle: Boolean = false,
    var isGoingHighway: Boolean = false,
)