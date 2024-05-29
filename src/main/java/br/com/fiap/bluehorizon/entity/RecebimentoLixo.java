package br.com.fiap.bluehorizon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "RECEBIMENTO_LIXO")
public class RecebimentoLixo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RECEBIMENTO_LIXO")
    @SequenceGenerator(name = "SQ_RECEBIMENTO_LIXO", sequenceName = "SQ_RECEBIMENTO_LIXO", allocationSize = 1)
    @Column(name = "ID_RECEBIMENTO")
    private Long id;

    @Column(name = "DT_RECEBIMENTO")
    private LocalDate  dataRecebimento;

    @ManyToOne( fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "VOLUNTARIO_PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "FK_RECEBIMENTO_LIXO_VOLUNTARIO_PESSOA")
    )
    private VoluntarioPessoa pessoa;

    @ManyToOne( fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "VOLUNTARIO_PERFIL",
            referencedColumnName = "ID_PERFIL",
            foreignKey = @ForeignKey(name = "FK_RECEBIMENTO_LIXO_VOLUNTARIO_PERFIL")
    )

    private VoluntarioPerfil perfil;

    @ManyToOne( fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "PONTOS_COLETA",
            referencedColumnName = "ID_PONTO",
            foreignKey = @ForeignKey(name = "FK_RECEBIMENTO_LIXO_PONTOS_COLETA")
    )

    private PontosColeta pontosColeta;

    @ManyToOne( fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "TIPOS_LIXO",
            referencedColumnName = "ID_LIXO",
            foreignKey = @ForeignKey(name = "FK_RECEBIMENTO_LIXO_TIPOS_LIXO")
    )

    private TiposLixo tiposLixo;



}
