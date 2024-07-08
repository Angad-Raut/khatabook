package com.projectx.khatabook.servicestests;

import com.projectx.khatabook.commonUtils.ExpenseUtils;
import com.projectx.khatabook.commons.Constants;
import com.projectx.khatabook.commons.EntityIdDto;
import com.projectx.khatabook.dtos.ExpenseDto;
import com.projectx.khatabook.entities.ExpensesDetails;
import com.projectx.khatabook.repositories.ExpensesRepository;
import com.projectx.khatabook.services.ExpenseServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ExpsenseServiceTest {

    @Mock
    private ExpensesRepository expensesRepository;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createExpenseTest() {
        when(expensesRepository.expenseExists(Constants.atStartOfDay(),Constants.atEndOfDay())).thenReturn(0);
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        ExpenseDto expenseDto = ExpenseUtils.toExpenseDto();
        expenseDto.setId(null);
        Boolean message = expenseService.createExpense(expenseDto);
        assertFalse(message);

    }

    @Test
    public void updateExpenseTest() {
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.getExpensesById(1L)).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.existsByExpenseName(1L,"Milk")).thenReturn(0);
        Boolean message = expenseService.updateExpense(ExpenseUtils.toExpenseDto());
        assertFalse(message);
    }

    @Test
    public  void getByIdTest() {
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.getExpensesById(1L)).thenReturn(ExpenseUtils.toCreateExpenses());
        ExpensesDetails details = expenseService.getById(EntityIdDto.builder().entityId(1L).build());
        assertEquals(ExpenseUtils.toCreateExpenses().getTotalAmount(),details.getTotalAmount());
    }

    @Test
    public void getAllExpensesOfMonthsTest() {
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.save(ExpenseUtils.toCreateExpensesTwo())).thenReturn(ExpenseUtils.toCreateExpensesTwo());
        when(expensesRepository.getMonthAllExpenses(Constants.firstDayOfMonth(),Constants.lastDayOfMonth())).thenReturn(new ArrayList<Object[]>());
        assertNotNull(expenseService.getAllExpensesOfMonths());
    }

    @Test
    public void getAllExpensesTest() {
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.save(ExpenseUtils.toCreateExpensesTwo())).thenReturn(ExpenseUtils.toCreateExpensesTwo());
        when(expensesRepository.getAllExpenses()).thenReturn(new ArrayList<Object[]>());
        assertNotNull(expenseService.getAllExpenses());
    }

    public void getExpensesItemsByExpenseIdTest() {
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.getExpensesById(1L)).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.getExpenseItemsByExpenseId(1L)).thenReturn(new ArrayList<Object[]>());
        assertNotNull(expenseService.getExpensesItemsByExpenseId(new EntityIdDto(1L)));
    }

    @Test
    public void updateStatusTest() {
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.getExpensesById(1L)).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.updateStatus(1L,false)).thenReturn(1);
        assertNotNull(expenseService.updateStatus(new EntityIdDto(1L)));
    }
}
