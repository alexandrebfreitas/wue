package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsCargaEnergiaProgramadaEntity.
 */
@Entity
@Table(name = "OnsTable23")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onscargaenergiaprogramada")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsCargaEnergiaProgramadaEntity implements Serializable {

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

    @Column(name = "val_cargaglobalprogramada")
    private Double valCargaglobalprogramada;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsCargaEnergiaProgramadaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodAreacarga() {
        return this.codAreacarga;
    }

    public OnsCargaEnergiaProgramadaEntity codAreacarga(String codAreacarga) {
        this.setCodAreacarga(codAreacarga);
        return this;
    }

    public void setCodAreacarga(String codAreacarga) {
        this.codAreacarga = codAreacarga;
    }

    public LocalDate getDatReferencia() {
        return this.datReferencia;
    }

    public OnsCargaEnergiaProgramadaEntity datReferencia(LocalDate datReferencia) {
        this.setDatReferencia(datReferencia);
        return this;
    }

    public void setDatReferencia(LocalDate datReferencia) {
        this.datReferencia = datReferencia;
    }

    public LocalDate getDinReferenciautc() {
        return this.dinReferenciautc;
    }

    public OnsCargaEnergiaProgramadaEntity dinReferenciautc(LocalDate dinReferenciautc) {
        this.setDinReferenciautc(dinReferenciautc);
        return this;
    }

    public void setDinReferenciautc(LocalDate dinReferenciautc) {
        this.dinReferenciautc = dinReferenciautc;
    }

    public Double getValCargaglobalprogramada() {
        return this.valCargaglobalprogramada;
    }

    public OnsCargaEnergiaProgramadaEntity valCargaglobalprogramada(Double valCargaglobalprogramada) {
        this.setValCargaglobalprogramada(valCargaglobalprogramada);
        return this;
    }

    public void setValCargaglobalprogramada(Double valCargaglobalprogramada) {
        this.valCargaglobalprogramada = valCargaglobalprogramada;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsCargaEnergiaProgramadaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsCargaEnergiaProgramadaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsCargaEnergiaProgramadaEntity{" +
            "id=" + getId() +
            ", codAreacarga='" + getCodAreacarga() + "'" +
            ", datReferencia='" + getDatReferencia() + "'" +
            ", dinReferenciautc='" + getDinReferenciautc() + "'" +
            ", valCargaglobalprogramada=" + getValCargaglobalprogramada() +
            "}";
    }
}
