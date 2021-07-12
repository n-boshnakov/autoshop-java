package com.nbu.autoshop.services;

import com.nbu.autoshop.data.entity.Worker;
import com.nbu.autoshop.dto.create.CreateWorkerDTO;
import com.nbu.autoshop.dto.WorkerDTO;
import com.nbu.autoshop.dto.update.UpdateWorkerDTO;

import java.util.List;

public interface WorkerService {

    List<WorkerDTO> getWorkers();

    WorkerDTO getWorker(long id);

    Worker create(CreateWorkerDTO createWorkerDTO);

    void deleteWorker(long id);

    public Worker updateWorker(long id, UpdateWorkerDTO updateWorkerDTO);
}
