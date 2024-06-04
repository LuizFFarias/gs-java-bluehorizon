package br.com.fiap.bluehorizon.entity;

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
@Table(name = "TIPOS_LIXO", uniqueConstraints = @UniqueConstraint(name = "UK_NOME_TIPOSLIXO", columnNames = "NOME_LIXO"))

public class TiposLixo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPOS_LIXO")
    @SequenceGenerator(name = "SQ_TIPOS_LIXO", sequenceName = "SQ_TIPOS_LIXO", allocationSize = 1)
    @Column(name = "ID_LIXO")
    private Long id;

    @Column(name = "NOME_LIXO")
    private String nome;

    @Column(name = "VALORKG_LIXO", precision = 4, scale = 2)
    private BigDecimal valorKg;

}
