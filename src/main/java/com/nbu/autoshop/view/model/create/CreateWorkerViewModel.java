package com.nbu.autoshop.view.model.create;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.data.entity.Qualifications;
import com.nbu.autoshop.view.model.UserViewModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CreateWorkerViewModel extends CreateUserViewModel {
    private Qualifications qualification;
    private AutoShop worksFor;
}
