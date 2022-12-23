package com.notflix.demo.filme;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class FilmeTest {

    private Filme filme;

    @BeforeEach
    void setup() {
        this.filme = Filme.builder()
                .id(1L)
                .titulo("O rei leão")
                .descricao("Um leão rei")
                .urlImagem("qualquerUrl.png")
                .dataLancamento(LocalDate.now())
                .build();
    }

    @Test
    void shouldUpdateFilmeSuccessfully() {
        final var novoFilme = new Filme();

        novoFilme.update(this.filme);

        SoftAssertions.assertSoftly(
                softly -> {
                    softly.assertThat(novoFilme.getTitulo()).isEqualTo(this.filme.getTitulo());
                    softly.assertThat(novoFilme.getDescricao()).isEqualTo(this.filme.getDescricao());
                    softly.assertThat(novoFilme.getDuracao()).isEqualTo(this.filme.getDuracao());
                    softly.assertThat(novoFilme.getDataLancamento()).isEqualTo(this.filme.getDataLancamento());
                    softly.assertThat(novoFilme.getUrlImagem()).isEqualTo(this.filme.getUrlImagem());

                    softly.assertThat(novoFilme.getId()).isNotEqualTo(this.filme.getId());
                }
        );
    }
}
