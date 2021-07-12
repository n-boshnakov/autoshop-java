package com.nbu.autoshop.view.controllers;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.data.entity.Brand;
import com.nbu.autoshop.data.entity.Worker;
import com.nbu.autoshop.dto.CarDTO;
import com.nbu.autoshop.dto.create.CreateCarDTO;
import com.nbu.autoshop.dto.update.UpdateCarDTO;
import com.nbu.autoshop.services.BrandService;
import com.nbu.autoshop.services.CarService;
import com.nbu.autoshop.view.model.*;
import com.nbu.autoshop.view.model.create.CreateCarViewModel;
import com.nbu.autoshop.view.model.update.UpdateCarViewModel;
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
@RequestMapping("/cars")
public class CarsController {
    private final CarService carService;

    private final BrandService brandService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String getCars(Model model, Authentication authentication) {
        final List<CarViewModel> cars = carService.getCarsByOwner(authentication)
                .stream()
                .map(this::convertToCarViewModel)
                .collect(Collectors.toList());
        model.addAttribute("cars", cars);
        return "/cars/cars";
    }

    @GetMapping("/cars-autoshop")
    public String getCarsAutoshop(Model model, Authentication authentication) {
        Worker worker = (Worker) authentication.getPrincipal();
        final List<CarViewModel> cars = carService.getCarsByAutoShop(worker.getWorksFor())
                .stream()
                .map(this::convertToCarViewModel)
                .collect(Collectors.toList());
        model.addAttribute("cars", cars);
        return "/cars/cars";
    }

    // Search car
    @GetMapping("/search-cars")
    public String processSearchCarForm(Model model) {
        model.addAttribute("brands", brandService.getBrands());
        model.addAttribute("car", new CreateCarViewModel());
        return "/cars/search-cars";
    }

    @GetMapping("/cars-search-brand")
    public String geCarsByAutoShopAndBrand(Model model, Authentication authentication, @RequestParam Brand brand) {
        Worker worker = (Worker) authentication.getPrincipal();
        AutoShop autoShop = worker.getWorksFor();
        List<CarViewModel> cars = carService
                .getCarsByAutoShopAndBrand(autoShop, brand)
                .stream()
                .map(this::convertToCarViewModel)
                .collect(Collectors.toList());

        model.addAttribute("cars", cars);
        return "/cars/cars-show-details";
    }

    @GetMapping("/create-car")
    public String showCreateCarForm(Model model) {
        model.addAttribute("brands", brandService.getBrands());
        model.addAttribute("car", new CreateCarViewModel());
        return "/cars/create-car";
    }

    @PostMapping("/create")
    public String createCar(@Valid @ModelAttribute("car") CreateCarDTO car,
                            BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "/cars/create-car";
        }
        carService.create(modelMapper.map(car, CreateCarDTO.class), authentication);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateCar(@PathVariable long id, @Valid @ModelAttribute("car") UpdateCarViewModel car,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/cars/edit-car";
        }
        carService.updateCar(id, modelMapper.map(car, UpdateCarDTO.class));
        return "redirect:/";
    }

    @GetMapping("/edit-car/{id}")
    public String showEditCarForm(Model model, @PathVariable Long id) {
        model.addAttribute("brands", brandService.getBrands());
        model.addAttribute("car", modelMapper.map(carService.getCar(id),
                UpdateCarViewModel.class));
        return "/cars/edit-car";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        carService.deleteCar(id);
        return "redirect:/";
    }
    
    private CarViewModel convertToCarViewModel(CarDTO carDTO) {
        return modelMapper.map(carDTO, CarViewModel.class);
    }
}
