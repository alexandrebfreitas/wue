package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosHidrologicosVolumeEsperaRecomendadoEntity.
 */
@Entity
@Table(name = "OnsTable14")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadoshidrologicosvolumeesperarecomendado")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosHidrologicosVolumeEsperaRecomendadoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    @Column(name = "val_volumeespera")
    private Double valVolumeespera;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosHidrologicosVolumeEsperaRecomendadoEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsDadosHidrologicosVolumeEsperaRecomendadoEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public Double getValVolumeespera() {
        return this.valVolumeespera;
    }

    public OnsDadosHidrologicosVolumeEsperaRecomendadoEntity valVolumeespera(Double valVolumeespera) {
        this.setValVolumeespera(valVolumeespera);
        return this;
    }

    public void setValVolumeespera(Double valVolumeespera) {
        this.valVolumeespera = valVolumeespera;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosHidrologicosVolumeEsperaRecomendadoEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosHidrologicosVolumeEsperaRecomendadoEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosHidrologicosVolumeEsperaRecomendadoEntity{" +
            "id=" + getId() +
            ", dinInstante='" + getDinInstante() + "'" +
            ", valVolumeespera=" + getValVolumeespera() +
            "}";
    }
}
