package com.schurov.ssu.web.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("t_user")
@Getter
@Setter
public class User {
    @Id
    @Column("user_id")
    private Long id;

    @Column("sso")
    private String sso;
    @Column("name")
    private String name;
}
