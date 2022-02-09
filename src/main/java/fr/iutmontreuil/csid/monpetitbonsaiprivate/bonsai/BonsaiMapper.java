package fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai;

import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain.model.Bonsai;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.exposition.dto.BonsaiDTO;
import fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence.BonsaiEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// TODO: complete all the methods !
public class BonsaiMapper {

    public static BonsaiDTO mapModelToDto(Bonsai bonsai) {
        BonsaiDTO bonsailDTO =  new BonsaiDTO();

        bonsailDTO.setId(bonsai.getId());
        bonsailDTO.setName(bonsai.getName());
        bonsailDTO.setSpecies(bonsai.getSpecies());
        bonsailDTO.setStatus(bonsai.getStatus());
        bonsailDTO.setAge(bonsai.getAge());
        return bonsailDTO;

    }

    public static List<BonsaiDTO> mapModels(List<Bonsai> bonsais) {
        List<BonsaiDTO> liste= new ArrayList<>();
        liste = bonsais.stream().map(x -> mapModelToDto(x)).collect(Collectors.toList());
        return liste;
    }

    public static Bonsai mapDtoToModel(BonsaiDTO bonsaiDTO) {
        UUID id = bonsaiDTO.getId();
        String name = bonsaiDTO.getName();
        String species = bonsaiDTO.getSpecies();
        String status = bonsaiDTO.getStatus();
        Integer age = bonsaiDTO.getAge();
        Bonsai b = new Bonsai(id,name,species,status,age);
        return b;
    }

    public static List<Bonsai> mapDtos(List<BonsaiDTO> bonsaiDTOS) {
        List <Bonsai> listBonsaiDTOS = new ArrayList<>();
        listBonsaiDTOS = bonsaiDTOS.stream().map(x -> mapDtoToModel(x)).collect(Collectors.toList());
        return listBonsaiDTOS;
    }

    public static BonsaiEntity mapModelToEntity(Bonsai bonsai) {
        BonsaiEntity bonsaidEntity = new BonsaiEntity();
        bonsaidEntity.setId(bonsai.getId());
        bonsaidEntity.setName(bonsai.getName());
        bonsaidEntity.setSpecies(bonsai.getSpecies());
        bonsaidEntity.setStatus(bonsai.getStatus());
        bonsaidEntity.setAge(bonsai.getAge());
        return bonsaidEntity;
    }

    public static List<BonsaiEntity> mapModelToDtos(List<Bonsai> bonsais) {
        return Collections.emptyList();
    }

    public static Bonsai mapEntityToModel(BonsaiEntity bonsaiEntity) {
        Bonsai b = new Bonsai(bonsaiEntity.getId(), bonsaiEntity.getName(), bonsaiEntity.getSpecies(),bonsaiEntity.getStatus(), bonsaiEntity.getAge());
        return b;
    }

    public static List<Bonsai> mapEntities(List<BonsaiEntity> bonsaiEntities) {
        List<Bonsai> liste = bonsaiEntities.stream().map(x -> mapEntityToModel(x)).collect(Collectors.toList());
        return liste;
    }
}
