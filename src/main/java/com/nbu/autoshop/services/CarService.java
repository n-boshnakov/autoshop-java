package com.nbu.autoshop.services;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.data.entity.Brand;
import com.nbu.autoshop.data.entity.Car;
import com.nbu.autoshop.data.entity.User;
import com.nbu.autoshop.dto.AutoShopDTO;
import com.nbu.autoshop.dto.CarDTO;
import com.nbu.autoshop.dto.create.CreateAutoShopDTO;
import com.nbu.autoshop.dto.create.CreateCarDTO;
import com.nbu.autoshop.dto.update.UpdateAutoShopDTO;
import com.nbu.autoshop.dto.update.UpdateCarDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CarService {
     List<CarDTO> getCars();

     List<CarDTO> getCarsByOwner(Authentication authentication);

     CarDTO getCar(long id);

     Car create(CreateCarDTO createCarDTO, Authentication authentication);

     Car updateCar(long id, UpdateCarDTO updateCarDTO);

     void deleteCar(long id);

     List<CarDTO> getCarsByOwner(User user);

     List<CarDTO> getCarsByAutoShop(AutoShop autoShop);

     public List<CarDTO> getCarsByAutoShopAndBrand(AutoShop autoShop, Brand brand);
}
