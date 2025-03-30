package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity.
 */
@Entity
@Table(name = "OnsTable20")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(
    indexName = "onsdadosindicadoresqualidadeenergiaredebasicadfddesempenhofrequenciaemdisturbiosevento"
)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "din_referencia")
    private LocalDate dinReferencia;

    @Column(name = "din_iniciodesviofreq")
    private LocalDate dinIniciodesviofreq;

    @Column(name = "din_fimdesviofreq")
    private LocalDate dinFimdesviofreq;

    @Column(name = "id_faixafrequencia")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idFaixafrequencia;

    @Column(name = "nom_faixafrequencia")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomFaixafrequencia;

    @Column(name = "val_dfd")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer valDfd;

    @Column(name = "val_freqmaxmin")
    private Double valFreqmaxmin;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDinReferencia() {
        return this.dinReferencia;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity dinReferencia(
        LocalDate dinReferencia
    ) {
        this.setDinReferencia(dinReferencia);
        return this;
    }

    public void setDinReferencia(LocalDate dinReferencia) {
        this.dinReferencia = dinReferencia;
    }

    public LocalDate getDinIniciodesviofreq() {
        return this.dinIniciodesviofreq;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity dinIniciodesviofreq(
        LocalDate dinIniciodesviofreq
    ) {
        this.setDinIniciodesviofreq(dinIniciodesviofreq);
        return this;
    }

    public void setDinIniciodesviofreq(LocalDate dinIniciodesviofreq) {
        this.dinIniciodesviofreq = dinIniciodesviofreq;
    }

    public LocalDate getDinFimdesviofreq() {
        return this.dinFimdesviofreq;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity dinFimdesviofreq(
        LocalDate dinFimdesviofreq
    ) {
        this.setDinFimdesviofreq(dinFimdesviofreq);
        return this;
    }

    public void setDinFimdesviofreq(LocalDate dinFimdesviofreq) {
        this.dinFimdesviofreq = dinFimdesviofreq;
    }

    public String getIdFaixafrequencia() {
        return this.idFaixafrequencia;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity idFaixafrequencia(
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

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity nomFaixafrequencia(
        String nomFaixafrequencia
    ) {
        this.setNomFaixafrequencia(nomFaixafrequencia);
        return this;
    }

    public void setNomFaixafrequencia(String nomFaixafrequencia) {
        this.nomFaixafrequencia = nomFaixafrequencia;
    }

    public Integer getValDfd() {
        return this.valDfd;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity valDfd(Integer valDfd) {
        this.setValDfd(valDfd);
        return this;
    }

    public void setValDfd(Integer valDfd) {
        this.valDfd = valDfd;
    }

    public Double getValFreqmaxmin() {
        return this.valFreqmaxmin;
    }

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity valFreqmaxmin(
        Double valFreqmaxmin
    ) {
        this.setValFreqmaxmin(valFreqmaxmin);
        return this;
    }

    public void setValFreqmaxmin(Double valFreqmaxmin) {
        this.valFreqmaxmin = valFreqmaxmin;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity)) {
            return false;
        }
        return (
            getId() != null &&
            getId().equals(((OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity) o).getId())
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
        return "OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity{" +
            "id=" + getId() +
            ", dinReferencia='" + getDinReferencia() + "'" +
            ", dinIniciodesviofreq='" + getDinIniciodesviofreq() + "'" +
            ", dinFimdesviofreq='" + getDinFimdesviofreq() + "'" +
            ", idFaixafrequencia='" + getIdFaixafrequencia() + "'" +
            ", nomFaixafrequencia='" + getNomFaixafrequencia() + "'" +
            ", valDfd=" + getValDfd() +
            ", valFreqmaxmin=" + getValFreqmaxmin() +
            "}";
    }
}
