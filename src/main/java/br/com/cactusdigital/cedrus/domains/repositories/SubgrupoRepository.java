package br.com.cactusdigital.cedrus.domains.repositories;

import br.com.cactusdigital.cedrus.domains.repositories.entities.SubgrupoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubgrupoRepository extends JpaRepository<SubgrupoEntity, Integer> {
}
