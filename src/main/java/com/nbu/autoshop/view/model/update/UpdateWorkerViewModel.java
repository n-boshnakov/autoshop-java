package com.nbu.autoshop.view.model.update;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.data.entity.Qualifications;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateWorkerViewModel extends UpdateUserViewModel{
    private Qualifications qualification;
    private AutoShop worksFor;
}
