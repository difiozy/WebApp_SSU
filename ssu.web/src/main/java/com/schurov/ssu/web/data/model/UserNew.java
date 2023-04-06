package com.schurov.ssu.web.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserNew {
    private String sso;
    private String token;
}