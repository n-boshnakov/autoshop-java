package com.nbu.autoshop.view.controllers;

import com.nbu.autoshop.data.entity.Qualifications;
import com.nbu.autoshop.dto.create.CreateWorkerDTO;
import com.nbu.autoshop.dto.WorkerDTO;
import com.nbu.autoshop.dto.update.UpdateWorkerDTO;
import com.nbu.autoshop.services.AutoShopService;
import com.nbu.autoshop.services.WorkerService;
import com.nbu.autoshop.view.model.create.CreateWorkerViewModel;
import com.nbu.autoshop.view.model.update.UpdateWorkerViewModel;
import com.nbu.autoshop.view.model.WorkerViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/workers")
public class WorkersController {
    private final WorkerService workerService;
    private final AutoShopService autoShopService;


    private final ModelMapper modelMapper;

    @GetMapping
    public String getWorkers(Model model) {
        final List<WorkerViewModel> workers = workerService.getWorkers()
                .stream()
                .map(this::convertToWorkerViewModel)
                .collect(Collectors.toList());
        model.addAttribute("workers", workers);
        return "/workers/workers";
    }

    @GetMapping("/create-worker")
    public String showCreateWorkerForm(Model model) {
        model.addAttribute("qualifications", Qualifications.values());
        model.addAttribute("autoshops", autoShopService.getAutoShops());
        model.addAttribute("worker", new CreateWorkerViewModel());
        return "/workers/create-worker";
    }

    @PostMapping("/create")
    public String createWorker(@Valid @ModelAttribute("worker") CreateWorkerViewModel worker,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/worker/create-worker";
        }
        workerService.create(modelMapper.map(worker, CreateWorkerDTO.class));
        return "redirect:/";
    }

    @GetMapping("/edit-worker/{id}")
    public String showEditWorkerForm(Model model, @PathVariable Long id) {
        model.addAttribute("qualifications", Qualifications.values());
        model.addAttribute("autoshops", autoShopService.getAutoShops());
        model.addAttribute("worker", modelMapper.map(workerService.getWorker(id),
                UpdateWorkerViewModel.class));
        return "/workers/edit-worker";
    }

    @PostMapping("/update/{id}")
    public String updateWorker(@PathVariable long id, @Valid @ModelAttribute("worker") UpdateWorkerViewModel worker,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/workers/edit-worker";
        }
        workerService.updateWorker(id, modelMapper.map(worker, UpdateWorkerDTO.class));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        workerService.deleteWorker(id);
        return "redirect:/";
    }

    private WorkerViewModel convertToWorkerViewModel(WorkerDTO workerDTO) {
        return modelMapper.map(workerDTO, WorkerViewModel.class);
    }
}
