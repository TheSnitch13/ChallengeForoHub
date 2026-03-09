package com.snitch.forohub.controller;

import com.snitch.forohub.domain.usuario.DatosAutenticacionUsuario;
import com.snitch.forohub.domain.usuario.Usuario;
import com.snitch.forohub.infra.security.DatosJWTToken;
import com.snitch.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public DatosJWTToken autenticar(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        var authenticationToken =
                new UsernamePasswordAuthenticationToken(datos.correoElectronico(), datos.contrasena());

        var auth = manager.authenticate(authenticationToken);
        var usuario = (Usuario) auth.getPrincipal();

        var jwtToken = tokenService.generarToken(usuario);

        return new DatosJWTToken(jwtToken);
    }
}