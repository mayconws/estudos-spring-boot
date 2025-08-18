package com.desafio.crud.controller;

import com.desafio.crud.dto.ClientDto;
import com.desafio.crud.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(Pageable pageable) {
        Page<ClientDto> clientDto = clientService.findAll(pageable);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        ClientDto clientDto = clientService.findById(id);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> save(@Valid @RequestBody ClientDto clientDto) {
        clientDto = clientService.save(clientDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .queryParam("id", clientDto.getId()).build().toUri();
        return ResponseEntity.created(uri).body(clientDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @Valid @RequestBody ClientDto clientDto) {
        clientDto = clientService.update(id, clientDto);
        return ResponseEntity.ok(clientDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
