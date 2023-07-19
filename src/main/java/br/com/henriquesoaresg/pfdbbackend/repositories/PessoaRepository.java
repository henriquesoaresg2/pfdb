package br.com.henriquesoaresg.pfdbbackend.repositories;

import br.com.henriquesoaresg.pfdbbackend.models.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {}