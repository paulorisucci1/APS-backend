package com.notflix.demo.usuario;

import com.notflix.demo.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario create(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    public List<Usuario> list() {
        return this.usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public Usuario updateUsuario(Long id, Usuario updatedUsuario) {

        final var foundUsuario = findById(id);

        foundUsuario.update(updatedUsuario);

        return usuarioRepository.save(foundUsuario);
    }

    public void deleteUser(Long id) {

        this.usuarioRepository.deleteById(id);
    }

}
