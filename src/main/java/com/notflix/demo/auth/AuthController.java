package com.notflix.demo.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.notflix.demo.usuario.Usuario;
import com.notflix.demo.usuario.UsuarioService;

import java.util.List;

import static com.notflix.demo.auth.AuthController.PATH_AUTH;

@RestController
@RequestMapping(PATH_AUTH)
public class AuthController {
    static final String PATH_AUTH = "/auth";

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(this.usuarioService.create(usuario));
    }

}


