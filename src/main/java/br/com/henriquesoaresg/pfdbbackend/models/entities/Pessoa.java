package br.com.henriquesoaresg.pfdbbackend.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Campo 'nome' não informado")
    private String nome;
    @Column(nullable = false)
    @NotBlank(message = "Campo 'cpf' não informado")
    @CPF(message = "Campo 'cpf' inválido")
    private String cpf;
    @Column(name = "data_nascimento", nullable = false)
    @NotNull(message = "Campo 'dataNascimento' não informado")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private List<Contato> contatos;

    public void addContato(Contato contato) {
        contatos.add(contato);
    }
    public void removeContato(Contato contato){
        contatos.remove(contato);
    }
}
