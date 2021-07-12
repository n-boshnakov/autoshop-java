package com.nbu.autoshop.dto.create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateClientDTO {
    private String username;
    private String password;
    private String name;
}
