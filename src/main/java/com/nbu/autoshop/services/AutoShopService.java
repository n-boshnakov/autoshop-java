package com.nbu.autoshop.services;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.dto.AutoShopDTO;
import com.nbu.autoshop.dto.create.CreateAutoShopDTO;
import com.nbu.autoshop.dto.update.UpdateAutoShopDTO;

import java.util.List;

public interface AutoShopService {
     List<AutoShopDTO> getAutoShops();

     AutoShopDTO getAutoShop(long id);

     AutoShop create(CreateAutoShopDTO createAutoShopDTO);

     AutoShop updateAutoShop(long id, UpdateAutoShopDTO updateAutoShopDTO);

     void deleteAutoShop(long id);
}