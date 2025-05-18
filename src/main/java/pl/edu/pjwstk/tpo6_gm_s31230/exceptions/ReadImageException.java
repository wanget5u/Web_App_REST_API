package pl.edu.pjwstk.tpo6_gm_s31230.exceptions;

public class ReadImageException extends RuntimeException
{
    public ReadImageException(String fileName)
    {super("Couldn't read image: " + fileName);}
}
