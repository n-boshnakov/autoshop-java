package com.nbu.autoshop.services.implementations;

import com.nbu.autoshop.data.entity.*;
import com.nbu.autoshop.data.entity.Client;
import com.nbu.autoshop.data.repository.ClientsRepository;
import com.nbu.autoshop.data.repository.RolesRepository;
import com.nbu.autoshop.dto.ClientDTO;
import com.nbu.autoshop.dto.WorkerDTO;
import com.nbu.autoshop.dto.create.CreateClientDTO;
import com.nbu.autoshop.dto.update.UpdateClientDTO;
import com.nbu.autoshop.exceptions.InvalidDataException;
import com.nbu.autoshop.exceptions.UserNotFoundException;
import com.nbu.autoshop.services.ClientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientsRepository clientsRepository;

    private RolesRepository rolesRepository;

    private PasswordEncoder encoder;

    private final ModelMapper modelMapper;

    @Override
    public List<ClientDTO> getClients() {
        return clientsRepository.findAll().stream()
                .map(this::convertToClientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClient(@Min(1) long id) {
        return modelMapper.map(clientsRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Invalid worker Id: " + id)), ClientDTO.class);
    }

    private ClientDTO convertToClientDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public Client create(@Valid CreateClientDTO createClientDTO) {
        Client clientT = new Client();
        clientT.setName(createClientDTO.getName());
        clientT.setPassword(encoder.encode(createClientDTO.getPassword()));
        clientT.setUsername(createClientDTO.getUsername());
        clientT.setAccountNonExpired(true);
        clientT.setAccountNonLocked(true);
        clientT.setCredentialsNonExpired(true);
        clientT.setEnabled(true);
        clientT.setAuthorities(rolesRepository.findAllByAuthority("CLIENT"));
        return clientsRepository.save(clientT);
    }

    @Override
    public Client updateClient(long id, UpdateClientDTO updateClientDTO) {
        Client client = modelMapper.map(updateClientDTO, Client.class);
        client.setId(id);
        return clientsRepository.save(client);
    }

    @Override
    public void deleteClient(long id) {
        Optional<Client> client = this.clientsRepository.findById(id);
        Iterator<Car> iterCa = client.get().getCars().iterator();
        while(iterCa.hasNext()){
            iterCa.next().setDeleted(true);
        }
        Iterator<Repair> iterRe = client.get().getRepairs().iterator();
        while(iterRe.hasNext()){
            iterRe.next().setDeleted(true);
        }
        client.get().setDeleted(true);
        this.clientsRepository.save(client.get());
    }
}
