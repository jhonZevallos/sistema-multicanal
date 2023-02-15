package com.nttdata.bc.resource;

import java.util.List;

import com.nttdata.bc.model.Usuario;
import com.nttdata.bc.service.IUsuarioService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuario")
public class UsuarioResource {

    @Inject
    private IUsuarioService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response crearUsuario(Usuario obj) {
        Usuario usuario = this.service.insert(obj);
        return Response.ok(usuario).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response editarUsuario(@PathParam("id") Integer id, Usuario obj) {
        obj.setIdUsuario(id);
        Usuario usuario = this.service.update(obj);
        return Response.ok(usuario).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodo() {
        List<Usuario> usuarios = this.service.findAll();
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarXId(@PathParam("id") Integer id) {
        Usuario usuario = this.service.findById(id);
        return Response.ok(usuario).build();
    }

}
