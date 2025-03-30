package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.
 */
@Entity
@Table(name = "OnsTable8")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(
    indexName = "onsdadosindicadoresqualidadeenergiaredebasicadfpdesempenhofrequenciaemregimepermanente"
)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_periodicidade")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idPeriodicidade;

    @Column(name = "din_referencia")
    private LocalDate dinReferencia;

    @Column(name = "num_desvio_perm_sobre")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numDesvioPermSobre;

    @Column(name = "num_desvio_perm_sub")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numDesvioPermSub;

    @Column(name = "num_desvio_dist_sobre")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numDesvioDistSobre;

    @Column(name = "num_desvio_dist_sub")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numDesvioDistSub;

    @Column(name = "num_minutos")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numMinutos;

    @Column(name = "num_violadodis")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numVioladodis;

    @Column(name = "num_violadoperm")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numVioladoperm;

    @Column(name = "val_dfp")
    private Double valDfp;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdPeriodicidade() {
        return this.idPeriodicidade;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity idPeriodicidade(
        String idPeriodicidade
    ) {
        this.setIdPeriodicidade(idPeriodicidade);
        return this;
    }

    public void setIdPeriodicidade(String idPeriodicidade) {
        this.idPeriodicidade = idPeriodicidade;
    }

    public LocalDate getDinReferencia() {
        return this.dinReferencia;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity dinReferencia(
        LocalDate dinReferencia
    ) {
        this.setDinReferencia(dinReferencia);
        return this;
    }

    public void setDinReferencia(LocalDate dinReferencia) {
        this.dinReferencia = dinReferencia;
    }

    public Integer getNumDesvioPermSobre() {
        return this.numDesvioPermSobre;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity numDesvioPermSobre(
        Integer numDesvioPermSobre
    ) {
        this.setNumDesvioPermSobre(numDesvioPermSobre);
        return this;
    }

    public void setNumDesvioPermSobre(Integer numDesvioPermSobre) {
        this.numDesvioPermSobre = numDesvioPermSobre;
    }

    public Integer getNumDesvioPermSub() {
        return this.numDesvioPermSub;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity numDesvioPermSub(
        Integer numDesvioPermSub
    ) {
        this.setNumDesvioPermSub(numDesvioPermSub);
        return this;
    }

    public void setNumDesvioPermSub(Integer numDesvioPermSub) {
        this.numDesvioPermSub = numDesvioPermSub;
    }

    public Integer getNumDesvioDistSobre() {
        return this.numDesvioDistSobre;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity numDesvioDistSobre(
        Integer numDesvioDistSobre
    ) {
        this.setNumDesvioDistSobre(numDesvioDistSobre);
        return this;
    }

    public void setNumDesvioDistSobre(Integer numDesvioDistSobre) {
        this.numDesvioDistSobre = numDesvioDistSobre;
    }

    public Integer getNumDesvioDistSub() {
        return this.numDesvioDistSub;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity numDesvioDistSub(
        Integer numDesvioDistSub
    ) {
        this.setNumDesvioDistSub(numDesvioDistSub);
        return this;
    }

    public void setNumDesvioDistSub(Integer numDesvioDistSub) {
        this.numDesvioDistSub = numDesvioDistSub;
    }

    public Integer getNumMinutos() {
        return this.numMinutos;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity numMinutos(Integer numMinutos) {
        this.setNumMinutos(numMinutos);
        return this;
    }

    public void setNumMinutos(Integer numMinutos) {
        this.numMinutos = numMinutos;
    }

    public Integer getNumVioladodis() {
        return this.numVioladodis;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity numVioladodis(
        Integer numVioladodis
    ) {
        this.setNumVioladodis(numVioladodis);
        return this;
    }

    public void setNumVioladodis(Integer numVioladodis) {
        this.numVioladodis = numVioladodis;
    }

    public Integer getNumVioladoperm() {
        return this.numVioladoperm;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity numVioladoperm(
        Integer numVioladoperm
    ) {
        this.setNumVioladoperm(numVioladoperm);
        return this;
    }

    public void setNumVioladoperm(Integer numVioladoperm) {
        this.numVioladoperm = numVioladoperm;
    }

    public Double getValDfp() {
        return this.valDfp;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity valDfp(Double valDfp) {
        this.setValDfp(valDfp);
        return this;
    }

    public void setValDfp(Double valDfp) {
        this.valDfp = valDfp;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity)) {
            return false;
        }
        return (
            getId() != null &&
            getId().equals(((OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity) o).getId())
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
        return "OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity{" +
            "id=" + getId() +
            ", idPeriodicidade='" + getIdPeriodicidade() + "'" +
            ", dinReferencia='" + getDinReferencia() + "'" +
            ", numDesvioPermSobre=" + getNumDesvioPermSobre() +
            ", numDesvioPermSub=" + getNumDesvioPermSub() +
            ", numDesvioDistSobre=" + getNumDesvioDistSobre() +
            ", numDesvioDistSub=" + getNumDesvioDistSub() +
            ", numMinutos=" + getNumMinutos() +
            ", numVioladodis=" + getNumVioladodis() +
            ", numVioladoperm=" + getNumVioladoperm() +
            ", valDfp=" + getValDfp() +
            "}";
    }
}
