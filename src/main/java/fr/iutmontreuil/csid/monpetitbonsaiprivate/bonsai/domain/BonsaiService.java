package fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain;

import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.CareEventMapper;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain.model.Bonsai;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain.model.CareEvent;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.exposition.dto.CareEventDTO;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.repository.BonsaiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BonsaiService {

    private final BonsaiRepository bonsaiRepository;

    public BonsaiService(BonsaiRepository bonsaiRepository) {
        this.bonsaiRepository = bonsaiRepository;
    }

    public Optional<Bonsai> findById(UUID uuid) {
        return bonsaiRepository.findById(uuid);
    }



    public List<Bonsai> findAll(String status) {
        return bonsaiRepository.findAll(status);
    }

    public Bonsai create(Bonsai bonsai) {
        return bonsaiRepository.create(bonsai);
    }

    public Bonsai update(Bonsai bonsai, UUID uuid) {
        Bonsai bonsaiFounded = this.findById(uuid).get();
        if (bonsai.getName() != null) {
            bonsaiFounded.setName(bonsai.getName());
        }
        if (bonsai.getSpecies() != null) {
            bonsaiFounded.setSpecies(bonsai.getSpecies());
        }
        return bonsaiRepository.update(bonsaiFounded);
    }
    public void delete(UUID uuid){
        bonsaiRepository.delete(uuid);
    }
    public Bonsai updateStatus(Bonsai bonsai,UUID uuid){
        Bonsai bonsaiFounded = this.findById(uuid).get();
        String tab[] = {"dead","alive","unknown"};
        for (String nom : tab){
            if (bonsai.getStatus().equals(nom)){
                bonsaiFounded.setStatus(bonsai.getStatus());
            }
        }
        return bonsaiRepository.updateStatus(bonsaiFounded);
    }
    public List<CareEventDTO> getCareEvents(UUID uuid) {
        return CareEventMapper.mapModelsToDTOS(bonsaiRepository.getAllEvents(uuid));
    }

    public CareEventDTO createEvent(CareEventDTO careEvent, UUID id) {
            return CareEventMapper.mapModelToDto(bonsaiRepository.createEvent(CareEventMapper.mapDTOtoModel(careEvent), id));

    }
    public List<CareEventDTO> getAllCareEventByStatut(UUID id, String statut) {
        return CareEventMapper.mapModelsToDTOS(bonsaiRepository.getAllCareEventByStatut(id,statut));
    }
    public void deleteEvent (UUID bonsaiUUID, UUID eventUUID) {
        bonsaiRepository.deleteEvent(eventUUID);
    }
    public Optional<CareEvent> findEventByID(UUID id) {
        return bonsaiRepository.findEventById(id);
    }
}

