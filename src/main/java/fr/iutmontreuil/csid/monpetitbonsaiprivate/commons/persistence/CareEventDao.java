package fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence;

import fr.iutmontreuil.csid.monpetitbonsaiprivate.bonsai.domain.model.CareEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CareEventDao extends JpaRepository<CareEventEntity, UUID> {

    @Query(value = "select *  from care_event where bonsai_id =:id", nativeQuery = true)
    List<CareEventEntity> findAllEventByID(@Param("id") UUID val);

    @Query(value = "select *  from care_event where bonsai_id =:id and care_type =:type", nativeQuery = true)
    List<CareEventEntity> findAllEventByStatut(@Param("type") String type, @Param("id") UUID val);

    @Query(value = "select *  FROM care_event where bonsai_id =:bonsaiID and id =:idEvent", nativeQuery = true)
    CareEventEntity selectByEventAndBonsai (@Param("bonsaiID") UUID bonsaiID, @Param("idEvent") UUID idEvent);
}