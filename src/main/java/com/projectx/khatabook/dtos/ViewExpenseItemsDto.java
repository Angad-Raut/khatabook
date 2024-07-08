package com.projectx.khatabook.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewExpenseItemsDto {
    private Integer srNo;
    private String itemName;
    private Double itemPrice;
    private String paymentWith;
}
