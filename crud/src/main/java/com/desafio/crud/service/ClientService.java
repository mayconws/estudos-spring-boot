package com.desafio.crud.service;

import com.desafio.crud.dto.ClientDto;
import com.desafio.crud.model.Client;
import com.desafio.crud.repository.ClientRepository;
import com.desafio.crud.service.exception.DataBaseException;
import com.desafio.crud.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(ClientDto::new);
    }

    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente nao encontrado"));
        return new ClientDto(client);

    }

    @Transactional(readOnly = true)
    public ClientDto save(ClientDto clientDto) {
        Client client = new Client();
        copyDtoToEntity(clientDto, client);
        client = clientRepository.save(client);

        return new ClientDto(client);
    }

    @Transactional
    public ClientDto update(Long id, ClientDto clientDto) {
        try {
            Client client = clientRepository.getReferenceById(id);
            copyDtoToEntity(clientDto, client);
            client = clientRepository.save(client);
            return new ClientDto(client);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso nao encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso nao encontrado");
        }
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade de dados");
        }
    }

    private void copyDtoToEntity(ClientDto clientDto, Client client) {
        client.setName(clientDto.getName());
        client.setCpf(clientDto.getCpf());
        client.setIncome(clientDto.getIncome());
        client.setBirthDate(clientDto.getBirthDate());
        client.setChildren(clientDto.getChildren());
    }
}
