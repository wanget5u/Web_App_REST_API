package pl.edu.pjwstk.tpo6_gm_s31230.controllers;

import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.tpo6_gm_s31230.entities.Item;
import pl.edu.pjwstk.tpo6_gm_s31230.repositories.ItemRepository;

import java.util.List;

@RestController
@RequestMapping("/data")
public class ItemController
{
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository)
    {this.itemRepository = itemRepository;}

    @GetMapping
    public List<Item> getItems(@RequestParam(required = false) String sortBy)
    {
        if ("price".equalsIgnoreCase(sortBy))
        {return itemRepository.findAllByOrderByPriceAsc();}
        else
        {return itemRepository.findAllByOrderByIdAsc();}
    }


    @PostMapping
    public Item addItem(@RequestBody Item item)
    {return itemRepository.save(item);}

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item updatedItem)
    {
        return itemRepository
                .findById(id)
                .map((item ->
                {
                    item.setName(updatedItem.getName());
                    item.setPrice(updatedItem.getPrice());
                    return itemRepository.save(item);
                })).orElse(updatedItem);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id)
    {itemRepository.deleteById(id);}
}
