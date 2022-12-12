package com.notflix.demo.usuario;

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

    public void update(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

}
