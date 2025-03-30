package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.
 */
@Entity
@Table(name = "OnsTable50")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsindicadoresconfiabilidaderedebasicarobustezrmalrmcsrrberrbcs")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cod_indicador")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codIndicador;

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

    @Column(name = "val_indicador")
    private Double valIndicador;

    @Column(name = "num_perturbacoes")
    private Double numPerturbacoes;

    @Column(name = "num_perturbacoescortecarga")
    private Double numPerturbacoescortecarga;

    @Column(name = "num_perturbacoescortecarga_0_a_50_mw")
    private Double numPerturbacoescortecarga0a50mw;

    @Column(name = "num_perturbacoescortecarga_50_a_100_mw")
    private Double numPerturbacoescortecarga50a100mw;

    @Column(name = "num_perturbacoescortecarga_maior_100_mw")
    private Double numPerturbacoescortecargaMaior100mw;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodIndicador() {
        return this.codIndicador;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity codIndicador(String codIndicador) {
        this.setCodIndicador(codIndicador);
        return this;
    }

    public void setCodIndicador(String codIndicador) {
        this.codIndicador = codIndicador;
    }

    public String getDscAgregacao() {
        return this.dscAgregacao;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity dscAgregacao(String dscAgregacao) {
        this.setDscAgregacao(dscAgregacao);
        return this;
    }

    public void setDscAgregacao(String dscAgregacao) {
        this.dscAgregacao = dscAgregacao;
    }

    public String getCodCaracteristica() {
        return this.codCaracteristica;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity codCaracteristica(String codCaracteristica) {
        this.setCodCaracteristica(codCaracteristica);
        return this;
    }

    public void setCodCaracteristica(String codCaracteristica) {
        this.codCaracteristica = codCaracteristica;
    }

    public String getDscCaracteristica() {
        return this.dscCaracteristica;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity dscCaracteristica(String dscCaracteristica) {
        this.setDscCaracteristica(dscCaracteristica);
        return this;
    }

    public void setDscCaracteristica(String dscCaracteristica) {
        this.dscCaracteristica = dscCaracteristica;
    }

    public String getIdPeriodicidade() {
        return this.idPeriodicidade;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity idPeriodicidade(String idPeriodicidade) {
        this.setIdPeriodicidade(idPeriodicidade);
        return this;
    }

    public void setIdPeriodicidade(String idPeriodicidade) {
        this.idPeriodicidade = idPeriodicidade;
    }

    public LocalDate getDinReferencia() {
        return this.dinReferencia;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity dinReferencia(LocalDate dinReferencia) {
        this.setDinReferencia(dinReferencia);
        return this;
    }

    public void setDinReferencia(LocalDate dinReferencia) {
        this.dinReferencia = dinReferencia;
    }

    public Double getValIndicador() {
        return this.valIndicador;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity valIndicador(Double valIndicador) {
        this.setValIndicador(valIndicador);
        return this;
    }

    public void setValIndicador(Double valIndicador) {
        this.valIndicador = valIndicador;
    }

    public Double getNumPerturbacoes() {
        return this.numPerturbacoes;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity numPerturbacoes(Double numPerturbacoes) {
        this.setNumPerturbacoes(numPerturbacoes);
        return this;
    }

    public void setNumPerturbacoes(Double numPerturbacoes) {
        this.numPerturbacoes = numPerturbacoes;
    }

    public Double getNumPerturbacoescortecarga() {
        return this.numPerturbacoescortecarga;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity numPerturbacoescortecarga(
        Double numPerturbacoescortecarga
    ) {
        this.setNumPerturbacoescortecarga(numPerturbacoescortecarga);
        return this;
    }

    public void setNumPerturbacoescortecarga(Double numPerturbacoescortecarga) {
        this.numPerturbacoescortecarga = numPerturbacoescortecarga;
    }

    public Double getNumPerturbacoescortecarga0a50mw() {
        return this.numPerturbacoescortecarga0a50mw;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity numPerturbacoescortecarga0a50mw(
        Double numPerturbacoescortecarga0a50mw
    ) {
        this.setNumPerturbacoescortecarga0a50mw(numPerturbacoescortecarga0a50mw);
        return this;
    }

    public void setNumPerturbacoescortecarga0a50mw(Double numPerturbacoescortecarga0a50mw) {
        this.numPerturbacoescortecarga0a50mw = numPerturbacoescortecarga0a50mw;
    }

    public Double getNumPerturbacoescortecarga50a100mw() {
        return this.numPerturbacoescortecarga50a100mw;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity numPerturbacoescortecarga50a100mw(
        Double numPerturbacoescortecarga50a100mw
    ) {
        this.setNumPerturbacoescortecarga50a100mw(numPerturbacoescortecarga50a100mw);
        return this;
    }

    public void setNumPerturbacoescortecarga50a100mw(Double numPerturbacoescortecarga50a100mw) {
        this.numPerturbacoescortecarga50a100mw = numPerturbacoescortecarga50a100mw;
    }

    public Double getNumPerturbacoescortecargaMaior100mw() {
        return this.numPerturbacoescortecargaMaior100mw;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity numPerturbacoescortecargaMaior100mw(
        Double numPerturbacoescortecargaMaior100mw
    ) {
        this.setNumPerturbacoescortecargaMaior100mw(numPerturbacoescortecargaMaior100mw);
        return this;
    }

    public void setNumPerturbacoescortecargaMaior100mw(Double numPerturbacoescortecargaMaior100mw) {
        this.numPerturbacoescortecargaMaior100mw = numPerturbacoescortecargaMaior100mw;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity{" +
            "id=" + getId() +
            ", codIndicador='" + getCodIndicador() + "'" +
            ", dscAgregacao='" + getDscAgregacao() + "'" +
            ", codCaracteristica='" + getCodCaracteristica() + "'" +
            ", dscCaracteristica='" + getDscCaracteristica() + "'" +
            ", idPeriodicidade='" + getIdPeriodicidade() + "'" +
            ", dinReferencia='" + getDinReferencia() + "'" +
            ", valIndicador=" + getValIndicador() +
            ", numPerturbacoes=" + getNumPerturbacoes() +
            ", numPerturbacoescortecarga=" + getNumPerturbacoescortecarga() +
            ", numPerturbacoescortecarga0a50mw=" + getNumPerturbacoescortecarga0a50mw() +
            ", numPerturbacoescortecarga50a100mw=" + getNumPerturbacoescortecarga50a100mw() +
            ", numPerturbacoescortecargaMaior100mw=" + getNumPerturbacoescortecargaMaior100mw() +
            "}";
    }
}
