package com.notflix.demo.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.notflix.demo.usuario.UsuarioController.PATH_USUARIOS;

@RestController
@RequestMapping(PATH_USUARIOS)
public class UsuarioController {

    static final String PATH_USUARIOS = "/api/usuarios";

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(this.usuarioService.create(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listUsers() {
        return ResponseEntity.ok(this.usuarioService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.usuarioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(this.usuarioService.updateUsuario(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        this.usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
