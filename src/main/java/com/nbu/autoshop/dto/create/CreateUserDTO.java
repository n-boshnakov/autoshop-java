package com.nbu.autoshop.dto.create;

import com.nbu.autoshop.data.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDTO {
    @NotBlank
    @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String username;
    @NotBlank
    @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String password;
    private String name;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Set<UserRole> authorities;
}
