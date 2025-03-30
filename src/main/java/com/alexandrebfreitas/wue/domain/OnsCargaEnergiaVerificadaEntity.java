package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsCargaEnergiaVerificadaEntity.
 */
@Entity
@Table(name = "OnsTable45")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onscargaenergiaverificada")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsCargaEnergiaVerificadaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cod_areacarga")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codAreacarga;

    @Column(name = "dat_referencia")
    private LocalDate datReferencia;

    @Column(name = "din_referenciautc")
    private LocalDate dinReferenciautc;

    @Column(name = "val_cargaglobal")
    private Double valCargaglobal;

    @Column(name = "val_cargaglobalsmmg")
    private Double valCargaglobalsmmg;

    @Column(name = "val_cargammgd")
    private Double valCargammgd;

    @Column(name = "val_cargaglobalcons")
    private Double valCargaglobalcons;

    @Column(name = "val_consistencia")
    private Double valConsistencia;

    @Column(name = "val_cargasupervisionada")
    private Double valCargasupervisionada;

    @Column(name = "val_carganaosupervisionada")
    private Double valCarganaosupervisionada;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsCargaEnergiaVerificadaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodAreacarga() {
        return this.codAreacarga;
    }

    public OnsCargaEnergiaVerificadaEntity codAreacarga(String codAreacarga) {
        this.setCodAreacarga(codAreacarga);
        return this;
    }

    public void setCodAreacarga(String codAreacarga) {
        this.codAreacarga = codAreacarga;
    }

    public LocalDate getDatReferencia() {
        return this.datReferencia;
    }

    public OnsCargaEnergiaVerificadaEntity datReferencia(LocalDate datReferencia) {
        this.setDatReferencia(datReferencia);
        return this;
    }

    public void setDatReferencia(LocalDate datReferencia) {
        this.datReferencia = datReferencia;
    }

    public LocalDate getDinReferenciautc() {
        return this.dinReferenciautc;
    }

    public OnsCargaEnergiaVerificadaEntity dinReferenciautc(LocalDate dinReferenciautc) {
        this.setDinReferenciautc(dinReferenciautc);
        return this;
    }

    public void setDinReferenciautc(LocalDate dinReferenciautc) {
        this.dinReferenciautc = dinReferenciautc;
    }

    public Double getValCargaglobal() {
        return this.valCargaglobal;
    }

    public OnsCargaEnergiaVerificadaEntity valCargaglobal(Double valCargaglobal) {
        this.setValCargaglobal(valCargaglobal);
        return this;
    }

    public void setValCargaglobal(Double valCargaglobal) {
        this.valCargaglobal = valCargaglobal;
    }

    public Double getValCargaglobalsmmg() {
        return this.valCargaglobalsmmg;
    }

    public OnsCargaEnergiaVerificadaEntity valCargaglobalsmmg(Double valCargaglobalsmmg) {
        this.setValCargaglobalsmmg(valCargaglobalsmmg);
        return this;
    }

    public void setValCargaglobalsmmg(Double valCargaglobalsmmg) {
        this.valCargaglobalsmmg = valCargaglobalsmmg;
    }

    public Double getValCargammgd() {
        return this.valCargammgd;
    }

    public OnsCargaEnergiaVerificadaEntity valCargammgd(Double valCargammgd) {
        this.setValCargammgd(valCargammgd);
        return this;
    }

    public void setValCargammgd(Double valCargammgd) {
        this.valCargammgd = valCargammgd;
    }

    public Double getValCargaglobalcons() {
        return this.valCargaglobalcons;
    }

    public OnsCargaEnergiaVerificadaEntity valCargaglobalcons(Double valCargaglobalcons) {
        this.setValCargaglobalcons(valCargaglobalcons);
        return this;
    }

    public void setValCargaglobalcons(Double valCargaglobalcons) {
        this.valCargaglobalcons = valCargaglobalcons;
    }

    public Double getValConsistencia() {
        return this.valConsistencia;
    }

    public OnsCargaEnergiaVerificadaEntity valConsistencia(Double valConsistencia) {
        this.setValConsistencia(valConsistencia);
        return this;
    }

    public void setValConsistencia(Double valConsistencia) {
        this.valConsistencia = valConsistencia;
    }

    public Double getValCargasupervisionada() {
        return this.valCargasupervisionada;
    }

    public OnsCargaEnergiaVerificadaEntity valCargasupervisionada(Double valCargasupervisionada) {
        this.setValCargasupervisionada(valCargasupervisionada);
        return this;
    }

    public void setValCargasupervisionada(Double valCargasupervisionada) {
        this.valCargasupervisionada = valCargasupervisionada;
    }

    public Double getValCarganaosupervisionada() {
        return this.valCarganaosupervisionada;
    }

    public OnsCargaEnergiaVerificadaEntity valCarganaosupervisionada(Double valCarganaosupervisionada) {
        this.setValCarganaosupervisionada(valCarganaosupervisionada);
        return this;
    }

    public void setValCarganaosupervisionada(Double valCarganaosupervisionada) {
        this.valCarganaosupervisionada = valCarganaosupervisionada;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsCargaEnergiaVerificadaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsCargaEnergiaVerificadaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsCargaEnergiaVerificadaEntity{" +
            "id=" + getId() +
            ", codAreacarga='" + getCodAreacarga() + "'" +
            ", datReferencia='" + getDatReferencia() + "'" +
            ", dinReferenciautc='" + getDinReferenciautc() + "'" +
            ", valCargaglobal=" + getValCargaglobal() +
            ", valCargaglobalsmmg=" + getValCargaglobalsmmg() +
            ", valCargammgd=" + getValCargammgd() +
            ", valCargaglobalcons=" + getValCargaglobalcons() +
            ", valConsistencia=" + getValConsistencia() +
            ", valCargasupervisionada=" + getValCargasupervisionada() +
            ", valCarganaosupervisionada=" + getValCarganaosupervisionada() +
            "}";
    }
}
