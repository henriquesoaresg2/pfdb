package br.com.henriquesoaresg.pfdbbackend.repositories;

import br.com.henriquesoaresg.pfdbbackend.models.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}