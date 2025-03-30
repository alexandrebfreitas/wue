package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.
 */
@Entity
@Table(name = "OnsTable16")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(
    indexName = "onsdadosindicadoresconfiabilidaderedebasicaccatcontrolecarregamentotransformadores"
)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity implements Serializable {

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

    @Column(name = "num_transformadoresoperacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numTransformadoresoperacao;

    @Column(name = "num_transformadoresviolados")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numTransformadoresviolados;

    @Column(name = "val_ccat")
    private Double valCcat;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodTipoagregacao() {
        return this.codTipoagregacao;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity codTipoagregacao(
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

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity idPeriodicidade(
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

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity nomAgregacao(String nomAgregacao) {
        this.setNomAgregacao(nomAgregacao);
        return this;
    }

    public void setNomAgregacao(String nomAgregacao) {
        this.nomAgregacao = nomAgregacao;
    }

    public LocalDate getDinReferencia() {
        return this.dinReferencia;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity dinReferencia(LocalDate dinReferencia) {
        this.setDinReferencia(dinReferencia);
        return this;
    }

    public void setDinReferencia(LocalDate dinReferencia) {
        this.dinReferencia = dinReferencia;
    }

    public Integer getNumTransformadoresoperacao() {
        return this.numTransformadoresoperacao;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity numTransformadoresoperacao(
        Integer numTransformadoresoperacao
    ) {
        this.setNumTransformadoresoperacao(numTransformadoresoperacao);
        return this;
    }

    public void setNumTransformadoresoperacao(Integer numTransformadoresoperacao) {
        this.numTransformadoresoperacao = numTransformadoresoperacao;
    }

    public Integer getNumTransformadoresviolados() {
        return this.numTransformadoresviolados;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity numTransformadoresviolados(
        Integer numTransformadoresviolados
    ) {
        this.setNumTransformadoresviolados(numTransformadoresviolados);
        return this;
    }

    public void setNumTransformadoresviolados(Integer numTransformadoresviolados) {
        this.numTransformadoresviolados = numTransformadoresviolados;
    }

    public Double getValCcat() {
        return this.valCcat;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity valCcat(Double valCcat) {
        this.setValCcat(valCcat);
        return this;
    }

    public void setValCcat(Double valCcat) {
        this.valCcat = valCcat;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity)) {
            return false;
        }
        return (
            getId() != null &&
            getId().equals(((OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity) o).getId())
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
        return "OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity{" +
            "id=" + getId() +
            ", codTipoagregacao='" + getCodTipoagregacao() + "'" +
            ", idPeriodicidade='" + getIdPeriodicidade() + "'" +
            ", nomAgregacao='" + getNomAgregacao() + "'" +
            ", dinReferencia='" + getDinReferencia() + "'" +
            ", numTransformadoresoperacao=" + getNumTransformadoresoperacao() +
            ", numTransformadoresviolados=" + getNumTransformadoresviolados() +
            ", valCcat=" + getValCcat() +
            "}";
    }
}
