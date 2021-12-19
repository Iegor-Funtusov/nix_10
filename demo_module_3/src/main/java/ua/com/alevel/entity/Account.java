package ua.com.alevel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {

    private String userId;
    private BigDecimal balance;
}
