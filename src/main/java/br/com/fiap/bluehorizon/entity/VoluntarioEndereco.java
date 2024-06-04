package br.com.fiap.bluehorizon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "voluntario_endereco")

public class VoluntarioEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VOLUNTARIO_ENDERECO")
    @SequenceGenerator(name = "SQ_VOLUNTARIO_ENDERECO", sequenceName = "SQ_VOLUNTARIO_ENDERECO", allocationSize = 1)
    @Column(name = "ID_END")
    private Long id;

    @Column(name = "CEP_END")
    private String cep;

    @Column(name = "RUA_END")
    private String rua;

    @Column(name = "NUM_END")
    private String numero;

    @Column(name = "BAIRRO_END")
    private String bairro;

    @Column(name = "CIDADE_END")
    private String cidade;

    @Column(name = "ESTADO_END")
    private String estado;

    @Column(name = "PAIS_END")
    private String pais;

}
