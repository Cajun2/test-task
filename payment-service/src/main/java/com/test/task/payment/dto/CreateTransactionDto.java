package com.test.task.payment.dto;

import com.test.task.payment.validators.annotations.TransactionValueConstrain;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionDto {

    @NotNull
    @Size(min = 1, max = 255)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 255)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 255)
    private String  patronymic;

    @NotNull
    @TransactionValueConstrain
    private double value;
}
