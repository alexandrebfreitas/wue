package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsCmoSemanalEntity.
 */
@Entity
@Table(name = "OnsTable30")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onscmosemanal")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsCmoSemanalEntity implements Serializable {

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

    @Column(name = "val_cmomediasemanal")
    private Double valCmomediasemanal;

    @Column(name = "val_cmoleve")
    private Double valCmoleve;

    @Column(name = "val_cmomedia")
    private Double valCmomedia;

    @Column(name = "val_cmopesada")
    private Double valCmopesada;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsCmoSemanalEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsCmoSemanalEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsCmoSemanalEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsCmoSemanalEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public Double getValCmomediasemanal() {
        return this.valCmomediasemanal;
    }

    public OnsCmoSemanalEntity valCmomediasemanal(Double valCmomediasemanal) {
        this.setValCmomediasemanal(valCmomediasemanal);
        return this;
    }

    public void setValCmomediasemanal(Double valCmomediasemanal) {
        this.valCmomediasemanal = valCmomediasemanal;
    }

    public Double getValCmoleve() {
        return this.valCmoleve;
    }

    public OnsCmoSemanalEntity valCmoleve(Double valCmoleve) {
        this.setValCmoleve(valCmoleve);
        return this;
    }

    public void setValCmoleve(Double valCmoleve) {
        this.valCmoleve = valCmoleve;
    }

    public Double getValCmomedia() {
        return this.valCmomedia;
    }

    public OnsCmoSemanalEntity valCmomedia(Double valCmomedia) {
        this.setValCmomedia(valCmomedia);
        return this;
    }

    public void setValCmomedia(Double valCmomedia) {
        this.valCmomedia = valCmomedia;
    }

    public Double getValCmopesada() {
        return this.valCmopesada;
    }

    public OnsCmoSemanalEntity valCmopesada(Double valCmopesada) {
        this.setValCmopesada(valCmopesada);
        return this;
    }

    public void setValCmopesada(Double valCmopesada) {
        this.valCmopesada = valCmopesada;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsCmoSemanalEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsCmoSemanalEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsCmoSemanalEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", dinInstante='" + getDinInstante() + "'" +
            ", valCmomediasemanal=" + getValCmomediasemanal() +
            ", valCmoleve=" + getValCmoleve() +
            ", valCmomedia=" + getValCmomedia() +
            ", valCmopesada=" + getValCmopesada() +
            "}";
    }
}
