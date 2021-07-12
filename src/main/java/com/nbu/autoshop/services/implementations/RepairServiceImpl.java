package com.nbu.autoshop.services.implementations;

import com.nbu.autoshop.data.entity.*;
import com.nbu.autoshop.data.repository.AutoShopsRepository;
import com.nbu.autoshop.data.repository.CarsRepository;
import com.nbu.autoshop.data.repository.ClientsRepository;
import com.nbu.autoshop.data.repository.RepairsRepository;
import com.nbu.autoshop.dto.CarDTO;
import com.nbu.autoshop.dto.RepairDTO;
import com.nbu.autoshop.dto.create.CreatRepairDTO;
import com.nbu.autoshop.dto.update.UpdateRepairDTO;
import com.nbu.autoshop.exceptions.InvalidDataException;
import com.nbu.autoshop.exceptions.ObjectNotFoundException;
import com.nbu.autoshop.services.BrandService;
import com.nbu.autoshop.services.RepairService;
import org.springframework.security.core.Authentication;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepairServiceImpl implements RepairService {
    private RepairsRepository repairsRepository;

    private ClientsRepository clientsRepository;

    private AutoShopsRepository autoShopsRepository;

    private CarsRepository carsRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<RepairDTO> getRepairs() {
        return repairsRepository.findAll().stream()
                .map(this::convertToRepairDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairDTO> getRepairsByClient(Authentication authentication) {
        Client client = (Client) authentication.getPrincipal();
        return repairsRepository.findAllByClient(client).stream()
                .map(this::convertToRepairDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairDTO> getRepairsByCarAndAutoShop(long id, Authentication authentication) {
        Worker client = (Worker) authentication.getPrincipal();
        return repairsRepository.findAllByCarAndAndAutoshop(carsRepository.findCarById(id), client.getWorksFor()).stream()
                .map(this::convertToRepairDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RepairDTO getRepair(@Min(1) long id) {
        return modelMapper.map(repairsRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Invalid repair Id: " + id)), RepairDTO.class);
    }

    @Override
    public Repair create(@Valid CreatRepairDTO creatRepairDTO, Authentication authentication) {
        Repair repair = modelMapper.map(creatRepairDTO, Repair.class);
        Client user = (Client) authentication.getPrincipal();
        repair.setClient(user);
        repair.setPrice(repair.getService().price);
        clientsRepository.findById(user.getId()).get().getRepairs().add(repair);
        autoShopsRepository.findById(repair.getAutoshop().getId()).get().getRepairsList().add(repair);
        return repairsRepository.save(repair);
    }

    @Override
    public Repair updateRepair(long id, UpdateRepairDTO updateRepairDTO) {
        Repair repair = modelMapper.map(updateRepairDTO, Repair.class);
        repair.setId(id);
        repair.setPrice(repair.getService().price);
        return repairsRepository.save(repair);
    }

    @Override
    public void deleteRepair(long id) {
        Optional<Repair> repair = this.repairsRepository.findById(id);

        repair.get().setDeleted(true);
        this.repairsRepository.save(repair.get());
    }

    private RepairDTO convertToRepairDTO(Repair repair) {
        return modelMapper.map(repair, RepairDTO.class);
    }

    @Override
    public List<RepairDTO> getRepairsByYear(AutoShop autoShop, int year) {
        List<Repair> repairsList = new ArrayList<>();
        List<Repair> repairsAutoshop = repairsRepository.findAllByAutoshop(autoShop);
        for (int i = 0; i < repairsAutoshop.size(); i++) {
            if(repairsAutoshop.get(i).getCar().getYear() == year){
                repairsList.add(repairsAutoshop.get(i));
                //repairsAutoshop.remove(repairsAutoshop.get(i));
            }
        }
        return repairsList.stream()
                .map(this::convertToRepairDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairDTO> getRepairsByBrand(AutoShop autoShop, Brand brand) {
        List<Repair> repairsList = new ArrayList<>();
        List<Repair> repairsAutoshop = repairsRepository.findAllByAutoshop(autoShop);
        for (int i = 0; i < repairsAutoshop.size(); i++) {
            if(repairsAutoshop.get(i).getCar().getBrand().equals(brand)){
                repairsList.add(repairsAutoshop.get(i));
                //repairsAutoshop.remove(repairsAutoshop.get(i));
            }
        }
        return repairsList.stream()
                .map(this::convertToRepairDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairDTO> getRepairsByService(AutoShop autoShop, Qualifications qualification) {
        List<Repair> repairsList = new ArrayList<>();
        List<Repair> repairsAutoshop = repairsRepository.findAllByAutoshop(autoShop);
        for (int i = 0; i < repairsAutoshop.size(); i++) {
            if(repairsAutoshop.get(i).getService().equals(qualification)){
                repairsList.add(repairsAutoshop.get(i));
                //repairsAutoshop.remove(repairsAutoshop.get(i));
            }
        }
        return repairsList.stream()
                .map(this::convertToRepairDTO)
                .collect(Collectors.toList());
    }

}
