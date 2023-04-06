package com.schurov.ssu.web.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("t_settings")
public class Setting {
    @Id
    private String name;
    private String value;
}
