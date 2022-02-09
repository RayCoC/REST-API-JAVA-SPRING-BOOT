package fr.iutmontreuil.csid.monpetitbonsaiprivate.owner.exposition;


import fr.iutmontreuil.csid.monpetitbonsaiprivate.owner.OwnerMapper;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.owner.domain.OwnerService;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.owner.domain.model.Owner;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.owner.exposition.dto.BonsaiDTO;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.owner.exposition.dto.OwnerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<OwnerDTO> findAll(){
       return ownerService.findAll();
    }

    @PostMapping()
    public ResponseEntity<OwnerDTO> createOwner(@RequestBody OwnerDTO ownerDTO) throws URISyntaxException {
        Owner ownerToCreate = ownerService.create(OwnerMapper.mapDtoToModelOwner(ownerDTO));
        OwnerDTO createdOwner = OwnerMapper.mapModelToDtoOwner(ownerToCreate);
        return ResponseEntity.created(new URI("")).body(createdOwner);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OwnerDTO> findOwnerByID(@PathVariable("uuid") UUID uuid) {
        Owner owner = ownerService.findById(uuid);
        if (owner!=null){
            return ResponseEntity.ok(OwnerMapper.mapModelToDtoOwner(owner));

        }
        else{
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping("/owner/{uuid}/bonsai/{bonsaiUUID}")
    public ResponseEntity<Void> transfererBonsai(@RequestBody OwnerDTO ownerDTO, @PathVariable("uuid") UUID uuidOwner, @PathVariable("bonsaiUUID") UUID bonsaiUUID) {
        ownerService.transfert(ownerDTO.getId(), uuidOwner, bonsaiUUID);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/owner/{uuid}/bonsai")
    public ResponseEntity<Void> ajouterBonsaiOwner(@PathVariable("uuid") UUID ownerID, @RequestBody List<String> bonsaisUUID) {
        ArrayList<UUID> uuid = new ArrayList<>();
        for (String uuids : bonsaisUUID) {
            uuid.add(UUID.fromString(uuids));
        }
        ownerService.addBonsaiToOwner(ownerID, uuid);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}/bonsais")
    public ResponseEntity<List<BonsaiDTO>> findAllBonsaiOfAnOwber(@PathVariable UUID id) {
        List<BonsaiDTO> bonsaiDTOS = ownerService.findAllBonsaisOfAnOwner(id);
        return ResponseEntity.ok(bonsaiDTOS);
    }
}
