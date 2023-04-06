package com.schurov.ssu.web.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("t_user")
@Getter
@Setter
@Accessors(chain = true)
public class User implements Persistable<String> {
    @Id
    @Column("sso")
    private String sso;
    @Column("name")
    private String name;
    @Column("token")
    private String token;

    @Transient
    private Boolean nev;
    @Override
    public String getId() {
        return sso;
    }

    @Override
    public boolean isNew() {
        return nev;
    }
}
