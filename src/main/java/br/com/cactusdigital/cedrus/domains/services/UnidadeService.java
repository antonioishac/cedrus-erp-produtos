package br.com.cactusdigital.cedrus.domains.services;

import br.com.cactusdigital.cedrus.api.request.UnidadeRequest;
import br.com.cactusdigital.cedrus.api.request.filter.UnidadeFiltro;
import br.com.cactusdigital.cedrus.domains.repositories.entities.UnidadeEntity;
import br.com.cactusdigital.cedrus.domains.services.dtos.UnidadeDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface UnidadeService {

    UnidadeDTO salvar(UnidadeRequest request);
    void verificaSeExiste(String sigla);
    Page<UnidadeDTO> buscar(UnidadeFiltro filtro, int page, int size);
    UnidadeDTO atualizar(Integer id, UnidadeRequest request);
    UnidadeDTO atualizarParcial(Integer id, Map<String, Object> campos, HttpServletRequest request);
    UnidadeEntity existeUnidade(Integer id);
    void deletar(Integer id);
}
