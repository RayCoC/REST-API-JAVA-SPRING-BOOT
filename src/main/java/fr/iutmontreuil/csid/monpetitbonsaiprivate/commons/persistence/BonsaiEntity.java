package fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name="Bonsai")
@Table(name = "bonsai")
public class BonsaiEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name="name")
    private String name;
    @Column(name = "species")
    private String species;
    @ManyToOne(targetEntity = OwnerEntity.class)
    @JoinColumn(name = "owner_id", updatable = false, insertable = false)
    private OwnerEntity ownerEntity;
    @Column(name = "status")
    private String status;
    @Column(name = "age")
    private Integer age;

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    @Column(name = "owner_id")
    private UUID ownerId;
    public BonsaiEntity() {
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }public String getSpecies() {
        return species;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name= name;
    }
    public void setSpecies(String species) {
        this.species= species;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
