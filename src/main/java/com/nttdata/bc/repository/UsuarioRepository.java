package com.nttdata.bc.repository;

import com.nttdata.bc.model.Usuario;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheMongoRepositoryBase<Usuario, Integer> {

}
