package ZD0SFY.collabcar.models

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.util.Date

@Entity
class Car (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 1,

    @Column
    @NotNull
    var registrationNumber: String = "",

    @Column
    @NotNull
    var year: Int = 0,

    @Column
    @NotNull
    var type: String = "",

    @Column
    @NotNull
    var technicalInspectionExpirationDate: Date = Date(),

    @Column
    @NotNull
    var seatingCapacity: Int = 0,

    @Column
    @NotNull
    var trunkCapacity: Int = 0,

    @ManyToOne
    var user: User = User(),
)