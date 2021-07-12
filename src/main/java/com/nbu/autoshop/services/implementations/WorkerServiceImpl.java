package com.nbu.autoshop.services.implementations;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.data.entity.Repair;
import com.nbu.autoshop.data.entity.Worker;
import com.nbu.autoshop.data.repository.AutoShopsRepository;
import com.nbu.autoshop.data.repository.RolesRepository;
import com.nbu.autoshop.data.repository.WorkersRepository;
import com.nbu.autoshop.dto.create.CreateWorkerDTO;
import com.nbu.autoshop.dto.WorkerDTO;
import com.nbu.autoshop.dto.update.UpdateAutoShopDTO;
import com.nbu.autoshop.dto.update.UpdateWorkerDTO;
import com.nbu.autoshop.exceptions.UserNotFoundException;
import com.nbu.autoshop.services.WorkerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private WorkersRepository workersRepository;

    private RolesRepository rolesRepository;

    private PasswordEncoder encoder;

    private AutoShopsRepository autoShopsRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<WorkerDTO> getWorkers() {
        return workersRepository.findAll().stream()
                .map(this::convertToWorkerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WorkerDTO getWorker(@Min(1) long id) {
        return modelMapper.map(workersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Invalid worker Id: " + id)), WorkerDTO.class);
    }

    private WorkerDTO convertToWorkerDTO(Worker worker) {
        return modelMapper.map(worker, WorkerDTO.class);
    }

    @Override
    public Worker create(@Valid CreateWorkerDTO createWorkerDTO) {
        Worker workerT = new Worker();
        workerT.setName(createWorkerDTO.getName());
        workerT.setPassword(encoder.encode(createWorkerDTO.getPassword()));
        workerT.setUsername(createWorkerDTO.getUsername());
        workerT.setQualification(createWorkerDTO.getQualification());
        workerT.setWorksFor(createWorkerDTO.getWorksFor());
        workerT.setAccountNonExpired(true);
        workerT.setAccountNonLocked(true);
        workerT.setCredentialsNonExpired(true);
        workerT.setEnabled(true);
        workerT.setAuthorities(rolesRepository.findAllByAuthority("WORKER"));
        autoShopsRepository.findById(createWorkerDTO.getWorksFor().getId()).get().getWorkerList().add(workerT);
        return workersRepository.save(workerT);
    }

    @Override
    public Worker updateWorker(long id, UpdateWorkerDTO updateWorkerDTO) {
        Worker worker = modelMapper.map(updateWorkerDTO, Worker.class);
        worker.setId(id);
        return workersRepository.save(worker);
    }

    @Override
    public void deleteWorker(long id) {
        Optional<Worker> worker = this.workersRepository.findById(id);

        worker.get().setDeleted(true);
        this.workersRepository.save(worker.get());
    }

}
