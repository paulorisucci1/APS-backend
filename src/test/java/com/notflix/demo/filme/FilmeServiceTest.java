package com.notflix.demo.filme;

import com.notflix.demo.exceptions.EntityAlreadyExistException;
import com.notflix.demo.exceptions.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class FilmeServiceTest {

    private Filme filme;

    @InjectMocks
    private FilmeService filmeService;

    @Mock
    private FilmeRepository filmeRepository;

    @Before
    public void setup() {

        this.filme = Filme.builder()
                .id(1L)
                .titulo("O rei leão")
                .descricao("Um leão rei")
                .urlImagem("qualquerUrl.png")
                .dataLancamento(LocalDate.now())
                .build();
    }

    @Test
    public void shouldFindFilmById() {

        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(this.filme));

        final var foundFilm = filmeService.findById(this.filme.getId());

        validateFilme(foundFilm);
    }

    @Test
    public void shouldThrowAnExceptionWhenFilmNotFound() {

        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> filmeService.findById(this.filme.getId())).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void shouldCreateFilmeSuccessfully() {

        Mockito.when(filmeRepository.save(Mockito.any(Filme.class))).thenReturn(this.filme);

        Filme createdFilm = filmeService.create(this.filme);

        validateFilme(createdFilm);
    }

    @Test
    public void shouldListFilmesSuccessfully() {

        Mockito.when(filmeRepository.findAll()).thenReturn(List.of(this.filme));

        List<Filme> filmeList = filmeService.list();

        filmeList.forEach(this::validateFilme);
    }

    @Test
    public void shouldDelete() {
        filmeService.deleteFilme(1L);
        Mockito.verify(filmeRepository).deleteById(Mockito.anyLong());
    }

    @Test
    public void shouldUpdateFilmSuccessfully() {

        final var originalFilm = Filme.builder()
                .id(2L)
                .build();

        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(originalFilm));
        Mockito.when(filmeRepository.save(Mockito.any(Filme.class))).thenReturn(this.filme);

        final var updatedFilme = filmeService.updateFilme(originalFilm.getId(), this.filme);

        validateFilme(updatedFilme);

    }

    @Test
    public void shouldVerifyIfFilmesTituloAlreadyExistsSuccessfully(){
        Mockito.when(filmeRepository.findByTitulo(Mockito.anyString())).thenReturn(null);

        Assertions.assertThatNoException().isThrownBy(() -> filmeService.verifyIfFilmesTituloAlreadyExists(this.filme));
    }

    @Test
    public void shouldThrowExceptionWhenFilmesTituloAlreadyExists(){
        final var newFilme = Filme.builder()
                        .titulo(filme.getTitulo())
                        .build();

        Mockito.when(filmeRepository.findByTitulo(Mockito.anyString())).thenReturn(this.filme);

        Assertions.assertThatThrownBy(() -> filmeService.verifyIfFilmesTituloAlreadyExists(newFilme))
                .isInstanceOf(EntityAlreadyExistException.class);
    }

    private void validateFilme(Filme film) {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(film.getId()).isEqualTo(this.filme.getId());
            softly.assertThat(film.getTitulo()).isEqualTo(this.filme.getTitulo());
            softly.assertThat(film.getDuracao()).isEqualTo(this.filme.getDuracao());
            softly.assertThat(film.getDescricao()).isEqualTo(this.filme.getDescricao());
            softly.assertThat(film.getUrlImagem()).isEqualTo(this.filme.getUrlImagem());
            softly.assertThat(film.getDataLancamento()).isEqualTo(this.filme.getDataLancamento());
        });

    }
}
