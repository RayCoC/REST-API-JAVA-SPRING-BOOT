package fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.repository;

import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.BonsaiMapper;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.CareEventMapper;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain.model.Bonsai;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain.model.CareEvent;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence.BonsaiDao;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence.BonsaiEntity;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence.CareEventDao;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence.CareEventEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BonsaiRepository {

    private final BonsaiDao bonsaiDao;
    private final CareEventDao careEventDao;

    public BonsaiRepository(BonsaiDao bonsaiDao, CareEventDao careEventDao) {
        this.bonsaiDao = bonsaiDao;
        this.careEventDao = careEventDao;
    }

    public Optional<Bonsai> findById(UUID uuid) {
        Optional<BonsaiEntity> entity = bonsaiDao.findById(uuid);
        return entity.map(BonsaiMapper::mapEntityToModel);
    }

    public List<Bonsai> findAll(String status) {
        List<BonsaiEntity> entities = new ArrayList<>();

        if(status == null){
            entities = bonsaiDao.findAll();
        } else{
            entities = bonsaiDao.findAllByStatus(status);
        }

        return BonsaiMapper.mapEntities(entities);
    }

    public Bonsai create(Bonsai bonsai) {
        BonsaiEntity bonsaiToCreate = BonsaiMapper.mapModelToEntity(bonsai);
        BonsaiEntity savedBonsai = bonsaiDao.save(bonsaiToCreate);
        return BonsaiMapper.mapEntityToModel(savedBonsai);
    }

    public Bonsai update(Bonsai bonsai) {
        BonsaiEntity bonsaiToUpdate = BonsaiMapper.mapModelToEntity(bonsai);
        BonsaiEntity savedBonsai = bonsaiDao.save(bonsaiToUpdate);
        return BonsaiMapper.mapEntityToModel(savedBonsai);
    }
    public void delete(UUID uuid){
        bonsaiDao.deleteById(uuid);
    }
    public Bonsai updateStatus(Bonsai bonsai){
        BonsaiEntity bonsaiToUpdateStatus = BonsaiMapper.mapModelToEntity(bonsai);
        BonsaiEntity savedBonsai = bonsaiDao.save(bonsaiToUpdateStatus);
        return BonsaiMapper.mapEntityToModel(savedBonsai);
    }

    public List<CareEvent> getAllEvents(UUID id) {
        return CareEventMapper.mapEntitiesToModels(careEventDao.findAllEventByID(id));
    }

    public CareEvent createEvent(CareEvent careEvent, UUID id) {
        CareEventEntity careEventToCreate = CareEventMapper.mapModelToEntity(careEvent);
        CareEventEntity created = careEventDao.save(careEventToCreate);
        return CareEventMapper.mapEntityToModel(created);
    }

    public List<CareEvent> getAllCareEventByStatut (UUID id,String statut) {
        return CareEventMapper.mapEntitiesToModels(careEventDao.findAllEventByStatut(statut, id));
    }
    public void deleteEvent (UUID eventUUID) {
        careEventDao.deleteById(eventUUID);
    }
    public Optional<CareEvent> findEventById(UUID uuid) {
        Optional<CareEventEntity> entity = careEventDao.findById(uuid);
        return entity.map(CareEventMapper::mapEntityToModel);
    }
}
