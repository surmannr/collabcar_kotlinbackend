package ZD0SFY.collabcar.models

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
class UserLogged (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    @Type(type="org.hibernate.type.UUIDCharType")
    val id: UUID = UUID.randomUUID(),

    @Column
    @NotNull
    var loginDate: Date = Date(),

    @OneToOne
    var user: User = User(),
)