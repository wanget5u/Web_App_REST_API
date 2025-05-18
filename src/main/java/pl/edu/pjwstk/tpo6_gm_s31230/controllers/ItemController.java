package pl.edu.pjwstk.tpo6_gm_s31230.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public Item addItem(@ModelAttribute Item item, @RequestPart(required = false) MultipartFile imageFile)
    {return itemService.addItem(item, imageFile);}

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @ModelAttribute Item updatedItem, @RequestPart(required = false) MultipartFile imageFile)
    {return itemService.updateItem(id, updatedItem, imageFile);}

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id)
    {itemService.deleteItemById(id);}

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getItemImage(@PathVariable Long id)
    {return itemService.getImageData(id);}
}
