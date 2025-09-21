package br.com.cactusdigital.cedrus.domains.repositories.specification;

import br.com.cactusdigital.cedrus.api.request.filter.UnidadeFiltro;
import br.com.cactusdigital.cedrus.domains.repositories.entities.UnidadeEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UnidadeSpecification {

    public static Specification<UnidadeEntity> filtrar(UnidadeFiltro filtro) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.sigla() != null && !filtro.sigla().isBlank()) {
                predicates.add(builder.like(builder.lower(root.get("sigla")), "%" + filtro.sigla().toLowerCase() + "%"));
            }

            if (filtro.descricao() != null && !filtro.descricao().isBlank()) {
                predicates.add(builder.like(builder.lower(root.get("descricao")), "%" + filtro.descricao().toLowerCase() + "%"));
            }

            if (filtro.podeFracionar() != null && !filtro.podeFracionar().isBlank()) {
                predicates.add(builder.equal(root.get("podeFracionar"), filtro.podeFracionar()));
            }

            return predicates.isEmpty()
                    ? builder.conjunction()
                    : builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
