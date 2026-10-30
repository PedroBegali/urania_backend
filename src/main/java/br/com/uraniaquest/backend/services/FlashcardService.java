package br.com.uraniaquest.backend.services;

import br.com.uraniaquest.backend.resources.dtos.FlashcardDTO;
import java.util.List;

public interface FlashcardService {
    List<FlashcardDTO> listarTodos();
    FlashcardDTO buscarPorId(Long id);
    List<FlashcardDTO> buscarPorTextoNaPergunta(String termo);
    List<FlashcardDTO> listarPorCategoria(Long categoriaId);
    FlashcardDTO criar(FlashcardDTO dto);
    FlashcardDTO atualizar(Long id, FlashcardDTO dto);
    void deletar(Long id);
}