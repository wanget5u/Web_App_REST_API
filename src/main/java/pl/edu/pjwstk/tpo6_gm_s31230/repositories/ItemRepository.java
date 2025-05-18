package pl.edu.pjwstk.tpo6_gm_s31230.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.tpo6_gm_s31230.entities.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>
{
    List<Item> findAllByOrderByIdAsc();
    List<Item> findAllByOrderByPriceAsc();
}
