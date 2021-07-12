package com.nbu.autoshop.view.controllers;

import com.nbu.autoshop.dto.ClientDTO;
import com.nbu.autoshop.dto.create.CreateClientDTO;
import com.nbu.autoshop.dto.update.UpdateClientDTO;
import com.nbu.autoshop.services.ClientService;
import com.nbu.autoshop.view.model.ClientViewModel;
import com.nbu.autoshop.view.model.create.CreateClientViewModel;
import com.nbu.autoshop.view.model.update.UpdateClientViewModel;
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
@RequestMapping("/clients")
public class ClientsController {
    private final ClientService clientService;

    private final ModelMapper modelMapper;
    
    @GetMapping
    public String getClients(Model model) {
        final List<ClientViewModel> clients = clientService.getClients()
                .stream()
                .map(this::convertToClientViewModel)
                .collect(Collectors.toList());
        model.addAttribute("clients", clients);
        return "/clients/clients";
    }

    @GetMapping("/create-client")
    public String showCreateClientForm(Model model) {
        model.addAttribute("client", new CreateClientViewModel());
        return "/clients/create-client";
    }

    @PostMapping("/create")
    public String createClient(@Valid @ModelAttribute("client") CreateClientViewModel client,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/clients/create-client";
        }
        clientService.create(modelMapper.map(client, CreateClientDTO.class));
        return "redirect:/";
    }

    @GetMapping("/edit-client/{id}")
    public String showEditClientForm(Model model, @PathVariable Long id) {
        model.addAttribute("client", modelMapper.map(clientService.getClient(id),
                UpdateClientViewModel.class));
        return "/clients/edit-client";
    }

    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable long id, @Valid @ModelAttribute("client") UpdateClientViewModel client,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/clients/edit-client";
        }
        clientService.updateClient(id, modelMapper.map(client, UpdateClientDTO.class));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        clientService.deleteClient(id);
        return "redirect:/";
    }

    private ClientViewModel convertToClientViewModel(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, ClientViewModel.class);
    }
}
