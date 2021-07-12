package com.nbu.autoshop.dto.create;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.data.entity.Qualifications;
import com.nbu.autoshop.dto.update.UpdateUserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CreateWorkerDTO extends CreateUserDTO {
    private Qualifications qualification;
    private AutoShop worksFor;
}
