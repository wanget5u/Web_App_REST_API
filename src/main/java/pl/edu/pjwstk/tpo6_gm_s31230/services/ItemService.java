package pl.edu.pjwstk.tpo6_gm_s31230.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pjwstk.tpo6_gm_s31230.entities.Item;
import pl.edu.pjwstk.tpo6_gm_s31230.exceptions.ItemNotFoundException;
import pl.edu.pjwstk.tpo6_gm_s31230.exceptions.ReadImageException;
import pl.edu.pjwstk.tpo6_gm_s31230.repositories.ItemRepository;

import java.io.IOException;
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
        {return itemRepository.findAllByOrderByNameAsc();}
    }

    public Item updateItem(Long id, Item updatedItem, MultipartFile imageFile)
    {
        return itemRepository
                .findById(id)
                .map(item ->
                {
                    item.setName(updatedItem.getName());
                    item.setPrice(updatedItem.getPrice());

                    try
                    {
                        if (imageFile != null && !imageFile.isEmpty())
                        {item.setImage(imageFile.getBytes());}
                    }
                    catch (IOException exception)
                    {throw new ReadImageException(imageFile.getName());}

                    return itemRepository.save(item);
                }).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Item addItem(Item item, MultipartFile imageFile)
    {
        try
        {
            if (imageFile != null && !imageFile.isEmpty())
            {item.setImage(imageFile.getBytes());}
        }
        catch (IOException exception)
        {throw new ReadImageException(imageFile.getName());}

        return itemRepository.save(item);
    }

    public void deleteItemById(Long id)
    {
        if (!itemRepository.existsById(id))
        {throw new ItemNotFoundException(id);}

        itemRepository.deleteById(id);
    }

    public Item getItemById(Long id)
    {return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));}

    public ResponseEntity<byte[]> getImageData(Long id)
    {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

        byte[] imageData = item.getImage();

        if (imageData == null || imageData.length == 0)
        {return ResponseEntity.notFound().build();}

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageData, httpHeaders, HttpStatus.OK);
    }
}
