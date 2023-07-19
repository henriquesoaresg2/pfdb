package br.com.henriquesoaresg.pfdbbackend.services;

import br.com.henriquesoaresg.pfdbbackend.exceptions.NotFoundException;
import br.com.henriquesoaresg.pfdbbackend.models.dtos.ContatoUpdateDto;
import br.com.henriquesoaresg.pfdbbackend.models.entities.Contato;
import br.com.henriquesoaresg.pfdbbackend.repositories.ContatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;

    public Contato findById(Long id){
        return this.contatoRepository.findById(id).orElseThrow(() -> new NotFoundException("Contato não encontrado"));
    }

    @Transactional
    public Contato updateById(Long id, ContatoUpdateDto body){
        Contato contato = this.contatoRepository.findById(id).orElseThrow(() -> new NotFoundException("Contato não encontrado!"));

        contato.setNome(body.getNome());
        contato.setTelefone(body.getTelefone());
        contato.setEmail(body.getEmail());

        return this.contatoRepository.save(contato);
    }
}
