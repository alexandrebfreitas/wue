package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.
 */
@Entity
@Table(name = "OnsTable15")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadosindicadorescumprimentoprovidenciasecpaepcpa")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dsc_agregacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String dscAgregacao;

    @Column(name = "dsc_caracteristica")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String dscCaracteristica;

    @Column(name = "din_referencia")
    private LocalDate dinReferencia;

    @Column(name = "num_nprc_concluidas")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numNprcConcluidas;

    @Column(name = "num_nprp_programadas")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numNprpProgramadas;

    @Column(name = "num_nprat_atrasadas")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numNpratAtrasadas;

    @Column(name = "num_npra_antecipadas")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numNpraAntecipadas;

    @Column(name = "num_nprcp_concluidas_prazo")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numNprcpConcluidasPrazo;

    @Column(name = "val_ecpa")
    private Double valEcpa;

    @Column(name = "val_pcpa")
    private Double valPcpa;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDscAgregacao() {
        return this.dscAgregacao;
    }

    public OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity dscAgregacao(String dscAgregacao) {
        this.setDscAgregacao(dscAgregacao);
        return this;
    }

    public void setDscAgregacao(String dscAgregacao) {
        this.dscAgregacao = dscAgregacao;
    }

    public String getDscCaracteristica() {
        return this.dscCaracteristica;
    }

    public OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity dscCaracteristica(String dscCaracteristica) {
        this.setDscCaracteristica(dscCaracteristica);
        return this;
    }

    public void setDscCaracteristica(String dscCaracteristica) {
        this.dscCaracteristica = dscCaracteristica;
    }

    public LocalDate getDinReferencia() {
        return this.dinReferencia;
    }

    public OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity dinReferencia(LocalDate dinReferencia) {
        this.setDinReferencia(dinReferencia);
        return this;
    }

    public void setDinReferencia(LocalDate dinReferencia) {
        this.dinReferencia = dinReferencia;
    }

    public Integer getNumNprcConcluidas() {
        return this.numNprcConcluidas;
    }

    public OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity numNprcConcluidas(Integer numNprcConcluidas) {
        this.setNumNprcConcluidas(numNprcConcluidas);
        return this;
    }

    public void setNumNprcConcluidas(Integer numNprcConcluidas) {
        this.numNprcConcluidas = numNprcConcluidas;
    }

    public Integer getNumNprpProgramadas() {
        return this.numNprpProgramadas;
    }

    public OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity numNprpProgramadas(Integer numNprpProgramadas) {
        this.setNumNprpProgramadas(numNprpProgramadas);
        return this;
    }

    public void setNumNprpProgramadas(Integer numNprpProgramadas) {
        this.numNprpProgramadas = numNprpProgramadas;
    }

    public Integer getNumNpratAtrasadas() {
        return this.numNpratAtrasadas;
    }

    public OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity numNpratAtrasadas(Integer numNpratAtrasadas) {
        this.setNumNpratAtrasadas(numNpratAtrasadas);
        return this;
    }

    public void setNumNpratAtrasadas(Integer numNpratAtrasadas) {
        this.numNpratAtrasadas = numNpratAtrasadas;
    }

    public Integer getNumNpraAntecipadas() {
        return this.numNpraAntecipadas;
    }

    public OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity numNpraAntecipadas(Integer numNpraAntecipadas) {
        this.setNumNpraAntecipadas(numNpraAntecipadas);
        return this;
    }

    public void setNumNpraAntecipadas(Integer numNpraAntecipadas) {
        this.numNpraAntecipadas = numNpraAntecipadas;
    }

    public Integer getNumNprcpConcluidasPrazo() {
        return this.numNprcpConcluidasPrazo;
    }

    public OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity numNprcpConcluidasPrazo(Integer numNprcpConcluidasPrazo) {
        this.setNumNprcpConcluidasPrazo(numNprcpConcluidasPrazo);
        return this;
    }

    public void setNumNprcpConcluidasPrazo(Integer numNprcpConcluidasPrazo) {
        this.numNprcpConcluidasPrazo = numNprcpConcluidasPrazo;
    }

    public Double getValEcpa() {
        return this.valEcpa;
    }

    public OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity valEcpa(Double valEcpa) {
        this.setValEcpa(valEcpa);
        return this;
    }

    public void setValEcpa(Double valEcpa) {
        this.valEcpa = valEcpa;
    }

    public Double getValPcpa() {
        return this.valPcpa;
    }

    public OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity valPcpa(Double valPcpa) {
        this.setValPcpa(valPcpa);
        return this;
    }

    public void setValPcpa(Double valPcpa) {
        this.valPcpa = valPcpa;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity{" +
            "id=" + getId() +
            ", dscAgregacao='" + getDscAgregacao() + "'" +
            ", dscCaracteristica='" + getDscCaracteristica() + "'" +
            ", dinReferencia='" + getDinReferencia() + "'" +
            ", numNprcConcluidas=" + getNumNprcConcluidas() +
            ", numNprpProgramadas=" + getNumNprpProgramadas() +
            ", numNpratAtrasadas=" + getNumNpratAtrasadas() +
            ", numNpraAntecipadas=" + getNumNpraAntecipadas() +
            ", numNprcpConcluidasPrazo=" + getNumNprcpConcluidasPrazo() +
            ", valEcpa=" + getValEcpa() +
            ", valPcpa=" + getValPcpa() +
            "}";
    }
}
