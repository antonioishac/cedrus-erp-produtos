package br.com.cactusdigital.cedrus.domains.repositories;

import br.com.cactusdigital.cedrus.domains.repositories.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

}
