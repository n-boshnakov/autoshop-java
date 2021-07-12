package com.nbu.autoshop.dto;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.data.entity.Qualifications;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class WorkerDTO extends UserDTO{
    private Qualifications qualification;
    private AutoShop worksFor;
}
