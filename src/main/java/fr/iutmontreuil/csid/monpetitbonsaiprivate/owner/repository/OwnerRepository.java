package fr.iutmontreuil.csid.monpetitbonsaiprivate.owner.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.BonsaiMapper;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain.model.CareEvent;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence.*;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.owner.domain.model.Bonsai;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.owner.OwnerMapper;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.owner.domain.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OwnerRepository {

    private final OwnerDao ownerDao;
    private final BonsaiDao bonsaiDao;

    public OwnerRepository(OwnerDao ownerDao, BonsaiDao bonsaiDao) {
        this.ownerDao = ownerDao;
        this.bonsaiDao = bonsaiDao;
    }

    public Owner create(Owner owner) {
        OwnerEntity ownerToCreate = OwnerMapper.mapModelToEntityOwner(owner);
        OwnerEntity savedOwner = ownerDao.save(ownerToCreate);
        return OwnerMapper.mapEntityToModelOwner(savedOwner);
    }

    public List<Owner> findAll() {

        return OwnerMapper.mapEntitiesToModelsOwner(ownerDao.findAll());
    }

    public Owner findById(UUID uuid) {
        Optional<OwnerEntity> entity = ownerDao.findById(uuid);
        return entity.map(OwnerMapper::mapEntityToModelOwner).get();
    }

    public void transfert(UUID ownerID, UUID bonsaiUUID) {
        bonsaiDao.transfert(ownerID, bonsaiUUID);
    }

    public void addBonsaiToOwner(UUID ownerUUID, List<UUID> bonsaisUUID) {

        for (UUID bonsaiUUID : bonsaisUUID) {
            BonsaiEntity b = (bonsaiDao.findById(bonsaiUUID).get());
            if (b != null) {
                //if (b.getOwnerId().equals("")) {
                    bonsaiDao.transfert(ownerUUID, bonsaiUUID);
                //}
            }
        }
    }
    public List<Bonsai> findAllBonsaisOfAnOwner (UUID uuid) {
        Owner owner = this.findById(uuid);
        return (owner.getBonsais());
    }

}
