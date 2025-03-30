package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsCurvaCargaHorariaEntity.
 */
@Entity
@Table(name = "OnsTable1")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onscurvacargahoraria")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsCurvaCargaHorariaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_subsistema")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idSubsistema;

    @Column(name = "nom_subsistema")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubsistema;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    @Column(name = "val_cargaenergiahomwmed")
    private Double valCargaenergiahomwmed;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsCurvaCargaHorariaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsCurvaCargaHorariaEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsCurvaCargaHorariaEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsCurvaCargaHorariaEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public Double getValCargaenergiahomwmed() {
        return this.valCargaenergiahomwmed;
    }

    public OnsCurvaCargaHorariaEntity valCargaenergiahomwmed(Double valCargaenergiahomwmed) {
        this.setValCargaenergiahomwmed(valCargaenergiahomwmed);
        return this;
    }

    public void setValCargaenergiahomwmed(Double valCargaenergiahomwmed) {
        this.valCargaenergiahomwmed = valCargaenergiahomwmed;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsCurvaCargaHorariaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsCurvaCargaHorariaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsCurvaCargaHorariaEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", dinInstante='" + getDinInstante() + "'" +
            ", valCargaenergiahomwmed=" + getValCargaenergiahomwmed() +
            "}";
    }
}
