package pl.edu.pjwstk.tpo6_gm_s31230.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Lob
    @Column(name = "IMAGE_DATA")
    private byte[] image;
}
