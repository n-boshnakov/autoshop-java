package com.nbu.autoshop.view.controllers;

import com.nbu.autoshop.data.entity.*;
import com.nbu.autoshop.data.repository.UsersRepository;
import com.nbu.autoshop.dto.RepairDTO;
import com.nbu.autoshop.dto.create.CreatRepairDTO;
import com.nbu.autoshop.dto.update.UpdateRepairDTO;
import com.nbu.autoshop.services.AutoShopService;
import com.nbu.autoshop.services.BrandService;
import com.nbu.autoshop.services.CarService;
import com.nbu.autoshop.services.RepairService;
import com.nbu.autoshop.view.model.*;
import com.nbu.autoshop.view.model.create.CreateCarViewModel;
import com.nbu.autoshop.view.model.create.CreateRepairViewModel;
import com.nbu.autoshop.view.model.update.UpdateRepairViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/repairs")
public class RepairsController {
    private final RepairService repairService;

    private final CarService carService;

    private final BrandService brandService;

    private final AutoShopService autoShopService;

    private final UsersRepository usersRepository;

    private final ModelMapper modelMapper;

    @GetMapping
    public String getRepairs(Model model) {
        final List<RepairViewModel> repairs = repairService.getRepairs()
                .stream()
                .map(this::convertToRepairViewModel)
                .collect(Collectors.toList());
        model.addAttribute("repairs", repairs);
        return "/repairs/repairs";
    }

    @GetMapping("/repairs-client")
    public String getRepairsByClient(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
            final List<RepairViewModel> repairs = repairService.getRepairsByClient(authentication)
                    .stream()
                    .map(this::convertToRepairViewModel)
                    .collect(Collectors.toList());
            model.addAttribute("repairs", repairs);
            return "/repairs/repairs";
    }

    @GetMapping("/create-repair")
    public String showCreateRepairsForm(Model model, Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        model.addAttribute("cars", carService.getCarsByOwner(usersRepository.findById(principal.getId())));
        model.addAttribute("qualifications", Qualifications.values());
        model.addAttribute("autoshops", autoShopService.getAutoShops());
        model.addAttribute("repair", new CreateRepairViewModel());
        return "/repairs/create-repair";
    }

    @PostMapping("/create")
    public String createRepair(@Valid @ModelAttribute("repair") CreateRepairViewModel repair,
                                 BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "/repairs/create-repair";
        }
        repairService.create(modelMapper.map(repair, CreatRepairDTO.class), authentication);
        return "redirect:/";
    }

    @GetMapping("/edit-repair/{id}")
    public String showEditRepairForm(Model model, @PathVariable Long id, Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        model.addAttribute("cars", carService.getCarsByOwner(usersRepository.findById(principal.getId())));
        model.addAttribute("qualifications", Qualifications.values());
        model.addAttribute("autoshops", autoShopService.getAutoShops());
        model.addAttribute("repair", modelMapper.map(repairService.getRepair(id),
                UpdateRepairViewModel.class));
        return "/repairs/edit-repair";
    }

    @PostMapping("/update/{id}")
    public String updateRepair(@PathVariable long id, @Valid @ModelAttribute("repair") UpdateRepairViewModel repair,
                                 BindingResult bindingResult,Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "/repairs/edit-repair";
        }
        User principal = (User) authentication.getPrincipal();
        repair.setClient(principal);
        repairService.updateRepair(id, modelMapper.map(repair, UpdateRepairDTO.class));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        repairService.deleteRepair(id);
        return "redirect:/";
    }

    private RepairViewModel convertToRepairViewModel(RepairDTO repairDTO) {
        return modelMapper.map(repairDTO, RepairViewModel.class);
    }

    // Search car
    @GetMapping("/search-repairs")
    public String processSearchRepairForm(Model model) {
        model.addAttribute("brands", brandService.getBrands());
        model.addAttribute("car", new CreateCarViewModel());
        model.addAttribute("qualifications", Qualifications.values());
        return "/repairs/search-repairs";
    }

    @GetMapping("/repairs-search-brand")
    public String getRepairsByAutoShopAndBrand(Model model, Authentication authentication, @RequestParam Brand brand) {
        Worker worker = (Worker) authentication.getPrincipal();
        AutoShop autoShop = worker.getWorksFor();
        List<RepairViewModel> repairs = repairService
                .getRepairsByBrand(autoShop, brand)
                .stream()
                .map(this::convertToRepairViewModel)
                .collect(Collectors.toList());

        model.addAttribute("repairs", repairs);
        return "/repairs/repairs-show-details";
    }

    @GetMapping("/repairs-search-year")
    public String getRepairsByAutoShopAndYear(Model model, Authentication authentication, @RequestParam int year) {
        Worker worker = (Worker) authentication.getPrincipal();
        AutoShop autoShop = worker.getWorksFor();
        List<RepairViewModel> repairs = repairService
                .getRepairsByYear(autoShop, year)
                .stream()
                .map(this::convertToRepairViewModel)
                .collect(Collectors.toList());

        model.addAttribute("repairs", repairs);
        return "/repairs/repairs-show-details";
    }

    @GetMapping("/repairs-search-service")
    public String getRepairsByAutoShopAndYear(Model model, Authentication authentication, @RequestParam Qualifications service) {
        Worker worker = (Worker) authentication.getPrincipal();
        AutoShop autoShop = worker.getWorksFor();
        List<RepairViewModel> repairs = repairService
                .getRepairsByService(autoShop, service)
                .stream()
                .map(this::convertToRepairViewModel)
                .collect(Collectors.toList());

        model.addAttribute("repairs", repairs);
        return "/repairs/repairs-show-details";
    }
}
