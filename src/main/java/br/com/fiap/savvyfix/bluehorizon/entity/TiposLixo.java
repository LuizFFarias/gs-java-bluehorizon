package br.com.fiap.savvyfix.bluehorizon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TIPOS_LIXO")

public class TiposLixo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPOS_LIXO")
    @SequenceGenerator(name = "SQ_TIPOS_LIXO", sequenceName = "SQ_TIPOS_LIXO", allocationSize = 1)
    @Column(name = "ID_LIXO")
    private Long id;

    @Column(name = "NOME_LIXO")
    private String nome;

    @Column(name = "VALORKG_LIXO")
    private BigDecimal valorKg;

}
