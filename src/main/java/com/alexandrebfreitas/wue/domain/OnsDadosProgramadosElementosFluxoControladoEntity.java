package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosProgramadosElementosFluxoControladoEntity.
 */
@Entity
@Table(name = "OnsTable21")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadosprogramadoselementosfluxocontrolado")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosProgramadosElementosFluxoControladoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "din_programacaodia")
    private LocalDate dinProgramacaodia;

    @Column(name = "num_patamar")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numPatamar;

    @Column(name = "nom_elementofluxocontrolado")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomElementofluxocontrolado;

    @Column(name = "dsc_elementofluxocontrolado")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String dscElementofluxocontrolado;

    @Column(name = "tip_terminal")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer tipTerminal;

    @Column(name = "cod_submercado")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codSubmercado;

    @Column(name = "val_carga")
    private Double valCarga;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosProgramadosElementosFluxoControladoEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDinProgramacaodia() {
        return this.dinProgramacaodia;
    }

    public OnsDadosProgramadosElementosFluxoControladoEntity dinProgramacaodia(LocalDate dinProgramacaodia) {
        this.setDinProgramacaodia(dinProgramacaodia);
        return this;
    }

    public void setDinProgramacaodia(LocalDate dinProgramacaodia) {
        this.dinProgramacaodia = dinProgramacaodia;
    }

    public Integer getNumPatamar() {
        return this.numPatamar;
    }

    public OnsDadosProgramadosElementosFluxoControladoEntity numPatamar(Integer numPatamar) {
        this.setNumPatamar(numPatamar);
        return this;
    }

    public void setNumPatamar(Integer numPatamar) {
        this.numPatamar = numPatamar;
    }

    public String getNomElementofluxocontrolado() {
        return this.nomElementofluxocontrolado;
    }

    public OnsDadosProgramadosElementosFluxoControladoEntity nomElementofluxocontrolado(String nomElementofluxocontrolado) {
        this.setNomElementofluxocontrolado(nomElementofluxocontrolado);
        return this;
    }

    public void setNomElementofluxocontrolado(String nomElementofluxocontrolado) {
        this.nomElementofluxocontrolado = nomElementofluxocontrolado;
    }

    public String getDscElementofluxocontrolado() {
        return this.dscElementofluxocontrolado;
    }

    public OnsDadosProgramadosElementosFluxoControladoEntity dscElementofluxocontrolado(String dscElementofluxocontrolado) {
        this.setDscElementofluxocontrolado(dscElementofluxocontrolado);
        return this;
    }

    public void setDscElementofluxocontrolado(String dscElementofluxocontrolado) {
        this.dscElementofluxocontrolado = dscElementofluxocontrolado;
    }

    public Integer getTipTerminal() {
        return this.tipTerminal;
    }

    public OnsDadosProgramadosElementosFluxoControladoEntity tipTerminal(Integer tipTerminal) {
        this.setTipTerminal(tipTerminal);
        return this;
    }

    public void setTipTerminal(Integer tipTerminal) {
        this.tipTerminal = tipTerminal;
    }

    public String getCodSubmercado() {
        return this.codSubmercado;
    }

    public OnsDadosProgramadosElementosFluxoControladoEntity codSubmercado(String codSubmercado) {
        this.setCodSubmercado(codSubmercado);
        return this;
    }

    public void setCodSubmercado(String codSubmercado) {
        this.codSubmercado = codSubmercado;
    }

    public Double getValCarga() {
        return this.valCarga;
    }

    public OnsDadosProgramadosElementosFluxoControladoEntity valCarga(Double valCarga) {
        this.setValCarga(valCarga);
        return this;
    }

    public void setValCarga(Double valCarga) {
        this.valCarga = valCarga;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosProgramadosElementosFluxoControladoEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosProgramadosElementosFluxoControladoEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosProgramadosElementosFluxoControladoEntity{" +
            "id=" + getId() +
            ", dinProgramacaodia='" + getDinProgramacaodia() + "'" +
            ", numPatamar=" + getNumPatamar() +
            ", nomElementofluxocontrolado='" + getNomElementofluxocontrolado() + "'" +
            ", dscElementofluxocontrolado='" + getDscElementofluxocontrolado() + "'" +
            ", tipTerminal=" + getTipTerminal() +
            ", codSubmercado='" + getCodSubmercado() + "'" +
            ", valCarga=" + getValCarga() +
            "}";
    }
}
