package com.TheTrueHooha.ExpenseTracker.Controllers;

import com.TheTrueHooha.ExpenseTracker.DTO.ExpenseDTO;
import com.TheTrueHooha.ExpenseTracker.Model.ItemCategory;
import com.TheTrueHooha.ExpenseTracker.Services.ExpenseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.google.common.net.HttpHeaders;

import javax.xml.stream.Location;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ExpenseController.class)
class ExpenseControllerTest {

    @MockBean
    private ExpenseService expenseService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should create an expense item")
    void shouldCreateExpense() throws Exception {
        ExpenseDTO expenseDTO = ExpenseDTO.builder()
                .itemCategory(ItemCategory.BOOKS)
                .expenseName("Purple Hibiscus")
                .expenseAmount(BigDecimal.TEN)
                .build();

        Mockito.when(expenseService.addExpense(expenseDTO)).thenReturn(Long.valueOf("222"));
        MvcResult mvcResult = mockMvc.perform(post("/add"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists(HttpHeaders.LOCATION)).andReturn();

        assertTrue(mvcResult.getResponse().getHeaderValue(HttpHeaders.LOCATION).toString().contains("222"));
    }

}