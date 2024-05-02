package com.vinifillos.demoparkapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "vagas")
@EntityListeners(AuditingEntityListener.class)
public class Vaga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo", nullable = false, unique = true, length = 4)
    private String codigo;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusVaga status;

    @CreatedDate
    @Column(name = "dataCriacao")
    private LocalDateTime dataCriacao;
    @LastModifiedDate
    @Column(name = "dataModificacao")
    private LocalDateTime dataModificacao;
    @CreatedBy
    @Column(name = "ciradoPor")
    private String ciradoPor;
    @LastModifiedBy
    @Column(name = "modificadoPor")
    private String modificadoPor;

    public enum StatusVaga {
        LIVRE,
        OCUPADA
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vaga vaga = (Vaga) o;
        return id.equals(vaga.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
