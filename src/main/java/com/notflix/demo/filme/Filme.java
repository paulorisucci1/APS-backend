package com.notflix.demo.filme;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "filme")
@Getter
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    // Duração em minutos
    @Column(name = "duracao")
    private int duracao;

    @Column(name = "dataLancamento")
    private Date dataLancamento;

    public void update(Filme filme) {
        this.titulo = filme.getTitulo();
        this.descricao = filme.getDescricao();
        this.duracao = filme.getDuracao();
        this.dataLancamento = filme.getDataLancamento();
    }

}
