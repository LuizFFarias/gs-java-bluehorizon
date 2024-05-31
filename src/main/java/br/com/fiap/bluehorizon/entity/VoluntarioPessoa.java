package br.com.fiap.bluehorizon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "VOLUNTARIO_PESSOA", uniqueConstraints = @UniqueConstraint(name = "UK_CPF_VOLUNTARIO_PESSOA", columnNames = "CPF_PESSOA"))

public class VoluntarioPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VOLUNTARIO_PESSOA")
    @SequenceGenerator(name = "SQ_VOLUNTARIO_PESSOA", sequenceName = "SQ_VOLUNTARIO_PESSOA", allocationSize = 1)
    @Column(name = "ID_PESSOA")
    private Long id;

    @Column(name = "CPF_PESSOA")
    private String cpf;

    @Column(name = "NOME_PESSOA")
    private String nome;

    @Column(name = "DTNASC_PESSOA")
    private LocalDate dtNascimento;


    @Column(name = "SENHA_PESSOA")
    private String senha;

    @ManyToOne( fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "VOLUNTARIO_ENDERECO",
            referencedColumnName = "ID_END",
            foreignKey = @ForeignKey(name = "FK_VOLUNTARIO_PESSOA_VOLUNTARIO_ENDERECO")
    )

    private VoluntarioEndereco endereco;

    @OneToOne( fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "VOLUNTARIO_PERFIL",
            referencedColumnName = "ID_PERFIl",
            foreignKey = @ForeignKey(name = "FK_VOLUNTARIO_PESSOA_VOLUNTARIO_PERFIL")
    )

    private VoluntarioPerfil perfil;


}
