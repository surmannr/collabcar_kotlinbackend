package ZD0SFY.collabcar.repositories

import ZD0SFY.collabcar.models.FavouriteSearch
import org.springframework.data.jpa.repository.JpaRepository

interface FavouriteSearchRepository : JpaRepository<FavouriteSearch, Int> {
}