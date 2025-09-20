package br.com.cactusdigital.cedrus.domains.services;

import br.com.cactusdigital.cedrus.api.request.UnidadeRequest;
import br.com.cactusdigital.cedrus.domains.services.dtos.UnidadeDTO;

public interface UnidadeService {

    UnidadeDTO salvar(UnidadeRequest request);
    void verificaSeExiste(String sigla);
}
