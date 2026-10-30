package br.com.uraniaquest.backend.services;

import br.com.uraniaquest.backend.datasource.repositories.CategoriaRepository;
import br.com.uraniaquest.backend.domainmodel.entities.Categoria;
import br.com.uraniaquest.backend.resources.dtos.CategoriaDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;

    @Override
    public List<CategoriaDTO> listarTodas() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + id));
        return toDTO(categoria);
    }

    @Override
    public List<CategoriaDTO> buscarPorTexto(String termo) {
        return repository.findByTituloContainingIgnoreCaseOrSubtituloContainingIgnoreCase(termo, termo)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public CategoriaDTO criar(CategoriaDTO dto) {
        Categoria nova = toEntity(dto);
        nova.setId(null);
        return toDTO(repository.save(nova));
    }

    @Override
    public CategoriaDTO atualizar(Long id, CategoriaDTO dto) {
        Categoria existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + id));

        existente.setTitulo(dto.getTitulo());
        existente.setSubtitulo(dto.getSubtitulo());
        existente.setResumoEducativo(dto.getResumoEducativo());
        existente.setEmoji(dto.getEmoji());

        return toDTO(repository.save(existente));
    }

    @Override
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada com o ID: " + id);
        }
        repository.deleteById(id);
    }

    private CategoriaDTO toDTO(Categoria entity) {
        return CategoriaDTO.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .subtitulo(entity.getSubtitulo())
                .resumoEducativo(entity.getResumoEducativo())
                .emoji(entity.getEmoji())
                .build();
    }

    private Categoria toEntity(CategoriaDTO dto) {
        return Categoria.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .subtitulo(dto.getSubtitulo())
                .resumoEducativo(dto.getResumoEducativo())
                .emoji(dto.getEmoji())
                .build();
    }
}