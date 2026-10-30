package br.com.uraniaquest.backend.infrastructure;

import br.com.uraniaquest.backend.datasource.repositories.CategoriaRepository;
import br.com.uraniaquest.backend.datasource.repositories.FlashcardRepository;
import br.com.uraniaquest.backend.domainmodel.entities.Categoria;
import br.com.uraniaquest.backend.domainmodel.entities.Flashcard;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final FlashcardRepository flashcardRepository;

    @Override
    public void run(String... args) {
        if (categoriaRepository.count() > 0) return;

        Categoria alimentacao = categoriaRepository.save(Categoria.builder()
                .titulo("Alimentação")
                .subtitulo("Conservação de alimentos e segurança alimentar")
                .resumoEducativo("A irradiação alimentar utiliza feixes controlados de energia para eliminar bactérias, fungos e pragas sem deixar o alimento radioativo.")
                .emoji("🍎")
                .build());

        Categoria saude = categoriaRepository.save(Categoria.builder()
                .titulo("Saúde")
                .subtitulo("Medicina nuclear e diagnóstico")
                .resumoEducativo("Radiofármacos permitem diagnosticar doenças precocemente e tratar tumores de forma localizada com apoio do Reator Multipropósito Brasileiro (RMB).")
                .emoji("🏥")
                .build());

        Categoria energia = categoriaRepository.save(Categoria.builder()
                .titulo("Energia")
                .subtitulo("Geração de energia limpa")
                .resumoEducativo("Usinas nucleares fornecem energia limpa de base 24 horas por dia, sem emitir gases de efeito estufa.")
                .emoji("⚡")
                .build());

        Categoria amazoniaAzul = categoriaRepository.save(Categoria.builder()
                .titulo("Amazônia Azul")
                .subtitulo("Tecnologia e defesa")
                .resumoEducativo("O Programa Nuclear da Marinha (PNM) e a AMAZUL desenvolvem tecnologia nacional para propulsão naval e soberania.")
                .emoji("🚢")
                .build());

        Categoria industria = categoriaRepository.save(Categoria.builder()
                .titulo("Indústria")
                .subtitulo("Aplicações industriais")
                .resumoEducativo("A gamagrafia industrial funciona como um raio-X de alta precisão para inspecionar soldas e estruturas sem danificá-las.")
                .emoji("⚙️")
                .build());

        Categoria pesquisa = categoriaRepository.save(Categoria.builder()
                .titulo("Pesquisa e Inovação")
                .subtitulo("Ciência para o futuro")
                .resumoEducativo("Técnicas nucleares preservam obras históricas e apoiam pesquisas avançadas em novos materiais e solos.")
                .emoji("🧪")
                .build());

        flashcardRepository.saveAll(List.of(
                Flashcard.builder()
                        .pergunta("Os alimentos tratados por irradiação ficam radioativos.")
                        .opcaoA("Verdadeiro")
                        .opcaoB("Falso")
                        .opcaoC("Depende do contexto")
                        .indiceCorreto(1)
                        .explicacao("Falso! O alimento apenas recebe ondas de energia para eliminar microrganismos, sem reter radioatividade.")
                        .moedasRecompensa(50)
                        .categoria(alimentacao)
                        .build(),
                Flashcard.builder()
                        .pergunta("Os radiofármacos produzidos com apoio da AMAZUL auxiliam no diagnóstico precoce do cancro.")
                        .opcaoA("Verdadeiro")
                        .opcaoB("Falso")
                        .opcaoC("Apenas em exames odontológicos")
                        .indiceCorreto(0)
                        .explicacao("Verdadeiro! Exames como PET-CT e cintilografias utilizam radiofármacos para mapear órgãos com precisão.")
                        .moedasRecompensa(50)
                        .categoria(saude)
                        .build(),
                Flashcard.builder()
                        .pergunta("O fumo branco que sai das torres das centrais nucleares é gás carbónico poluente.")
                        .opcaoA("Verdadeiro")
                        .opcaoB("Falso, é apenas vapor de água limpa")
                        .opcaoC("Depende do clima")
                        .indiceCorreto(1)
                        .explicacao("As torres de arrefecimento libertam exclusivamente vapor de água, sem queima de combustíveis fósseis.")
                        .moedasRecompensa(50)
                        .categoria(energia)
                        .build(),
                Flashcard.builder()
                        .pergunta("O Brasil domina o ciclo completo do combustível nuclear para fins pacíficos.")
                        .opcaoA("Verdadeiro")
                        .opcaoB("Falso")
                        .opcaoC("Importa 100% pronto")
                        .indiceCorreto(0)
                        .explicacao("Graças ao Programa Nuclear da Marinha e ao CTMSP, o Brasil domina a tecnologia de enriquecimento isotópico.")
                        .moedasRecompensa(50)
                        .categoria(amazoniaAzul)
                        .build(),
                Flashcard.builder()
                        .pergunta("A gamagrafia industrial permite inspecionar soldaduras de aviões e navios sem desmontar as peças.")
                        .opcaoA("Verdadeiro")
                        .opcaoB("Falso")
                        .opcaoC("Apenas em peças de madeira")
                        .indiceCorreto(0)
                        .explicacao("Por meio de ensaios não destrutivos com radiação gama, identificam-se fissuras internas com total segurança.")
                        .moedasRecompensa(50)
                        .categoria(industria)
                        .build(),
                Flashcard.builder()
                        .pergunta("A radiação gama pode descontaminar livros raros e obras de museus eliminando fungos e térmitas.")
                        .opcaoA("Verdadeiro")
                        .opcaoB("Falso, destrói as páginas")
                        .opcaoC("Apenas quadros a óleo")
                        .indiceCorreto(0)
                        .explicacao("A irradiação elimina pragas de acervos culturais inteiros sem usar produtos químicos líquidos.")
                        .moedasRecompensa(50)
                        .categoria(pesquisa)
                        .build()
        ));
    }
}