package com.nbu.autoshop.view.controllers;

import com.nbu.autoshop.data.repository.ClientsRepository;
import com.nbu.autoshop.data.repository.RolesRepository;
import com.nbu.autoshop.dto.create.CreateClientDTO;
import com.nbu.autoshop.services.ClientService;
import com.nbu.autoshop.view.model.create.CreateClientViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class RegisterController {
    private final ClientService clientService;
    private final ClientsRepository clientsRepository;
    private final RolesRepository rolesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encoder;

    public RegisterController(ClientService clientService, ClientsRepository clientsRepository, RolesRepository rolesRepository, ModelMapper modelMapper, ModelMapper modelMapper1) {
        this.clientService = clientService;
        this.clientsRepository = clientsRepository;
        this.rolesRepository = rolesRepository;
        this.modelMapper = modelMapper1;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("client", new CreateClientViewModel());
        return "/register";
    }

    @PostMapping("/create-client")
    public String registerClient(@Valid @ModelAttribute("client") CreateClientViewModel client,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/register";
        }
        clientService.create(modelMapper.map(client, CreateClientDTO.class));
        return "redirect:/login";
    }
}
