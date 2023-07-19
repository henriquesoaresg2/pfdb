package br.com.henriquesoaresg.pfdbbackend.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PessoaUpdateDto {
    @NotBlank(message = "Campo 'nome' não informado")
    private String nome;
    @NotBlank(message = "Campo 'cpf' não informado")
    @CPF(message = "Campo 'cpf' inválido")
    private String cpf;
    @NotNull(message = "Campo 'dataNascimento' não informado")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
}
