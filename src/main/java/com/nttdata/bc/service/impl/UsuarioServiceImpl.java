package com.nttdata.bc.service.impl;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.nttdata.bc.RestClient.IDebitCardRestClient;
import com.nttdata.bc.model.DebitCard;
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

    @Inject
    Logger log;

    @Inject
    @RestClient
    IDebitCardRestClient debitCardRestClient;

    @Override
    public Usuario insert(Usuario obj) {
        DebitCard debit = this.debitCardRestClient.findByCardNumber(obj.getNumeroTarjeta());
        boolean validacionStatus = this.debitCardRestClient.validateDebitCardAndClient(obj.getNumeroTarjeta(),
                obj.getTipoDocumento(), obj.getNumeroDocumento());
        if (validacionStatus == false) {
            throw new NotFoundException("Los datos del usuario ingresados no coinciden con la tarjeta");
        }
        if (debit == null) {
            throw new NotFoundException("El numero de tarjeta no existe");
        } else if (!debit.getExpirationDate().equals(obj.getFechaVencimiento())) {
            throw new NotFoundException("La fecha de vencimiento no coincide con la tarjeta");
        } else if (!debit.getCardValidationCode().equals(obj.getCodigoValidacion())) {
            throw new NotFoundException("El codigo de validacion no coincide con la tarjeta");
        } else if (!debit.getPin().equals(obj.getPin())) {
            throw new NotFoundException("El codigo pin ingresado es incorrecto");
        }
        this.repository.persist(obj);
        return obj;
    }

    @Override
    public Usuario update(Usuario obj) {
        Usuario usuario = this.findById(obj.getIdUsuario());
        DebitCard debit = this.debitCardRestClient.findByCardNumber(obj.getNumeroTarjeta());
        if (debit.getPin() != obj.getPin()) {
            throw new NotFoundException("El codigo pin ingresado es incorrecto");
        }
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
