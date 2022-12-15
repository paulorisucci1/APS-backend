package com.notflix.demo.filme;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Usuario, Long> {
}
