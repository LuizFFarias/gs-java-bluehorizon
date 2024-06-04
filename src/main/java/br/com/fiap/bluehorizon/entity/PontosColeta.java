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
@Table(name = "PONTOS_COLETA")

public class PontosColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PONTOS_COLETA")
    @SequenceGenerator(name = "SQ_PONTOS_COLETA", sequenceName = "SQ_PONTOS_COLETA", allocationSize = 1)
    @Column(name = "ID_PONTO")
    private Long id;

    @Column(name = "NOME_PONTO")
    private String nome;

    @Column(name = "ESTADO_PONTO")
    private String estado;

    @Column(name = "GERENTE_PONTO")
    private String gerente;
}
