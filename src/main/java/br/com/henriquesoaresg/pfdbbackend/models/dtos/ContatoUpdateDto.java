package br.com.henriquesoaresg.pfdbbackend.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ContatoUpdateDto {
    @NotBlank(message = "Campo 'nome' não informado")
    private String nome;
    @NotBlank(message = "Campo 'telefone' não informado")
    private String telefone;
    @NotBlank(message = "Campo 'email' não informado")
    @Email(message = "Campo 'email' inválido")
    private String email;
}
