package br.com.henriquesoaresg.pfdbbackend.services;

import br.com.henriquesoaresg.pfdbbackend.models.entities.Contato;
import br.com.henriquesoaresg.pfdbbackend.models.entities.Pessoa;
import br.com.henriquesoaresg.pfdbbackend.repositories.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {
    @InjectMocks
    PessoaService pessoaService;
    @Mock
    PessoaRepository pessoaRepository;

    Pessoa pessoaToSave;
    Pessoa pessoaSaved;
    Contato contato;
    List<Contato> contatos = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        contato = Contato.builder()
                .nome("contato1")
                .telefone("99994-9994")
                .email("email@email.com")
                .build();
        contatos.add(contato);
        pessoaToSave = Pessoa.builder()
                .nome("pessoaSalva")
                .cpf("52063352070")
                .dataNascimento(LocalDate.of(1989, 5,18))
                .contatos(contatos)
                .build();
        pessoaSaved = pessoaToSave;
        pessoaSaved.setId(1L);
    }

    @Test
    void shouldCreateOnePessoa(){
        when(this.pessoaRepository.save(pessoaToSave)).thenReturn(pessoaSaved);
        Pessoa pessoa = this.pessoaService.create(pessoaToSave);

        assertEquals(pessoa, pessoaSaved);
        verify(this.pessoaRepository).save(pessoaToSave);
        verifyNoMoreInteractions(this.pessoaRepository);
    }

    @Test
    void shouldFindOnePessoa(){
        when(this.pessoaRepository.findById(pessoaSaved.getId())).thenReturn(Optional.ofNullable(pessoaSaved));
        Pessoa pessoa = this.pessoaService.readOneById(pessoaSaved.getId());

        assertEquals(pessoa, pessoaSaved);
        verify(this.pessoaRepository).findById(pessoaSaved.getId());
        verifyNoMoreInteractions(this.pessoaRepository);
    }
}