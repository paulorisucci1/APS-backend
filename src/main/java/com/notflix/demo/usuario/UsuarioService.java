package com.notflix.demo.usuario;

import com.notflix.demo.exceptions.EntityNotFoundException;
import com.notflix.demo.filme.Filme;

import com.notflix.demo.exceptions.EntityAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario create(Usuario usuario) {
        verifyIfUsuariosEmailAlreadyExists(usuario);
        return this.usuarioRepository.save(usuario);
    }

    private void verifyIfUsuariosEmailAlreadyExists(Usuario usuario) {
        final var foundUsuario = usuarioRepository.findByEmail(usuario.getEmail());

        if(Objects.nonNull(foundUsuario) && !Objects.equals(foundUsuario.getId(), usuario.getId())) {
            throw new EntityAlreadyExistException("Já existe um usuário cadastrado com o email informado");
        }
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

        verifyIfUsuariosEmailAlreadyExists(foundUsuario);

        return usuarioRepository.save(foundUsuario);
    }

    public void deleteUser(Long id) {

        this.usuarioRepository.deleteById(id);
    }
    
    public Usuario curtirFilme(Long id, Filme filmeCurtido) {
    	final var foundUsuario = findById(id);
    	
    	foundUsuario.curtirFilme(filmeCurtido);
    	
    	return foundUsuario;
    }
    
    public Usuario descurtirFilme(Long id, Filme filmeDescurtido) {
    	final var foundUsuario = findById(id);
    	
    	foundUsuario.descurtirFilme(filmeDescurtido);

    	return foundUsuario;
    }

}
