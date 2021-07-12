package com.nbu.autoshop.dto.update;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateClientDTO {
    private String username;
    private String password;
    private String name;
}
