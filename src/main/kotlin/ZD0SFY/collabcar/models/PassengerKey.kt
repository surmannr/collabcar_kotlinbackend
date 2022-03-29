package ZD0SFY.collabcar.models

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class PassengerKey : java.io.Serializable {
    @Column(name = "user_id")
    var userId: Int? = null

    @Column(name = "service_id")
    var serviceId: Int? = null
}