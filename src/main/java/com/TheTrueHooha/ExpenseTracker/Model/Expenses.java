package com.TheTrueHooha.ExpenseTracker.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Expense-Tracker")
public class Expenses {

    @Id
    private Long id;

    @Field("item_name") @Indexed(unique = true)
    private String item;

    @Field("category")
    private ItemCategory itemCategory;

    @Field("amount")
    private BigDecimal amount;
}
