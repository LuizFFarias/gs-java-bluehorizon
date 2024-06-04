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
@Table(name = "VOLUNTARIO_PERFIL")

public class VoluntarioPerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VOLUNTARIO_PERFIL")
    @SequenceGenerator(name = "SQ_VOLUNTARIO_PERFIL", sequenceName = "SQ_VOLUNTARIO_PERFIL", allocationSize = 1)
    @Column(name = "ID_PERFIL")
    private Long id;

    @Column(name = "QNTDLIXORETIRADO_PERFIL")
    private Float qntdLixo;
}
