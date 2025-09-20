package br.com.cactusdigital.cedrus.domains.repositories;

import br.com.cactusdigital.cedrus.domains.repositories.entities.GrupoTributarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoTribitarioRepository extends JpaRepository<GrupoTributarioEntity, Integer> {
}
