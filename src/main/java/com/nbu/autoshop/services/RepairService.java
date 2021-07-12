package com.nbu.autoshop.services;

import com.nbu.autoshop.data.entity.*;
import com.nbu.autoshop.dto.RepairDTO;
import com.nbu.autoshop.dto.create.CreatRepairDTO;
import com.nbu.autoshop.dto.update.UpdateRepairDTO;
import com.nbu.autoshop.exceptions.ObjectNotFoundException;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

public interface RepairService {
    List<RepairDTO> getRepairs();

    RepairDTO getRepair(long id);

    List<RepairDTO> getRepairsByCarAndAutoShop(long id, Authentication authentication);

    Repair create(CreatRepairDTO creatRepairDTO, Authentication authentication);

    Repair updateRepair(long id, UpdateRepairDTO updateRepairDTO);

    List<RepairDTO> getRepairsByClient(Authentication authentication);

    void deleteRepair(long id);

    List<RepairDTO> getRepairsByYear(AutoShop autoShop, int year);

    List<RepairDTO> getRepairsByBrand(AutoShop autoShop, Brand brand);

    List<RepairDTO> getRepairsByService(AutoShop autoShop, Qualifications qualification);
}
