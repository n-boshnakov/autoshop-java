package com.nbu.autoshop.services.implementations;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.data.entity.Repair;
import com.nbu.autoshop.data.entity.Worker;
import com.nbu.autoshop.data.repository.AutoShopsRepository;
import com.nbu.autoshop.dto.*;
import com.nbu.autoshop.dto.create.CreateAutoShopDTO;
import com.nbu.autoshop.dto.update.UpdateAutoShopDTO;
import com.nbu.autoshop.exceptions.ObjectNotFoundException;
import com.nbu.autoshop.services.AutoShopService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AutoShopServiceImpl implements AutoShopService {

    private final AutoShopsRepository autoShopsRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<AutoShopDTO> getAutoShops() {
        return autoShopsRepository.findAll().stream()
                .map(this::convertToAutoShopDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AutoShopDTO getAutoShop(@Min(1) long id) {
        return modelMapper.map(autoShopsRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Invalid shop Id: " + id)), AutoShopDTO.class);
    }

    @Override
    public AutoShop create(@Valid CreateAutoShopDTO createAutoShopDTO) {
        return autoShopsRepository.save(modelMapper.map(createAutoShopDTO, AutoShop.class));
    }

    @Override
    public AutoShop updateAutoShop(long id, UpdateAutoShopDTO updateAutoShopDTO) {
        AutoShop autoShop = modelMapper.map(updateAutoShopDTO, AutoShop.class);
        autoShop.setId(id);
        return autoShopsRepository.save(autoShop);
    }

    @Override
    public void deleteAutoShop(long id) {
        Optional<AutoShop> autoShop = this.autoShopsRepository.findById(id);
        Iterator<Worker> iterWo = autoShop.get().getWorkerList().iterator();
        while(iterWo.hasNext()){
            iterWo.next().setDeleted(true);
        }
        Iterator<Repair> iterRe = autoShop.get().getRepairsList().iterator();
        while(iterRe.hasNext()){
            iterRe.next().setDeleted(true);
        }
        autoShop.get().setDeleted(true);
        this.autoShopsRepository.save(autoShop.get());
    }

    private AutoShopDTO convertToAutoShopDTO(AutoShop autoShop) {
        return modelMapper.map(autoShop, AutoShopDTO.class);
    }
}

