package br.com.henriquesoaresg.pfdbbackend.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contatos")
public class Contato implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Campo 'nome' não informado")
    private String nome;
    @Column(nullable = false)
    @NotBlank(message = "Campo 'telefone' não informado")
    private String telefone;
    @Column(nullable = false)
    @NotBlank(message = "Campo 'email' não informado")
    @Email(message = "Campo 'email' inválido")
    private String email;
    @ManyToOne
    @JoinColumn(name = "fk_id_pessoa")
    @JsonBackReference
    private Pessoa pessoa;
}
