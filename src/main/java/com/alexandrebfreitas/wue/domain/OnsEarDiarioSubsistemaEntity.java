package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsEarDiarioSubsistemaEntity.
 */
@Entity
@Table(name = "OnsTable31")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onseardiariosubsistema")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsEarDiarioSubsistemaEntity implements Serializable {

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

    @Column(name = "ear_data")
    private LocalDate earData;

    @Column(name = "ear_max_subsistema")
    private Double earMaxSubsistema;

    @Column(name = "ear_verif_subsistema_mwmes")
    private Double earVerifSubsistemaMwmes;

    @Column(name = "ear_verif_subsistema_percentual")
    private Double earVerifSubsistemaPercentual;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsEarDiarioSubsistemaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsEarDiarioSubsistemaEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsEarDiarioSubsistemaEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public LocalDate getEarData() {
        return this.earData;
    }

    public OnsEarDiarioSubsistemaEntity earData(LocalDate earData) {
        this.setEarData(earData);
        return this;
    }

    public void setEarData(LocalDate earData) {
        this.earData = earData;
    }

    public Double getEarMaxSubsistema() {
        return this.earMaxSubsistema;
    }

    public OnsEarDiarioSubsistemaEntity earMaxSubsistema(Double earMaxSubsistema) {
        this.setEarMaxSubsistema(earMaxSubsistema);
        return this;
    }

    public void setEarMaxSubsistema(Double earMaxSubsistema) {
        this.earMaxSubsistema = earMaxSubsistema;
    }

    public Double getEarVerifSubsistemaMwmes() {
        return this.earVerifSubsistemaMwmes;
    }

    public OnsEarDiarioSubsistemaEntity earVerifSubsistemaMwmes(Double earVerifSubsistemaMwmes) {
        this.setEarVerifSubsistemaMwmes(earVerifSubsistemaMwmes);
        return this;
    }

    public void setEarVerifSubsistemaMwmes(Double earVerifSubsistemaMwmes) {
        this.earVerifSubsistemaMwmes = earVerifSubsistemaMwmes;
    }

    public Double getEarVerifSubsistemaPercentual() {
        return this.earVerifSubsistemaPercentual;
    }

    public OnsEarDiarioSubsistemaEntity earVerifSubsistemaPercentual(Double earVerifSubsistemaPercentual) {
        this.setEarVerifSubsistemaPercentual(earVerifSubsistemaPercentual);
        return this;
    }

    public void setEarVerifSubsistemaPercentual(Double earVerifSubsistemaPercentual) {
        this.earVerifSubsistemaPercentual = earVerifSubsistemaPercentual;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsEarDiarioSubsistemaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsEarDiarioSubsistemaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsEarDiarioSubsistemaEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", earData='" + getEarData() + "'" +
            ", earMaxSubsistema=" + getEarMaxSubsistema() +
            ", earVerifSubsistemaMwmes=" + getEarVerifSubsistemaMwmes() +
            ", earVerifSubsistemaPercentual=" + getEarVerifSubsistemaPercentual() +
            "}";
    }
}
