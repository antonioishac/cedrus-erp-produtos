package br.com.cactusdigital.cedrus.domains.repositories;

import br.com.cactusdigital.cedrus.domains.repositories.entities.GrupoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoEntity, Integer> {

    Optional<GrupoEntity> findByNome(String nome);

}
