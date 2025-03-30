package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity.
 */
@Entity
@Table(name = "OnsTable18")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(
    indexName = "onsdadosindicadoresconfiabilidaderedebasicaccalcontrolecarregamentolinhastransmissao"
)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cod_tipoagregacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codTipoagregacao;

    @Column(name = "id_periodicidade")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idPeriodicidade;

    @Column(name = "nom_agregacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomAgregacao;

    @Column(name = "din_referencia")
    private LocalDate dinReferencia;

    @Column(name = "num_linhasoperacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numLinhasoperacao;

    @Column(name = "num_linhasvioladas")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numLinhasvioladas;

    @Column(name = "val_ccal")
    private Double valCcal;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodTipoagregacao() {
        return this.codTipoagregacao;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity codTipoagregacao(
        String codTipoagregacao
    ) {
        this.setCodTipoagregacao(codTipoagregacao);
        return this;
    }

    public void setCodTipoagregacao(String codTipoagregacao) {
        this.codTipoagregacao = codTipoagregacao;
    }

    public String getIdPeriodicidade() {
        return this.idPeriodicidade;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity idPeriodicidade(
        String idPeriodicidade
    ) {
        this.setIdPeriodicidade(idPeriodicidade);
        return this;
    }

    public void setIdPeriodicidade(String idPeriodicidade) {
        this.idPeriodicidade = idPeriodicidade;
    }

    public String getNomAgregacao() {
        return this.nomAgregacao;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity nomAgregacao(String nomAgregacao) {
        this.setNomAgregacao(nomAgregacao);
        return this;
    }

    public void setNomAgregacao(String nomAgregacao) {
        this.nomAgregacao = nomAgregacao;
    }

    public LocalDate getDinReferencia() {
        return this.dinReferencia;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity dinReferencia(
        LocalDate dinReferencia
    ) {
        this.setDinReferencia(dinReferencia);
        return this;
    }

    public void setDinReferencia(LocalDate dinReferencia) {
        this.dinReferencia = dinReferencia;
    }

    public Integer getNumLinhasoperacao() {
        return this.numLinhasoperacao;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity numLinhasoperacao(
        Integer numLinhasoperacao
    ) {
        this.setNumLinhasoperacao(numLinhasoperacao);
        return this;
    }

    public void setNumLinhasoperacao(Integer numLinhasoperacao) {
        this.numLinhasoperacao = numLinhasoperacao;
    }

    public Integer getNumLinhasvioladas() {
        return this.numLinhasvioladas;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity numLinhasvioladas(
        Integer numLinhasvioladas
    ) {
        this.setNumLinhasvioladas(numLinhasvioladas);
        return this;
    }

    public void setNumLinhasvioladas(Integer numLinhasvioladas) {
        this.numLinhasvioladas = numLinhasvioladas;
    }

    public Double getValCcal() {
        return this.valCcal;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity valCcal(Double valCcal) {
        this.setValCcal(valCcal);
        return this;
    }

    public void setValCcal(Double valCcal) {
        this.valCcal = valCcal;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity)) {
            return false;
        }
        return (
            getId() != null &&
            getId().equals(((OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity) o).getId())
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
        return "OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity{" +
            "id=" + getId() +
            ", codTipoagregacao='" + getCodTipoagregacao() + "'" +
            ", idPeriodicidade='" + getIdPeriodicidade() + "'" +
            ", nomAgregacao='" + getNomAgregacao() + "'" +
            ", dinReferencia='" + getDinReferencia() + "'" +
            ", numLinhasoperacao=" + getNumLinhasoperacao() +
            ", numLinhasvioladas=" + getNumLinhasvioladas() +
            ", valCcal=" + getValCcal() +
            "}";
    }
}
