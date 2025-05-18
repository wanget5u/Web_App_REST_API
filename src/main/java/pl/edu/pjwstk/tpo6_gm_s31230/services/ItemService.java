package pl.edu.pjwstk.tpo6_gm_s31230.services;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.tpo6_gm_s31230.entities.Item;
import pl.edu.pjwstk.tpo6_gm_s31230.exceptions.ItemNotFoundException;
import pl.edu.pjwstk.tpo6_gm_s31230.repositories.ItemRepository;

import java.util.List;

@Service
public class ItemService
{
    private final ItemRepository itemRepository;

    ItemService(ItemRepository itemRepository)
    {this.itemRepository = itemRepository;}

    public List<Item> getItems(String sortBy)
    {
        if ("price".equalsIgnoreCase(sortBy))
        {return itemRepository.findAllByOrderByPriceAsc();}
        else
        {return itemRepository.findAllByOrderByIdAsc();}
    }

    public Item updateItem(Long id, Item updatedItem)
    {
        return itemRepository
                .findById(id)
                .map(((item) ->
                {
                    item.setName(updatedItem.getName());
                    item.setPrice(updatedItem.getPrice());
                    return itemRepository.save(item);
                })).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Item addItem(Item item)
    {return itemRepository.save(item);}

    public void deleteItemById(Long id)
    {
        if (!itemRepository.existsById(id))
        {throw new ItemNotFoundException(id);}

        itemRepository.deleteById(id);
    }
}
