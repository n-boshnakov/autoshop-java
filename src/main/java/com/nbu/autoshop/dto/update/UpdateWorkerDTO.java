package com.nbu.autoshop.dto.update;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.data.entity.Qualifications;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateWorkerDTO extends UpdateUserDTO{
    private Qualifications qualification;
    private AutoShop worksFor;
}
