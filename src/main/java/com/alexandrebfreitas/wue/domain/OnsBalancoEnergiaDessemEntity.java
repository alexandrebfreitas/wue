package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsBalancoEnergiaDessemEntity.
 */
@Entity
@Table(name = "OnsTable34")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsbalancoenergiadessem")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsBalancoEnergiaDessemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_subsistema")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idSubsistema;

    @Column(name = "nom_subsistema")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubsistema;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    @Column(name = "val_demanda")
    private Double valDemanda;

    @Column(name = "val_geracaohidraulicamwmed")
    private Double valGeracaohidraulicamwmed;

    @Column(name = "val_geracaopchmwmed")
    private Double valGeracaopchmwmed;

    @Column(name = "val_geracaotermicamwed")
    private Double valGeracaotermicamwed;

    @Column(name = "val_geracaopctmwmed")
    private Double valGeracaopctmwmed;

    @Column(name = "val_geracaoeolicamwmed")
    private Double valGeracaoeolicamwmed;

    @Column(name = "val_geracaofotovoltaicamwmed")
    private Double valGeracaofotovoltaicamwmed;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsBalancoEnergiaDessemEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsBalancoEnergiaDessemEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsBalancoEnergiaDessemEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsBalancoEnergiaDessemEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public Double getValDemanda() {
        return this.valDemanda;
    }

    public OnsBalancoEnergiaDessemEntity valDemanda(Double valDemanda) {
        this.setValDemanda(valDemanda);
        return this;
    }

    public void setValDemanda(Double valDemanda) {
        this.valDemanda = valDemanda;
    }

    public Double getValGeracaohidraulicamwmed() {
        return this.valGeracaohidraulicamwmed;
    }

    public OnsBalancoEnergiaDessemEntity valGeracaohidraulicamwmed(Double valGeracaohidraulicamwmed) {
        this.setValGeracaohidraulicamwmed(valGeracaohidraulicamwmed);
        return this;
    }

    public void setValGeracaohidraulicamwmed(Double valGeracaohidraulicamwmed) {
        this.valGeracaohidraulicamwmed = valGeracaohidraulicamwmed;
    }

    public Double getValGeracaopchmwmed() {
        return this.valGeracaopchmwmed;
    }

    public OnsBalancoEnergiaDessemEntity valGeracaopchmwmed(Double valGeracaopchmwmed) {
        this.setValGeracaopchmwmed(valGeracaopchmwmed);
        return this;
    }

    public void setValGeracaopchmwmed(Double valGeracaopchmwmed) {
        this.valGeracaopchmwmed = valGeracaopchmwmed;
    }

    public Double getValGeracaotermicamwed() {
        return this.valGeracaotermicamwed;
    }

    public OnsBalancoEnergiaDessemEntity valGeracaotermicamwed(Double valGeracaotermicamwed) {
        this.setValGeracaotermicamwed(valGeracaotermicamwed);
        return this;
    }

    public void setValGeracaotermicamwed(Double valGeracaotermicamwed) {
        this.valGeracaotermicamwed = valGeracaotermicamwed;
    }

    public Double getValGeracaopctmwmed() {
        return this.valGeracaopctmwmed;
    }

    public OnsBalancoEnergiaDessemEntity valGeracaopctmwmed(Double valGeracaopctmwmed) {
        this.setValGeracaopctmwmed(valGeracaopctmwmed);
        return this;
    }

    public void setValGeracaopctmwmed(Double valGeracaopctmwmed) {
        this.valGeracaopctmwmed = valGeracaopctmwmed;
    }

    public Double getValGeracaoeolicamwmed() {
        return this.valGeracaoeolicamwmed;
    }

    public OnsBalancoEnergiaDessemEntity valGeracaoeolicamwmed(Double valGeracaoeolicamwmed) {
        this.setValGeracaoeolicamwmed(valGeracaoeolicamwmed);
        return this;
    }

    public void setValGeracaoeolicamwmed(Double valGeracaoeolicamwmed) {
        this.valGeracaoeolicamwmed = valGeracaoeolicamwmed;
    }

    public Double getValGeracaofotovoltaicamwmed() {
        return this.valGeracaofotovoltaicamwmed;
    }

    public OnsBalancoEnergiaDessemEntity valGeracaofotovoltaicamwmed(Double valGeracaofotovoltaicamwmed) {
        this.setValGeracaofotovoltaicamwmed(valGeracaofotovoltaicamwmed);
        return this;
    }

    public void setValGeracaofotovoltaicamwmed(Double valGeracaofotovoltaicamwmed) {
        this.valGeracaofotovoltaicamwmed = valGeracaofotovoltaicamwmed;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsBalancoEnergiaDessemEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsBalancoEnergiaDessemEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsBalancoEnergiaDessemEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", dinInstante='" + getDinInstante() + "'" +
            ", valDemanda=" + getValDemanda() +
            ", valGeracaohidraulicamwmed=" + getValGeracaohidraulicamwmed() +
            ", valGeracaopchmwmed=" + getValGeracaopchmwmed() +
            ", valGeracaotermicamwed=" + getValGeracaotermicamwed() +
            ", valGeracaopctmwmed=" + getValGeracaopctmwmed() +
            ", valGeracaoeolicamwmed=" + getValGeracaoeolicamwmed() +
            ", valGeracaofotovoltaicamwmed=" + getValGeracaofotovoltaicamwmed() +
            "}";
    }
}
