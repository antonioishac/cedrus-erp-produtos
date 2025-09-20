package br.com.cactusdigital.cedrus.domains.services;

import br.com.cactusdigital.cedrus.api.request.GrupoRequest;
import br.com.cactusdigital.cedrus.domains.services.dtos.GrupoDTO;

public interface GrupoService {

    GrupoDTO salvar(GrupoRequest request);
    void existeGrupo(String nome);

}
