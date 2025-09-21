package br.com.cactusdigital.cedrus.domains.repositories;

import br.com.cactusdigital.cedrus.domains.repositories.entities.UnidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnidadeRepository extends JpaRepository<UnidadeEntity, Integer>, JpaSpecificationExecutor<UnidadeEntity> {

    Optional<UnidadeEntity> findBySigla(String sigla);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UnidadeEntity u " +
            "WHERE LOWER(u.sigla) = LOWER(:sigla) AND u.id <> :id")
    boolean existsBySiglaIgnoreCaseAndIdNot(@Param("sigla") String sigla, @Param("id") Integer id);


}
