package com.schurov.ssu.web.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("t_customers")
public class Customer {
    @Id
    @Column("customer_id")
    private Long customerId;

    private String name;
    private String phone;
}
