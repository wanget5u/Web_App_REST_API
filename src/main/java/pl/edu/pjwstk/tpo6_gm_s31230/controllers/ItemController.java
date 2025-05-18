package pl.edu.pjwstk.tpo6_gm_s31230.controllers;

import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.tpo6_gm_s31230.entities.Item;
import pl.edu.pjwstk.tpo6_gm_s31230.services.ItemService;

import java.util.List;

@RestController
@RequestMapping("/data")
public class ItemController
{
    private final ItemService itemService;

    public ItemController(ItemService itemService)
    {this.itemService = itemService;}

    @GetMapping
    public List<Item> getItemsSortedBy(@RequestParam(required = false) String sortBy)
    {return itemService.getItems(sortBy);}

    @PostMapping
    public Item addItem(@RequestBody Item item)
    {return itemService.addItem(item);}

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item updatedItem)
    {return itemService.updateItem(id, updatedItem);}

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id)
    {itemService.deleteItemById(id);}
}
