package com.TheTrueHooha.ExpenseTracker.Controllers;

import com.TheTrueHooha.ExpenseTracker.DTO.ExpenseDTO;
import com.TheTrueHooha.ExpenseTracker.Model.Expenses;
import com.TheTrueHooha.ExpenseTracker.Services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("{/add}")
    public ResponseEntity<Void> addExpenses (@RequestBody ExpenseDTO expenseDTO) {
        Long expenseId = expenseService.addExpense(expenseDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(expenseId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("{/get_item}")
    public ExpenseDTO getExpenseByName(@PathVariable String name) {
        return expenseService.getExpense(name);
    }

    @GetMapping("{/get_all}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenseDTO> getAllItems() {
        return expenseService.getAllItems();
    }

    @PutMapping("{/update}")
    @ResponseStatus(HttpStatus.OK)
    public void updateExpenses(@RequestBody Expenses expense) {
        expenseService.updateItem(expense);
    }

    @DeleteMapping("{delete_item}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExpense (@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
}
