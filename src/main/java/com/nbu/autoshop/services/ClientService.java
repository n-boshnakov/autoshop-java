package com.nbu.autoshop.services;

import com.nbu.autoshop.data.entity.Client;
import com.nbu.autoshop.data.entity.User;
import com.nbu.autoshop.data.entity.Client;
import com.nbu.autoshop.dto.ClientDTO;
import com.nbu.autoshop.dto.create.CreateClientDTO;
import com.nbu.autoshop.dto.update.UpdateClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClients();

    ClientDTO getClient(long id);

    Client create(CreateClientDTO createClientDTO);

    void deleteClient(long id);

    public Client updateClient(long id, UpdateClientDTO updateClientDTO);
}
