package com.nttdata.bc.service.impl;

import java.util.List;

import com.nttdata.bc.model.Usuario;
import com.nttdata.bc.repository.UsuarioRepository;
import com.nttdata.bc.service.IUsuarioService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements IUsuarioService {

    @Inject
    private UsuarioRepository repository;

    @Override
    public Usuario insert(Usuario obj) {
        this.repository.persist(obj);
        return obj;
    }

    @Override
    public Usuario update(Usuario obj) {
        Usuario usuario = this.findById(obj.getIdUsuario());

        usuario.setNumeroTarjeta(obj.getNumeroTarjeta());
        usuario.setFechaVencimiento(obj.getFechaVencimiento());
        usuario.setTipoDocumento(obj.getTipoDocumento());
        usuario.setNumeroDocumento(obj.getNumeroDocumento());
        return usuario;
    }

    @Override
    public List<Usuario> findAll() {

        return this.repository.listAll();
    }

    @Override
    public Usuario findById(Integer id) {
        Usuario usuario = this.repository.findById(id);
        if (usuario == null) {
            throw new NotFoundException("El usuario con id:" + id + "no existe");
        }
        return this.repository.findById(id);
    }

    @Override
    public void delete(Integer id) {

    }

}
