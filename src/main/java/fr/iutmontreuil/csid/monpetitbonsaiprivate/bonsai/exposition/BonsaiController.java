package fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.exposition;

import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.BonsaiMapper;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.CareEventMapper;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain.BonsaiService;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain.model.Bonsai;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain.model.CareEvent;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.exposition.dto.BonsaiDTO;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.exposition.dto.CareEventDTO;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence.CareType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/bonsais")
public class BonsaiController {

    private final BonsaiService bonsaiService;

    public BonsaiController(BonsaiService bonsaiService) {
        this.bonsaiService = bonsaiService;
    }

    @GetMapping
    public List<BonsaiDTO> findAllBonsais(@RequestParam(required = false,name = "status") String status) {
        List<Bonsai> bonsais = bonsaiService.findAll(status);
        return BonsaiMapper.mapModels(bonsais);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<BonsaiDTO> findBonsaiById(@PathVariable("uuid") UUID uuid) {
        Optional<Bonsai> bo = bonsaiService.findById(uuid);
        return ResponseEntity.ok(BonsaiMapper.mapModelToDto(bo.get()));

    }

    @PostMapping()
    public ResponseEntity<BonsaiDTO> createBonsai(@RequestBody BonsaiDTO bonsaiDTO) throws URISyntaxException {
        Bonsai bonsaiToCreate = bonsaiService.create(BonsaiMapper.mapDtoToModel(bonsaiDTO));
        BonsaiDTO createdBonsai = BonsaiMapper.mapModelToDto(bonsaiToCreate);
        return ResponseEntity.created(new URI("")).body(createdBonsai);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<BonsaiDTO> updateBonsai(@RequestBody BonsaiDTO bonsaiDTO,@PathVariable("uuid") UUID uuid ) {
        Bonsai bonsaiToUpdate = bonsaiService.update(BonsaiMapper.mapDtoToModel(bonsaiDTO),uuid);
        BonsaiDTO updateBonsai = BonsaiMapper.mapModelToDto(bonsaiToUpdate);
        return ResponseEntity.ok(updateBonsai);
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteBonsai(@PathVariable("uuid") UUID uuid) {
        bonsaiService.delete(uuid);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<Void> changeStatus(@RequestBody BonsaiDTO bonsaiDTO ,@PathVariable("uuid") UUID uuid) {
        Bonsai statusToUpdate = bonsaiService.updateStatus(BonsaiMapper.mapDtoToModel(bonsaiDTO),uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bonsais/{id}/care-event")
    public ResponseEntity<List<CareEventDTO>> tousLesEventsBonsai(@PathVariable("id") UUID id) {
        List<CareEventDTO> allEvent = bonsaiService.getCareEvents(id);
        return ResponseEntity.ok(allEvent);
    }

    @PostMapping("/bonsai/{BonsaiID}")
    public ResponseEntity<CareEventDTO> createEvent(@PathVariable("BonsaiID") UUID  id, @RequestBody CareEventDTO careEventDTO) {
        CareEvent careEvent = CareEventMapper.mapDTOtoModel(careEventDTO);
        if (careEvent.getCareType() == CareType.PRUNING || careEvent.getCareType() == CareType.REPORTING || careEvent.getCareType() == CareType.WATERING) {
            CareEventDTO event = bonsaiService.createEvent(careEventDTO, id);
            return ResponseEntity.ok(event);
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/bonsai/{id}/care-event")
    public ResponseEntity<List<CareEventDTO>> typeCareEvent (@PathVariable("id") UUID id, @RequestParam (required = false,name = "type") String type) {
        List<CareEventDTO> allCareEventByStatut = bonsaiService.getAllCareEventByStatut(id, type);
        return ResponseEntity.ok(allCareEventByStatut);
    }
    @DeleteMapping("/bonsais/{id}/care-events/{event-id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") UUID bonsaiUUID, @PathVariable("event-id") UUID eventID) {
        Optional<Bonsai> b = bonsaiService.findById(bonsaiUUID);
        Optional<CareEvent> c = bonsaiService.findEventByID(eventID);
        if (b==null || c.get().getBonsai().equals(bonsaiUUID)) {
            return ResponseEntity.noContent().build();
        }
        bonsaiService.deleteEvent(bonsaiUUID, eventID);
        return ResponseEntity.noContent().build();
    }

}
