package pl.edu.pjwstk.tpo6_gm_s31230.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @Column(name = "NAME", nullable = false)
    private String name;

    @Setter
    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Lob
    @Setter
    @Column(name = "IMAGE_DATA")
    private byte[] image;
}
