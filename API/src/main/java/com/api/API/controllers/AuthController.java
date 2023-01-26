package com.api.API.controllers;

import com.api.API.models.Usuario;
import com.api.API.dao.UsuarioDao;
import com.api.API.utils.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController{
        @Autowired
        private UsuarioDao usuarioDao;

        @Autowired
        private JWTutil jwtutil;

        @RequestMapping(value = "/login/", method = RequestMethod.POST)
        public String ingresar(@RequestBody Usuario usuario) {

            Usuario usuarioLogueado = usuarioDao.verificarCredenciales(usuario);
            if (usuarioLogueado != null) {
                String tokenJwt = jwtutil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
                return tokenJwt;
                }
            return "ACCESS FAILED";
        }
}
