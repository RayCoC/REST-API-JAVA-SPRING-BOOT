package fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain.model;

import java.util.UUID;

public class Bonsai {

    private final UUID id;
    private String name;
    private String species;
    private String status;
    private Integer age;
    // Add the missing fields



    public Bonsai(UUID id, String name, String species, String status, Integer age) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.status = status;
        this.age=age;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public void setName(String nom) {
            this.name=nom;
    }

    public void setSpecies(String species) {
        this.species=species;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAge() {
        return this.age;
    }
    public void setAge(Integer age) {
        this.age=age;
    }
}
