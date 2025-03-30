package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.
 */
@Entity
@Table(name = "OnsTable26")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsindicadoresconfiabilidaderedebasicasmseveridadeperturbacoes")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity implements Serializable {

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

    @Column(name = "val_sm_1")
    private Double valSm1;

    @Column(name = "val_sm_2")
    private Double valSm2;

    @Column(name = "val_sm_3")
    private Double valSm3;

    @Column(name = "val_sm_4")
    private Double valSm4;

    @Column(name = "val_sm_5")
    private Double valSm5;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDscAgregacao() {
        return this.dscAgregacao;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity dscAgregacao(String dscAgregacao) {
        this.setDscAgregacao(dscAgregacao);
        return this;
    }

    public void setDscAgregacao(String dscAgregacao) {
        this.dscAgregacao = dscAgregacao;
    }

    public String getCodCaracteristica() {
        return this.codCaracteristica;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity codCaracteristica(String codCaracteristica) {
        this.setCodCaracteristica(codCaracteristica);
        return this;
    }

    public void setCodCaracteristica(String codCaracteristica) {
        this.codCaracteristica = codCaracteristica;
    }

    public String getDscCaracteristica() {
        return this.dscCaracteristica;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity dscCaracteristica(String dscCaracteristica) {
        this.setDscCaracteristica(dscCaracteristica);
        return this;
    }

    public void setDscCaracteristica(String dscCaracteristica) {
        this.dscCaracteristica = dscCaracteristica;
    }

    public String getIdPeriodicidade() {
        return this.idPeriodicidade;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity idPeriodicidade(String idPeriodicidade) {
        this.setIdPeriodicidade(idPeriodicidade);
        return this;
    }

    public void setIdPeriodicidade(String idPeriodicidade) {
        this.idPeriodicidade = idPeriodicidade;
    }

    public LocalDate getDinReferencia() {
        return this.dinReferencia;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity dinReferencia(LocalDate dinReferencia) {
        this.setDinReferencia(dinReferencia);
        return this;
    }

    public void setDinReferencia(LocalDate dinReferencia) {
        this.dinReferencia = dinReferencia;
    }

    public Double getValSm1() {
        return this.valSm1;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity valSm1(Double valSm1) {
        this.setValSm1(valSm1);
        return this;
    }

    public void setValSm1(Double valSm1) {
        this.valSm1 = valSm1;
    }

    public Double getValSm2() {
        return this.valSm2;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity valSm2(Double valSm2) {
        this.setValSm2(valSm2);
        return this;
    }

    public void setValSm2(Double valSm2) {
        this.valSm2 = valSm2;
    }

    public Double getValSm3() {
        return this.valSm3;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity valSm3(Double valSm3) {
        this.setValSm3(valSm3);
        return this;
    }

    public void setValSm3(Double valSm3) {
        this.valSm3 = valSm3;
    }

    public Double getValSm4() {
        return this.valSm4;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity valSm4(Double valSm4) {
        this.setValSm4(valSm4);
        return this;
    }

    public void setValSm4(Double valSm4) {
        this.valSm4 = valSm4;
    }

    public Double getValSm5() {
        return this.valSm5;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity valSm5(Double valSm5) {
        this.setValSm5(valSm5);
        return this;
    }

    public void setValSm5(Double valSm5) {
        this.valSm5 = valSm5;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity{" +
            "id=" + getId() +
            ", dscAgregacao='" + getDscAgregacao() + "'" +
            ", codCaracteristica='" + getCodCaracteristica() + "'" +
            ", dscCaracteristica='" + getDscCaracteristica() + "'" +
            ", idPeriodicidade='" + getIdPeriodicidade() + "'" +
            ", dinReferencia='" + getDinReferencia() + "'" +
            ", valSm1=" + getValSm1() +
            ", valSm2=" + getValSm2() +
            ", valSm3=" + getValSm3() +
            ", valSm4=" + getValSm4() +
            ", valSm5=" + getValSm5() +
            "}";
    }
}
