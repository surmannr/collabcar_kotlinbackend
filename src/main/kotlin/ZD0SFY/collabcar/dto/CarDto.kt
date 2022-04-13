package ZD0SFY.collabcar.dto

import java.util.*

class CarDto(
    var registrationNumber: String = "",
    var year: Int = 0,
    var type: String = "",
    var technicalInspectionExpirationDate: Date = Date(),
    var seatingCapacity: Int = 0,
    var trunkCapacity: Int = 0,
)