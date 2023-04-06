package com.schurov.ssu.web.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("t_user")
@Getter
@Setter
@Accessors(chain = true)
public class User {
    @Id
    @Column("sso")
    private String sso;
    @Column("name")
    private String name;
    @Column("token")
    private String token;
}
