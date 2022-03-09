package com.TheTrueHooha.ExpenseTracker.Services;

import com.TheTrueHooha.ExpenseTracker.DTO.ExpenseDTO;
import com.TheTrueHooha.ExpenseTracker.Exception.ExpenseNotFoundException;
import com.TheTrueHooha.ExpenseTracker.Model.Expenses;
import com.TheTrueHooha.ExpenseTracker.Repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private Expenses mapFromDto(ExpenseDTO expenses) {

        return Expenses.builder()
                .itemCategory(expenses.getItemCategory())
                .item(expenses.getExpenseName())
                .amount(expenses.getExpenseAmount())
                .build();
    }

    public Long addExpense (ExpenseDTO expenseDTO) {
        Expenses expenses = mapFromDto(expenseDTO);
        return expenseRepository.insert(expenses).getId();
    }


    private ExpenseDTO mapToDto (Expenses dto) {
        return ExpenseDTO.builder()
                .id(dto.getId())
                .expenseName(dto.getItem())
                .itemCategory(dto.getItemCategory())
                .expenseAmount(dto.getAmount())
                .build();
    }

    public ExpenseDTO getExpense(String name){
        Expenses expenses = expenseRepository.findByName(name).orElseThrow(() ->
                new ExpenseNotFoundException(String.format("error, cannot find expense item - %s", name)));
        return mapToDto(expenses);
    }

    public List<ExpenseDTO> getAllItems() {
        return expenseRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void updateItem(Expenses expenseDTO) {
        Expenses savedExpenses = expenseRepository.findById(expenseDTO.getId()).
                orElseThrow(() -> new RuntimeException
                        (String.format("sorry, does not exist, try again", expenseDTO.getId())));
        savedExpenses.setItemCategory(expenseDTO.getItemCategory());
        savedExpenses.setAmount(expenseDTO.getAmount());
        expenseRepository.save(savedExpenses);
    }

    public void deleteExpense (Long id) {
        expenseRepository.deleteById(id);
    }
}
