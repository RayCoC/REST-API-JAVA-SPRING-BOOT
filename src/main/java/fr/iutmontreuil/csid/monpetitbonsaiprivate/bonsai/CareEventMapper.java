package fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai;

import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain.model.CareEvent;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.exposition.dto.CareEventDTO;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence.BonsaiEntity;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence.CareEventEntity;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence.OwnerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CareEventMapper {

    public static CareEvent mapDTOtoModel(CareEventDTO careEventDTO) {
        return new CareEvent(careEventDTO.getId(), careEventDTO.getCareDate(), careEventDTO.getOwner(), careEventDTO.getBonsai(), careEventDTO.getCareType());
    }

    public static CareEventEntity mapModelToEntity(CareEvent careEvent) {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setId(careEvent.getOwner());
        BonsaiEntity bonsaiEntity = new BonsaiEntity();
        bonsaiEntity.setId(careEvent.getBonsai());
        CareEventEntity careEventEntity = new CareEventEntity();
        careEventEntity.setId(careEvent.getId());
        careEventEntity.setCareDate(careEvent.getCareDate());
        careEventEntity.setOwner(ownerEntity);
        careEventEntity.setBonsai(bonsaiEntity);
        careEventEntity.setCareType(careEvent.getCareType());
        return careEventEntity;
    }


    public static CareEvent mapEntityToModel(CareEventEntity careEventEntity) {
        return new CareEvent(careEventEntity.getId(), careEventEntity.getCareDate(), careEventEntity.getOwner().getId(), careEventEntity.getBonsai().getId(), careEventEntity.getCareType());
    }

    public static CareEventDTO mapModelToDto(CareEvent careEvent) {
        return new CareEventDTO(careEvent.getId(), careEvent.getCareDate(), careEvent.getOwner(),careEvent.getBonsai(), careEvent.getCareType());
    }

    public static List<CareEventDTO> mapModelsToDTOS(List<CareEvent> careEvents){
        List<CareEventDTO> careEventDtos = new ArrayList<>();
        careEventDtos = careEvents.stream().map(x -> mapModelToDto(x)).collect(Collectors.toList());
        return careEventDtos;
    }

    public static List<CareEvent> mapEntitiesToModels(List<CareEventEntity> careEventEntities){
        List<CareEvent> careEvents = new ArrayList<>();
        careEvents = careEventEntities.stream().map(x -> mapEntityToModel(x)).collect(Collectors.toList());
        return careEvents;
    }
}
