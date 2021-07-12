package com.nbu.autoshop.view.controllers;

import com.nbu.autoshop.dto.AutoShopDTO;
import com.nbu.autoshop.dto.create.CreateAutoShopDTO;
import com.nbu.autoshop.dto.update.UpdateAutoShopDTO;
import com.nbu.autoshop.services.AutoShopService;
import com.nbu.autoshop.services.BrandService;
import com.nbu.autoshop.view.model.AutoShopViewModel;
import com.nbu.autoshop.view.model.create.CreateAutoShopViewModel;
import com.nbu.autoshop.view.model.update.UpdateAutoShopViewModel;
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
@RequestMapping("/autoshops")
public class AutoShopsController {

    private final AutoShopService autoShopService;

    private final BrandService brandService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String getAutoShops(Model model) {
        final List<AutoShopViewModel> autoShops = autoShopService.getAutoShops()
                .stream()
                .map(this::convertToAutoShopViewModel)
                .collect(Collectors.toList());
        model.addAttribute("autoshops", autoShops);
        return "/autoshops/autoshops";
    }

    @GetMapping("/create-autoshop")
    public String showCreateAutoShopForm(Model model) {
        model.addAttribute("brands", brandService.getBrands());
        model.addAttribute("autoShop", new CreateAutoShopViewModel());
        return "/autoshops/create-autoshop";
    }

    @PostMapping("/create")
    public String createAutoShop(@Valid @ModelAttribute("autoshop") CreateAutoShopViewModel autoshop,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/autoshops/create-autoshop";
        }
        autoShopService.create(modelMapper.map(autoshop, CreateAutoShopDTO.class));
        return "redirect:/";
    }

    @GetMapping("/edit-autoshop/{id}")
    public String showEditAutoShopForm(Model model, @PathVariable Long id) {
        model.addAttribute("brands", brandService.getBrands());
        model.addAttribute("autoShop", modelMapper.map(autoShopService.getAutoShop(id),
                UpdateAutoShopViewModel.class));
        return "/autoshops/edit-autoshop";
    }

    @PostMapping("/update/{id}")
    public String updateAutoShop(@PathVariable long id, @Valid @ModelAttribute("autoShop") UpdateAutoShopViewModel autoshop,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/autoshops/edit-autoshop";
        }
        autoShopService.updateAutoShop(id, modelMapper.map(autoshop, UpdateAutoShopDTO.class));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        autoShopService.deleteAutoShop(id);
        return "redirect:/";
    }

    private AutoShopViewModel convertToAutoShopViewModel(AutoShopDTO autoShopDTO) {
        return modelMapper.map(autoShopDTO, AutoShopViewModel.class);
    }
}