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
@Table(name = "SITUACAO_PRAIA")
public class SituacaoPraia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SITUACAO_PRAIA")
    @SequenceGenerator(name = "SQ_SITUACAO_PRAIA", sequenceName = "SQ_SITUACAO_PRAIA", allocationSize = 1)
    @Column(name = "ID_PRAIA")
    private Long id;

    @Column(name = "NOME_PRAIA")
    private String nome;

    @Column(name = "NIVELSUJEIRA_PRAIA")
    private Integer nivelSujeira;

    @Column(name = "CIDADE_PRAIA")
    private String cidade;
}
