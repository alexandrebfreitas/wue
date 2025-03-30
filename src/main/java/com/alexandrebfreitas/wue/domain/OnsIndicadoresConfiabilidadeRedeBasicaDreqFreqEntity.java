package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.
 */
@Entity
@Table(name = "OnsTable33")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsindicadoresconfiabilidaderedebasicadreqfreq")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dsc_agregacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String dscAgregacao;

    @Column(name = "cod_caracteristica")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codCaracteristica;

    @Column(name = "dsc_caracteristica")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String dscCaracteristica;

    @Column(name = "id_periodicidade")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idPeriodicidade;

    @Column(name = "din_referencia")
    private LocalDate dinReferencia;

    @Column(name = "val_dreq")
    private Double valDreq;

    @Column(name = "val_freq")
    private Double valFreq;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDscAgregacao() {
        return this.dscAgregacao;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity dscAgregacao(String dscAgregacao) {
        this.setDscAgregacao(dscAgregacao);
        return this;
    }

    public void setDscAgregacao(String dscAgregacao) {
        this.dscAgregacao = dscAgregacao;
    }

    public String getCodCaracteristica() {
        return this.codCaracteristica;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity codCaracteristica(String codCaracteristica) {
        this.setCodCaracteristica(codCaracteristica);
        return this;
    }

    public void setCodCaracteristica(String codCaracteristica) {
        this.codCaracteristica = codCaracteristica;
    }

    public String getDscCaracteristica() {
        return this.dscCaracteristica;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity dscCaracteristica(String dscCaracteristica) {
        this.setDscCaracteristica(dscCaracteristica);
        return this;
    }

    public void setDscCaracteristica(String dscCaracteristica) {
        this.dscCaracteristica = dscCaracteristica;
    }

    public String getIdPeriodicidade() {
        return this.idPeriodicidade;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity idPeriodicidade(String idPeriodicidade) {
        this.setIdPeriodicidade(idPeriodicidade);
        return this;
    }

    public void setIdPeriodicidade(String idPeriodicidade) {
        this.idPeriodicidade = idPeriodicidade;
    }

    public LocalDate getDinReferencia() {
        return this.dinReferencia;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity dinReferencia(LocalDate dinReferencia) {
        this.setDinReferencia(dinReferencia);
        return this;
    }

    public void setDinReferencia(LocalDate dinReferencia) {
        this.dinReferencia = dinReferencia;
    }

    public Double getValDreq() {
        return this.valDreq;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity valDreq(Double valDreq) {
        this.setValDreq(valDreq);
        return this;
    }

    public void setValDreq(Double valDreq) {
        this.valDreq = valDreq;
    }

    public Double getValFreq() {
        return this.valFreq;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity valFreq(Double valFreq) {
        this.setValFreq(valFreq);
        return this;
    }

    public void setValFreq(Double valFreq) {
        this.valFreq = valFreq;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity{" +
            "id=" + getId() +
            ", dscAgregacao='" + getDscAgregacao() + "'" +
            ", codCaracteristica='" + getCodCaracteristica() + "'" +
            ", dscCaracteristica='" + getDscCaracteristica() + "'" +
            ", idPeriodicidade='" + getIdPeriodicidade() + "'" +
            ", dinReferencia='" + getDinReferencia() + "'" +
            ", valDreq=" + getValDreq() +
            ", valFreq=" + getValFreq() +
            "}";
    }
}
