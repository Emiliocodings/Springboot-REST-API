package com.api.API.dao;
import com.api.API.models.Usuario;
import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();

    void eliminar(Long id);

   void registrar(Usuario usuario);

   Usuario verificarCredenciales(Usuario usuario);
}
