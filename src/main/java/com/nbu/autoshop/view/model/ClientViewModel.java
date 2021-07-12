package com.nbu.autoshop.view.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientViewModel {
    private long id;
    private String username;
    private String password;
    private String name;
}
