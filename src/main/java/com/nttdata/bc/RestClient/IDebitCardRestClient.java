package com.nttdata.bc.RestClient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.nttdata.bc.model.DebitCard;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/debit-cards")
@RegisterRestClient
public interface IDebitCardRestClient {
    @GET
    @Path("/findByCardNumber/{cardNumber}")
    DebitCard findByCardNumber(@PathParam("cardNumber") String cardNumber);

    @GET
    @Path("/validateclient/{debitCardNumber}/{documentType}/{documentNumber}")
    Boolean validateDebitCardAndClient(@PathParam("debitCardNumber") String debitCardNumber,
            @PathParam("documentType") String documentType, @PathParam("documentNumber") String documentNumber);
}
