package com.api.API.controllers;

import com.api.API.dao.UsuarioDao;
import com.api.API.models.Usuario;
import com.api.API.utils.JWTutil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTutil jwtutil;

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Blerckiomansk");
        usuario.setApellido("Tramnbesolipn");
        usuario.setEmail("Blerckiomansk.Tramnbesolipn@ymail.com");
        usuario.setTelefono("6135490249");
        return usuario;
    }

    @RequestMapping(value = "/usuarios/", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
        if (!validarToken(token))
        {return null;}
        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtutil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "/usuarios/", method = RequestMethod.POST)
    public void registrarUsuarios(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Authorization") String token,
                         @PathVariable Long id) {
        if (!validarToken(token))
        {return;}
        usuarioDao.eliminar(id);
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }
}
