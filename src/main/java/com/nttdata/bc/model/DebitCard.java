package com.nttdata.bc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitCard {
    private Integer debitCardId;
    private Integer accountId;
    private String cardNumber;
    private String pin;
    private String expirationDate;
    private String cardValidationCode;
    private Boolean isActive;
}
