package com.projectx.khatabook.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewExpensesDto {
    private Integer srNo;
    private Long expenseId;
    private Double totalAmount;
    private String expenseDate;
}
