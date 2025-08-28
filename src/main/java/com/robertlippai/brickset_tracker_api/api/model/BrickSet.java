package com.robertlippai.brickset_tracker_api.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrickSet {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int sid;

    @Column(nullable = false, unique = true)
    private String setId;

    @Column(nullable = false)
    private String name;

    private Integer pieces;
    private Integer releaseYear;
    private String imageUrl;
    @Lob
    private String description;
    private String instructionUrl;
    private Integer minifiguresCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid", nullable = false)
    private BrickBrand brand;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "sets_tags",
            joinColumns = @JoinColumn(name = "sid"),
            inverseJoinColumns = @JoinColumn(name = "tid")
    )
    private Set<BrickTag> tags = new HashSet<>();

    public void addTag(BrickTag tag) {
        this.tags.add(tag);
    }

    public void removeTag(BrickTag tag) {
        this.tags.remove(tag);
    }
}
