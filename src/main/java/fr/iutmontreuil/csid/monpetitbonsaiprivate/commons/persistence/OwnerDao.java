package fr.iutmontreuil.csid.monpetitbonsaiprivate.commons.persistence;

import fr.iutmontreuil.csid.monpetitbonsaiprivate.owner.domain.model.Bonsai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.UUID;
@Component
public interface OwnerDao extends JpaRepository<OwnerEntity, UUID> {
    @Query(value = "SELECT * FROM bonsai where bonsai.id=:id", nativeQuery = true)
    Bonsai findBonsaiById(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE bonsai set owner_id=:owner_id where id=:id", nativeQuery = true)
    void transfert(@Param("owner_id") UUID ownerId, @Param("id") UUID bonsaiID);

}
