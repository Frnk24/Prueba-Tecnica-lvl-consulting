package ms.clinica.gestion.app.configuration.service;

import ms.clinica.gestion.app.configuration.domain.Catalogo;
import ms.clinica.gestion.dto.model.catalogo.CatalogoFiltroRequest;
import ms.clinica.gestion.dto.model.catalogo.CatalogoRequest;


import java.util.List;

public interface CatalogoService {
    List<Catalogo> find(CatalogoFiltroRequest filtro);
    Catalogo get(Long catalogoId);
    void delete(Long catalogoId);
    void saveOrUpdate(CatalogoRequest request);
}
