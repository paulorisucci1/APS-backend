package com.notflix.demo.usuario;

import java.util.ArrayList;
import java.util.List;

import com.notflix.demo.exceptions.EntityNotFoundException;
import com.notflix.demo.filme.Filme;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "usuario")
@Getter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @ManyToMany
    private List<Filme> filmesCurtidos = new ArrayList<>();

    public void update(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
    }

	private String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	public String getEmail() {
		return this.email;
	}

	public String getNome() {
		return this.nome;
	}

	public List<Filme> getCurtidos() {
		return filmesCurtidos;
	}
	
	public List<Filme> curtirFilme(Filme filme) {
		if(filmesCurtidos.contains(filme)) {
			throw new EntityNotFoundException("Filme já curtido!");
		}
		filmesCurtidos.add(filme);
		return this.filmesCurtidos;
	}
	
	public List<Filme> descurtirFilme(Filme filme) {
		if(!filmesCurtidos.contains(filme)) {
			throw new EntityNotFoundException("Filme não encontrado!");
		}
		filmesCurtidos.remove(filme);
		return filmesCurtidos;
	}

}
