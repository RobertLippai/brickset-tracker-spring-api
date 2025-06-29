package com.robertlippai.brickset_tracker_api.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sets")
public class BrickSet {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int sid;

    @Column(nullable = false)
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

    public BrickSet() {

    }

    public BrickSet(String setId, String name, Integer pieces, Integer releaseYear, String imageUrl, String description, String instructionUrl, Integer minifiguresCount) {
        this.setId = setId;
        this.name = name;
        this.pieces = pieces;
        this.releaseYear = releaseYear;
        this.imageUrl = imageUrl;
        this.description = description;
        this.instructionUrl = instructionUrl;
        this.minifiguresCount = minifiguresCount;
    }

    public int getSid() {
        return sid;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructionUrl() {
        return instructionUrl;
    }

    public void setInstructionUrl(String instructionUrl) {
        this.instructionUrl = instructionUrl;
    }

    public Integer getMinifiguresCount() {
        return minifiguresCount;
    }

    public void setMinifiguresCount(Integer minifiguresCount) {
        this.minifiguresCount = minifiguresCount;
    }
}
