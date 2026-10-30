package br.com.uraniaquest.backend.services;

import br.com.uraniaquest.backend.resources.dtos.CategoriaDTO;
import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> listarTodas();
    CategoriaDTO buscarPorId(Long id);
    List<CategoriaDTO> buscarPorTexto(String termo);
    CategoriaDTO criar(CategoriaDTO dto);
    CategoriaDTO atualizar(Long id, CategoriaDTO dto);
    void deletar(Long id);
}