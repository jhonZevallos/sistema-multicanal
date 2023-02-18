package com.nttdata.bc.model;

import java.time.LocalDate;

import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "Usuario")
public class Usuario extends PanacheMongoEntityBase {

    @BsonId
    private Integer idUsuario;
    private String numeroTarjeta;
    private Integer contraseña;
    private String fechaVencimiento;
    private String codigoValidacion;
    private String tipoDocumento;
    private String numeroDocumento;
    private String pin;
}
