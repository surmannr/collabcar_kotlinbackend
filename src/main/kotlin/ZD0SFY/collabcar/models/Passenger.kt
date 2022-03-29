package ZD0SFY.collabcar.models

import jakarta.persistence.*

@Entity(name = "passenger")
class Passenger(

    @EmbeddedId
    var id: PassengerKey = PassengerKey(),

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    var user: User = User(),

    @ManyToOne
    @MapsId("serviceId")
    @JoinColumn(name = "service_id")
    var service: Service = Service(),

    @Column
    val isAccepted: Boolean = false,
)