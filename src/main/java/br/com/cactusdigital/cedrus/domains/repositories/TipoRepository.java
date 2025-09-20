package br.com.cactusdigital.cedrus.domains.repositories;

import br.com.cactusdigital.cedrus.domains.repositories.entities.TipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<TipoEntity, Integer> {
}
