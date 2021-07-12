package com.nbu.autoshop.services.implementations;

import com.nbu.autoshop.data.entity.*;
import com.nbu.autoshop.data.repository.*;
import com.nbu.autoshop.dto.AutoShopDTO;
import com.nbu.autoshop.dto.CarDTO;
import com.nbu.autoshop.dto.create.CreateAutoShopDTO;
import com.nbu.autoshop.dto.create.CreateCarDTO;
import com.nbu.autoshop.dto.update.UpdateAutoShopDTO;
import com.nbu.autoshop.dto.update.UpdateCarDTO;
import com.nbu.autoshop.exceptions.ObjectNotFoundException;
import com.nbu.autoshop.services.CarService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarsRepository carsRepository;

    private ClientsRepository clientsRepository;

    private AutoShopsRepository autoShopsRepository;

    private RepairsRepository repairsRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<CarDTO> getCars() {
        return carsRepository.findAll().stream()
                .map(this::convertToCarDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getCarsByOwner(Authentication authentication){
        Client user = (Client) authentication.getPrincipal();
        return carsRepository.findAllByOwner(user).stream()
                .map(this::convertToCarDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO getCar(@Min(1) long id) {
        return modelMapper.map(carsRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Invalid car Id: " + id)), CarDTO.class);
    }

    @Override
    public Car create(@Valid CreateCarDTO createCarDTO, Authentication authentication) {
        Car car = modelMapper.map(createCarDTO, Car.class);
        Client user = (Client) authentication.getPrincipal();
        car.setOwner(user);
        clientsRepository.findById(user.getId()).get().getCars().add(car);
        return carsRepository.save(car);
    }

    @Override
    public Car updateCar(long id, UpdateCarDTO updateCarDTO) {
        Car car = modelMapper.map(updateCarDTO, Car.class);
        car.setId(id);
        return carsRepository.save(car);
    }

    @Override
    public void deleteCar(long id) {
        Optional<Car> car = this.carsRepository.findById(id);

        car.get().setDeleted(true);
        this.carsRepository.save(car.get());
    }

    private CarDTO convertToCarDTO(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }

    @Override
    public List<CarDTO> getCarsByOwner(User user) {
        return carsRepository.findAllByOwner(user).stream()
                .map(this::convertToCarDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getCarsByAutoShop(AutoShop autoShop) {
        List<Car> carsList = new ArrayList<>();
        List<Repair> repairsAutoshop = repairsRepository.findAllByAutoshop(autoShop);
        for (int i = 0; i < repairsAutoshop.size(); i++) {
            carsList.add(repairsAutoshop.get(i).getCar());
        }
        return carsList.stream()
                .map(this::convertToCarDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getCarsByAutoShopAndBrand(AutoShop autoShop, Brand brand) {
        List<Car> carsList = new ArrayList<>();
        List<Repair> repairsAutoshop = repairsRepository.findAllByAutoshop(autoShop);
        for (int i = 0; i < repairsAutoshop.size(); i++) {
            if(repairsAutoshop.get(i).getCar().getBrand().equals(brand)){
                carsList.add(repairsAutoshop.get(i).getCar());
            }
        }
        return carsList.stream()
                .map(this::convertToCarDTO)
                .collect(Collectors.toList());
    }
}
