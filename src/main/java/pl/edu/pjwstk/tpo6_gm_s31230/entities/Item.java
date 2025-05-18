package pl.edu.pjwstk.tpo6_gm_s31230.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @Column(name = "NAME", nullable = false)
    private String name;

    @Setter
    @Column
    private Double price;
}
