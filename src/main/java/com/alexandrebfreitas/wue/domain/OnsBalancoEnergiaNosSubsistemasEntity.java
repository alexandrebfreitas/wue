package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsBalancoEnergiaNosSubsistemasEntity.
 */
@Entity
@Table(name = "OnsTable22")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsbalancoenergianossubsistemas")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsBalancoEnergiaNosSubsistemasEntity implements Serializable {

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

    @Column(name = "val_gerhidraulica")
    private Double valGerhidraulica;

    @Column(name = "val_gertermica")
    private Double valGertermica;

    @Column(name = "val_gereolica")
    private Double valGereolica;

    @Column(name = "val_gersolar")
    private Double valGersolar;

    @Column(name = "val_carga")
    private Double valCarga;

    @Column(name = "val_intercambio")
    private Double valIntercambio;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsBalancoEnergiaNosSubsistemasEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsBalancoEnergiaNosSubsistemasEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsBalancoEnergiaNosSubsistemasEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsBalancoEnergiaNosSubsistemasEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public Double getValGerhidraulica() {
        return this.valGerhidraulica;
    }

    public OnsBalancoEnergiaNosSubsistemasEntity valGerhidraulica(Double valGerhidraulica) {
        this.setValGerhidraulica(valGerhidraulica);
        return this;
    }

    public void setValGerhidraulica(Double valGerhidraulica) {
        this.valGerhidraulica = valGerhidraulica;
    }

    public Double getValGertermica() {
        return this.valGertermica;
    }

    public OnsBalancoEnergiaNosSubsistemasEntity valGertermica(Double valGertermica) {
        this.setValGertermica(valGertermica);
        return this;
    }

    public void setValGertermica(Double valGertermica) {
        this.valGertermica = valGertermica;
    }

    public Double getValGereolica() {
        return this.valGereolica;
    }

    public OnsBalancoEnergiaNosSubsistemasEntity valGereolica(Double valGereolica) {
        this.setValGereolica(valGereolica);
        return this;
    }

    public void setValGereolica(Double valGereolica) {
        this.valGereolica = valGereolica;
    }

    public Double getValGersolar() {
        return this.valGersolar;
    }

    public OnsBalancoEnergiaNosSubsistemasEntity valGersolar(Double valGersolar) {
        this.setValGersolar(valGersolar);
        return this;
    }

    public void setValGersolar(Double valGersolar) {
        this.valGersolar = valGersolar;
    }

    public Double getValCarga() {
        return this.valCarga;
    }

    public OnsBalancoEnergiaNosSubsistemasEntity valCarga(Double valCarga) {
        this.setValCarga(valCarga);
        return this;
    }

    public void setValCarga(Double valCarga) {
        this.valCarga = valCarga;
    }

    public Double getValIntercambio() {
        return this.valIntercambio;
    }

    public OnsBalancoEnergiaNosSubsistemasEntity valIntercambio(Double valIntercambio) {
        this.setValIntercambio(valIntercambio);
        return this;
    }

    public void setValIntercambio(Double valIntercambio) {
        this.valIntercambio = valIntercambio;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsBalancoEnergiaNosSubsistemasEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsBalancoEnergiaNosSubsistemasEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsBalancoEnergiaNosSubsistemasEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", dinInstante='" + getDinInstante() + "'" +
            ", valGerhidraulica=" + getValGerhidraulica() +
            ", valGertermica=" + getValGertermica() +
            ", valGereolica=" + getValGereolica() +
            ", valGersolar=" + getValGersolar() +
            ", valCarga=" + getValCarga() +
            ", valIntercambio=" + getValIntercambio() +
            "}";
    }
}
