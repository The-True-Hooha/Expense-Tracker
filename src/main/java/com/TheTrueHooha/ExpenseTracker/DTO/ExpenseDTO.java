package com.TheTrueHooha.ExpenseTracker.DTO;

//the expense data transfer object (DTO) decouples the model layer with the api layer
//receives input from the client and maps this to the object model

import com.TheTrueHooha.ExpenseTracker.Model.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseDTO {

    private Long id;
    private String expenseName;
    private ItemCategory itemCategory;
    private BigDecimal expenseAmount;
}
