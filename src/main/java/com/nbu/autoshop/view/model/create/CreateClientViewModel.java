package com.nbu.autoshop.view.model.create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateClientViewModel {
    private long id;
    private String username;
    private String password;
    private String name;
}
