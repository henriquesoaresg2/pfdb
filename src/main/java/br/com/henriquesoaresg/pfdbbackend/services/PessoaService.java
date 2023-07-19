package br.com.henriquesoaresg.pfdbbackend.services;

import br.com.henriquesoaresg.pfdbbackend.exceptions.BadRequestException;
import br.com.henriquesoaresg.pfdbbackend.exceptions.NotFoundException;
import br.com.henriquesoaresg.pfdbbackend.models.dtos.PessoaUpdateDto;
import br.com.henriquesoaresg.pfdbbackend.models.entities.Pessoa;
import br.com.henriquesoaresg.pfdbbackend.repositories.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;

@Service
public class PessoaService  implements Serializable {
    private final long serialVersionUID = 1L;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public Pessoa create(Pessoa body){
        return this.pessoaRepository.save(this.validateFutureDate(body));
    }

    public Page<Pessoa> readAll(Pageable pageable){
        return this.pessoaRepository.findAll(pageable);
    }

    public Pessoa readOneById(Long id){
        return this.pessoaRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada para este ID!"));
    }

    @Transactional
    public Pessoa updateById(Long id, PessoaUpdateDto body){
        Pessoa pessoa = this.pessoaRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada!"));

        pessoa.setNome(body.getNome());
        pessoa.setCpf(body.getCpf());
        pessoa.setDataNascimento(body.getDataNascimento());

        return this.pessoaRepository.save(validateFutureDate(pessoa));
    }

    @Transactional
    public void deleteById(Long id){
        Pessoa pessoa = this.pessoaRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada!"));
        this.pessoaRepository.deleteById(pessoa.getId());
    }

    public Pessoa validateFutureDate(Pessoa pessoa){
        if(pessoa.getDataNascimento().isAfter(LocalDate.now()))
            throw new BadRequestException("Data de nascimento não pode ser uma data futura!");

        return pessoa;
    }
}
