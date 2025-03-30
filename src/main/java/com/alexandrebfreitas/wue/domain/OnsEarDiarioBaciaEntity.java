package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsEarDiarioBaciaEntity.
 */
@Entity
@Table(name = "OnsTable56")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onseardiariobacia")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsEarDiarioBaciaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom_curto")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomCurto;

    @Column(name = "ear_data")
    private LocalDate earData;

    @Column(name = "ear_max_bacia")
    private Double earMaxBacia;

    @Column(name = "ear_verif_bacia_mwmes")
    private Double earVerifBaciaMwmes;

    @Column(name = "ear_verif_bacia_percentual")
    private Double earVerifBaciaPercentual;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsEarDiarioBaciaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCurto() {
        return this.nomCurto;
    }

    public OnsEarDiarioBaciaEntity nomCurto(String nomCurto) {
        this.setNomCurto(nomCurto);
        return this;
    }

    public void setNomCurto(String nomCurto) {
        this.nomCurto = nomCurto;
    }

    public LocalDate getEarData() {
        return this.earData;
    }

    public OnsEarDiarioBaciaEntity earData(LocalDate earData) {
        this.setEarData(earData);
        return this;
    }

    public void setEarData(LocalDate earData) {
        this.earData = earData;
    }

    public Double getEarMaxBacia() {
        return this.earMaxBacia;
    }

    public OnsEarDiarioBaciaEntity earMaxBacia(Double earMaxBacia) {
        this.setEarMaxBacia(earMaxBacia);
        return this;
    }

    public void setEarMaxBacia(Double earMaxBacia) {
        this.earMaxBacia = earMaxBacia;
    }

    public Double getEarVerifBaciaMwmes() {
        return this.earVerifBaciaMwmes;
    }

    public OnsEarDiarioBaciaEntity earVerifBaciaMwmes(Double earVerifBaciaMwmes) {
        this.setEarVerifBaciaMwmes(earVerifBaciaMwmes);
        return this;
    }

    public void setEarVerifBaciaMwmes(Double earVerifBaciaMwmes) {
        this.earVerifBaciaMwmes = earVerifBaciaMwmes;
    }

    public Double getEarVerifBaciaPercentual() {
        return this.earVerifBaciaPercentual;
    }

    public OnsEarDiarioBaciaEntity earVerifBaciaPercentual(Double earVerifBaciaPercentual) {
        this.setEarVerifBaciaPercentual(earVerifBaciaPercentual);
        return this;
    }

    public void setEarVerifBaciaPercentual(Double earVerifBaciaPercentual) {
        this.earVerifBaciaPercentual = earVerifBaciaPercentual;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsEarDiarioBaciaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsEarDiarioBaciaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsEarDiarioBaciaEntity{" +
            "id=" + getId() +
            ", nomCurto='" + getNomCurto() + "'" +
            ", earData='" + getEarData() + "'" +
            ", earMaxBacia=" + getEarMaxBacia() +
            ", earVerifBaciaMwmes=" + getEarVerifBaciaMwmes() +
            ", earVerifBaciaPercentual=" + getEarVerifBaciaPercentual() +
            "}";
    }
}
