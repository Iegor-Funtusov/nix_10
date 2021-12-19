package ua.com.alevel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseEntity {

    private String accountId;
    private String categoryId;
    private BigDecimal amount;
    private Date created;

    public Transaction() {
        this.created = new Date();
    }
}
