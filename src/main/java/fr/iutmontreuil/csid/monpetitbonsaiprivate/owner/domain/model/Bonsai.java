package fr.iutmontreuil.csid.monpetitbonsaiprivate.owner.domain.model;

import java.util.UUID;

public class Bonsai {

    private final UUID id;
    private String name;
    private String species;
    private Integer age;
// Add the missing fields

    public Bonsai(UUID id, String name, String species,Integer age) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

}
