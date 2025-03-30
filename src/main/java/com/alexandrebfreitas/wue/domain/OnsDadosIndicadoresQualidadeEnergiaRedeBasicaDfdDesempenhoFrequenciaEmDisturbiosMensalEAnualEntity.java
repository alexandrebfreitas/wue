package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualEntity.
 */
@Entity
@Table(name = "OnsTable48")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(
    indexName = "onsdadosindicadoresqualidadeenergiaredebasicadfddesempenhofrequenciaemdisturbiosmensaleanual"
)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualEntity implements Serializable {

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

    @Column(name = "id_faixafrequencia")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idFaixafrequencia;

    @Column(name = "nom_faixafrequencia")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomFaixafrequencia;

    @Column(name = "val_dfd")
    private Double valDfd;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdPeriodicidade() {
        return this.idPeriodicidade;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualEntity idPeriodicidade(
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

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualEntity dinReferencia(
        LocalDate dinReferencia
    ) {
        this.setDinReferencia(dinReferencia);
        return this;
    }

    public void setDinReferencia(LocalDate dinReferencia) {
        this.dinReferencia = dinReferencia;
    }

    public String getIdFaixafrequencia() {
        return this.idFaixafrequencia;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualEntity idFaixafrequencia(
        String idFaixafrequencia
    ) {
        this.setIdFaixafrequencia(idFaixafrequencia);
        return this;
    }

    public void setIdFaixafrequencia(String idFaixafrequencia) {
        this.idFaixafrequencia = idFaixafrequencia;
    }

    public String getNomFaixafrequencia() {
        return this.nomFaixafrequencia;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualEntity nomFaixafrequencia(
        String nomFaixafrequencia
    ) {
        this.setNomFaixafrequencia(nomFaixafrequencia);
        return this;
    }

    public void setNomFaixafrequencia(String nomFaixafrequencia) {
        this.nomFaixafrequencia = nomFaixafrequencia;
    }

    public Double getValDfd() {
        return this.valDfd;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualEntity valDfd(Double valDfd) {
        this.setValDfd(valDfd);
        return this;
    }

    public void setValDfd(Double valDfd) {
        this.valDfd = valDfd;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualEntity)) {
            return false;
        }
        return (
            getId() != null &&
            getId().equals(((OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualEntity) o).getId())
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
        return "OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualEntity{" +
            "id=" + getId() +
            ", idPeriodicidade='" + getIdPeriodicidade() + "'" +
            ", dinReferencia='" + getDinReferencia() + "'" +
            ", idFaixafrequencia='" + getIdFaixafrequencia() + "'" +
            ", nomFaixafrequencia='" + getNomFaixafrequencia() + "'" +
            ", valDfd=" + getValDfd() +
            "}";
    }
}
