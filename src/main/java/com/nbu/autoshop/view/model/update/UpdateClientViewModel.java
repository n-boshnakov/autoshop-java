package com.nbu.autoshop.view.model.update;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateClientViewModel {
    private long id;
    private String username;
    private String password;
    private String name;
}
