package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsEnaDiarioReservatorioEntity.
 */
@Entity
@Table(name = "OnsTable2")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsenadiarioreservatorio")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsEnaDiarioReservatorioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ena_bruta_res_mwmed")
    private Double enaBrutaResMwmed;

    @Column(name = "ena_bruta_res_percentualmlt")
    private Double enaBrutaResPercentualmlt;

    @Column(name = "ena_armazenavel_res_mwmed")
    private Double enaArmazenavelResMwmed;

    @Column(name = "ena_armazenavel_res_percentualmlt")
    private Double enaArmazenavelResPercentualmlt;

    @Column(name = "ena_queda_bruta")
    private Double enaQuedaBruta;

    @Column(name = "mlt_ena")
    private Double mltEna;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsEnaDiarioReservatorioEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getEnaBrutaResMwmed() {
        return this.enaBrutaResMwmed;
    }

    public OnsEnaDiarioReservatorioEntity enaBrutaResMwmed(Double enaBrutaResMwmed) {
        this.setEnaBrutaResMwmed(enaBrutaResMwmed);
        return this;
    }

    public void setEnaBrutaResMwmed(Double enaBrutaResMwmed) {
        this.enaBrutaResMwmed = enaBrutaResMwmed;
    }

    public Double getEnaBrutaResPercentualmlt() {
        return this.enaBrutaResPercentualmlt;
    }

    public OnsEnaDiarioReservatorioEntity enaBrutaResPercentualmlt(Double enaBrutaResPercentualmlt) {
        this.setEnaBrutaResPercentualmlt(enaBrutaResPercentualmlt);
        return this;
    }

    public void setEnaBrutaResPercentualmlt(Double enaBrutaResPercentualmlt) {
        this.enaBrutaResPercentualmlt = enaBrutaResPercentualmlt;
    }

    public Double getEnaArmazenavelResMwmed() {
        return this.enaArmazenavelResMwmed;
    }

    public OnsEnaDiarioReservatorioEntity enaArmazenavelResMwmed(Double enaArmazenavelResMwmed) {
        this.setEnaArmazenavelResMwmed(enaArmazenavelResMwmed);
        return this;
    }

    public void setEnaArmazenavelResMwmed(Double enaArmazenavelResMwmed) {
        this.enaArmazenavelResMwmed = enaArmazenavelResMwmed;
    }

    public Double getEnaArmazenavelResPercentualmlt() {
        return this.enaArmazenavelResPercentualmlt;
    }

    public OnsEnaDiarioReservatorioEntity enaArmazenavelResPercentualmlt(Double enaArmazenavelResPercentualmlt) {
        this.setEnaArmazenavelResPercentualmlt(enaArmazenavelResPercentualmlt);
        return this;
    }

    public void setEnaArmazenavelResPercentualmlt(Double enaArmazenavelResPercentualmlt) {
        this.enaArmazenavelResPercentualmlt = enaArmazenavelResPercentualmlt;
    }

    public Double getEnaQuedaBruta() {
        return this.enaQuedaBruta;
    }

    public OnsEnaDiarioReservatorioEntity enaQuedaBruta(Double enaQuedaBruta) {
        this.setEnaQuedaBruta(enaQuedaBruta);
        return this;
    }

    public void setEnaQuedaBruta(Double enaQuedaBruta) {
        this.enaQuedaBruta = enaQuedaBruta;
    }

    public Double getMltEna() {
        return this.mltEna;
    }

    public OnsEnaDiarioReservatorioEntity mltEna(Double mltEna) {
        this.setMltEna(mltEna);
        return this;
    }

    public void setMltEna(Double mltEna) {
        this.mltEna = mltEna;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsEnaDiarioReservatorioEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsEnaDiarioReservatorioEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsEnaDiarioReservatorioEntity{" +
            "id=" + getId() +
            ", enaBrutaResMwmed=" + getEnaBrutaResMwmed() +
            ", enaBrutaResPercentualmlt=" + getEnaBrutaResPercentualmlt() +
            ", enaArmazenavelResMwmed=" + getEnaArmazenavelResMwmed() +
            ", enaArmazenavelResPercentualmlt=" + getEnaArmazenavelResPercentualmlt() +
            ", enaQuedaBruta=" + getEnaQuedaBruta() +
            ", mltEna=" + getMltEna() +
            "}";
    }
}
