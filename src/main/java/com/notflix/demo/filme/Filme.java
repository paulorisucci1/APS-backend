package com.notflix.demo.filme;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.notflix.demo.usuario.Usuario;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(name = "url_imagem")
    private String urlImagem;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @ManyToMany(mappedBy="filmesCurtidos")
    private List<Usuario> usuarios = new ArrayList<>();
    
    public void update(Filme filme) {
        this.titulo = filme.getTitulo();
        this.descricao = filme.getDescricao();
        this.duracao = filme.getDuracao();
        this.dataLancamento = filme.getDataLancamento();
        this.urlImagem = filme.getUrlImagem();
    }

	public LocalDate getDataLancamento() {
		// TODO Auto-generated method stub
		return this.dataLancamento;
	}

	public String getTitulo() {
		// TODO Auto-generated method stub
		return this.titulo;
	}

	public String getDescricao() {
		// TODO Auto-generated method stub
		return this.descricao;
	}
	
	public int getDuracao() {
		return this.duracao;
	}

	public Long getId() {
		return id;
	}

}
