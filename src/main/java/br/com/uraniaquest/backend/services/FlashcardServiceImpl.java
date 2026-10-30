package br.com.uraniaquest.backend.services;

import br.com.uraniaquest.backend.datasource.repositories.CategoriaRepository;
import br.com.uraniaquest.backend.datasource.repositories.FlashcardRepository;
import br.com.uraniaquest.backend.domainmodel.entities.Categoria;
import br.com.uraniaquest.backend.domainmodel.entities.Flashcard;
import br.com.uraniaquest.backend.resources.dtos.FlashcardDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlashcardServiceImpl implements FlashcardService {

    private final FlashcardRepository flashcardRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    public List<FlashcardDTO> listarTodos() {
        return flashcardRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public FlashcardDTO buscarPorId(Long id) {
        Flashcard flashcard = flashcardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flashcard não encontrado com o ID: " + id));
        return toDTO(flashcard);
    }

    @Override
    public List<FlashcardDTO> buscarPorTextoNaPergunta(String termo) {
        return flashcardRepository.findByPerguntaContainingIgnoreCase(termo)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<FlashcardDTO> listarPorCategoria(Long categoriaId) {
        return flashcardRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public FlashcardDTO criar(FlashcardDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + dto.getCategoriaId()));

        Flashcard novo = Flashcard.builder()
                .pergunta(dto.getPergunta())
                .opcaoA(dto.getOpcaoA())
                .opcaoB(dto.getOpcaoB())
                .opcaoC(dto.getOpcaoC())
                .indiceCorreto(dto.getIndiceCorreto())
                .explicacao(dto.getExplicacao())
                .moedasRecompensa(dto.getMoedasRecompensa())
                .categoria(categoria)
                .build();

        return toDTO(flashcardRepository.save(novo));
    }

    @Override
    public FlashcardDTO atualizar(Long id, FlashcardDTO dto) {
        Flashcard existente = flashcardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flashcard não encontrado com o ID: " + id));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + dto.getCategoriaId()));

        existente.setPergunta(dto.getPergunta());
        existente.setOpcaoA(dto.getOpcaoA());
        existente.setOpcaoB(dto.getOpcaoB());
        existente.setOpcaoC(dto.getOpcaoC());
        existente.setIndiceCorreto(dto.getIndiceCorreto());
        existente.setExplicacao(dto.getExplicacao());
        existente.setMoedasRecompensa(dto.getMoedasRecompensa());
        existente.setCategoria(categoria);

        return toDTO(flashcardRepository.save(existente));
    }

    @Override
    public void deletar(Long id) {
        if (!flashcardRepository.existsById(id)) {
            throw new EntityNotFoundException("Flashcard não encontrado com o ID: " + id);
        }
        flashcardRepository.deleteById(id);
    }

    private FlashcardDTO toDTO(Flashcard entity) {
        return FlashcardDTO.builder()
                .id(entity.getId())
                .pergunta(entity.getPergunta())
                .opcaoA(entity.getOpcaoA())
                .opcaoB(entity.getOpcaoB())
                .opcaoC(entity.getOpcaoC())
                .indiceCorreto(entity.getIndiceCorreto())
                .explicacao(entity.getExplicacao())
                .moedasRecompensa(entity.getMoedasRecompensa())
                .categoriaId(entity.getCategoria().getId())
                .categoriaTitulo(entity.getCategoria().getTitulo())
                .build();
    }
}