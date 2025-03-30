package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.
 */
@Entity
@Table(name = "OnsTable41")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(
    indexName = "onsindicadoresconfiabilidaderedebasicacipercargainterrompidaperturbacoes"
)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity implements Serializable {

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

    @Column(name = "val_ciper_1")
    private Double valCiper1;

    @Column(name = "val_ciper_2")
    private Double valCiper2;

    @Column(name = "val_ciper_3")
    private Double valCiper3;

    @Column(name = "val_ciper_4")
    private Double valCiper4;

    @Column(name = "val_ciper_5")
    private Double valCiper5;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDscAgregacao() {
        return this.dscAgregacao;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity dscAgregacao(String dscAgregacao) {
        this.setDscAgregacao(dscAgregacao);
        return this;
    }

    public void setDscAgregacao(String dscAgregacao) {
        this.dscAgregacao = dscAgregacao;
    }

    public String getCodCaracteristica() {
        return this.codCaracteristica;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity codCaracteristica(String codCaracteristica) {
        this.setCodCaracteristica(codCaracteristica);
        return this;
    }

    public void setCodCaracteristica(String codCaracteristica) {
        this.codCaracteristica = codCaracteristica;
    }

    public String getDscCaracteristica() {
        return this.dscCaracteristica;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity dscCaracteristica(String dscCaracteristica) {
        this.setDscCaracteristica(dscCaracteristica);
        return this;
    }

    public void setDscCaracteristica(String dscCaracteristica) {
        this.dscCaracteristica = dscCaracteristica;
    }

    public String getIdPeriodicidade() {
        return this.idPeriodicidade;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity idPeriodicidade(String idPeriodicidade) {
        this.setIdPeriodicidade(idPeriodicidade);
        return this;
    }

    public void setIdPeriodicidade(String idPeriodicidade) {
        this.idPeriodicidade = idPeriodicidade;
    }

    public LocalDate getDinReferencia() {
        return this.dinReferencia;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity dinReferencia(LocalDate dinReferencia) {
        this.setDinReferencia(dinReferencia);
        return this;
    }

    public void setDinReferencia(LocalDate dinReferencia) {
        this.dinReferencia = dinReferencia;
    }

    public Double getValCiper1() {
        return this.valCiper1;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity valCiper1(Double valCiper1) {
        this.setValCiper1(valCiper1);
        return this;
    }

    public void setValCiper1(Double valCiper1) {
        this.valCiper1 = valCiper1;
    }

    public Double getValCiper2() {
        return this.valCiper2;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity valCiper2(Double valCiper2) {
        this.setValCiper2(valCiper2);
        return this;
    }

    public void setValCiper2(Double valCiper2) {
        this.valCiper2 = valCiper2;
    }

    public Double getValCiper3() {
        return this.valCiper3;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity valCiper3(Double valCiper3) {
        this.setValCiper3(valCiper3);
        return this;
    }

    public void setValCiper3(Double valCiper3) {
        this.valCiper3 = valCiper3;
    }

    public Double getValCiper4() {
        return this.valCiper4;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity valCiper4(Double valCiper4) {
        this.setValCiper4(valCiper4);
        return this;
    }

    public void setValCiper4(Double valCiper4) {
        this.valCiper4 = valCiper4;
    }

    public Double getValCiper5() {
        return this.valCiper5;
    }

    public OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity valCiper5(Double valCiper5) {
        this.setValCiper5(valCiper5);
        return this;
    }

    public void setValCiper5(Double valCiper5) {
        this.valCiper5 = valCiper5;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity)) {
            return false;
        }
        return (
            getId() != null && getId().equals(((OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity) o).getId())
        );
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity{" +
            "id=" + getId() +
            ", dscAgregacao='" + getDscAgregacao() + "'" +
            ", codCaracteristica='" + getCodCaracteristica() + "'" +
            ", dscCaracteristica='" + getDscCaracteristica() + "'" +
            ", idPeriodicidade='" + getIdPeriodicidade() + "'" +
            ", dinReferencia='" + getDinReferencia() + "'" +
            ", valCiper1=" + getValCiper1() +
            ", valCiper2=" + getValCiper2() +
            ", valCiper3=" + getValCiper3() +
            ", valCiper4=" + getValCiper4() +
            ", valCiper5=" + getValCiper5() +
            "}";
    }
}
