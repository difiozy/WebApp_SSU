package com.schurov.ssu.web.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("t_companies")
public class Companies {
    @Id
    @Column("company_id")
    private Long companyId;
    @Column("company_short_name")
    private String companyShortName;
    @Column("company_full_name")
    private String companyFullName;
    private String inn;
}
