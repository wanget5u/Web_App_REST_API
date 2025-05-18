package pl.edu.pjwstk.tpo6_gm_s31230.exceptions;

public class ItemNotFoundException extends RuntimeException
{
    public ItemNotFoundException(Long id)
    {super("Item not found with ID: " + id);}
}