package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsCargaEnergiaDiariaEntity.
 */
@Entity
@Table(name = "OnsTable43")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onscargaenergiadiaria")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsCargaEnergiaDiariaEntity implements Serializable {

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

    @Column(name = "val_cargaenergiamwmed")
    private Double valCargaenergiamwmed;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsCargaEnergiaDiariaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsCargaEnergiaDiariaEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsCargaEnergiaDiariaEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsCargaEnergiaDiariaEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public Double getValCargaenergiamwmed() {
        return this.valCargaenergiamwmed;
    }

    public OnsCargaEnergiaDiariaEntity valCargaenergiamwmed(Double valCargaenergiamwmed) {
        this.setValCargaenergiamwmed(valCargaenergiamwmed);
        return this;
    }

    public void setValCargaenergiamwmed(Double valCargaenergiamwmed) {
        this.valCargaenergiamwmed = valCargaenergiamwmed;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsCargaEnergiaDiariaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsCargaEnergiaDiariaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsCargaEnergiaDiariaEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", dinInstante='" + getDinInstante() + "'" +
            ", valCargaenergiamwmed=" + getValCargaenergiamwmed() +
            "}";
    }
}
