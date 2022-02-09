package fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Component
public interface BonsaiDao extends JpaRepository<BonsaiEntity, UUID> {

    @Query(value = "Select * from bonsai where bonsai.status =:status",nativeQuery = true)
    List<BonsaiEntity> findAllByStatus(@Param("status") String status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE bonsai set owner_id=:owner_id where id=:id", nativeQuery = true)
    void transfert(@Param("owner_id") UUID ownerId, @Param("id") UUID bonsaiID);

}
