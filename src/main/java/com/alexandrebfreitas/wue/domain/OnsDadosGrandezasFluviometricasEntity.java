package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosGrandezasFluviometricasEntity.
 */
@Entity
@Table(name = "OnsTable58")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadosgrandezasfluviometricas")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosGrandezasFluviometricasEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_postofluv")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idPostofluv;

    @Column(name = "nom_postofluviometrico")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomPostofluviometrico;

    @Column(name = "val_latitude")
    private Double valLatitude;

    @Column(name = "val_longitude")
    private Double valLongitude;

    @Column(name = "nom_rio")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomRio;

    @Column(name = "nom_bacia")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomBacia;

    @Column(name = "din_medicao")
    private LocalDate dinMedicao;

    @Column(name = "val_vazaomedia")
    private Double valVazaomedia;

    @Column(name = "val_vazaomediaincr")
    private Double valVazaomediaincr;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosGrandezasFluviometricasEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdPostofluv() {
        return this.idPostofluv;
    }

    public OnsDadosGrandezasFluviometricasEntity idPostofluv(String idPostofluv) {
        this.setIdPostofluv(idPostofluv);
        return this;
    }

    public void setIdPostofluv(String idPostofluv) {
        this.idPostofluv = idPostofluv;
    }

    public String getNomPostofluviometrico() {
        return this.nomPostofluviometrico;
    }

    public OnsDadosGrandezasFluviometricasEntity nomPostofluviometrico(String nomPostofluviometrico) {
        this.setNomPostofluviometrico(nomPostofluviometrico);
        return this;
    }

    public void setNomPostofluviometrico(String nomPostofluviometrico) {
        this.nomPostofluviometrico = nomPostofluviometrico;
    }

    public Double getValLatitude() {
        return this.valLatitude;
    }

    public OnsDadosGrandezasFluviometricasEntity valLatitude(Double valLatitude) {
        this.setValLatitude(valLatitude);
        return this;
    }

    public void setValLatitude(Double valLatitude) {
        this.valLatitude = valLatitude;
    }

    public Double getValLongitude() {
        return this.valLongitude;
    }

    public OnsDadosGrandezasFluviometricasEntity valLongitude(Double valLongitude) {
        this.setValLongitude(valLongitude);
        return this;
    }

    public void setValLongitude(Double valLongitude) {
        this.valLongitude = valLongitude;
    }

    public String getNomRio() {
        return this.nomRio;
    }

    public OnsDadosGrandezasFluviometricasEntity nomRio(String nomRio) {
        this.setNomRio(nomRio);
        return this;
    }

    public void setNomRio(String nomRio) {
        this.nomRio = nomRio;
    }

    public String getNomBacia() {
        return this.nomBacia;
    }

    public OnsDadosGrandezasFluviometricasEntity nomBacia(String nomBacia) {
        this.setNomBacia(nomBacia);
        return this;
    }

    public void setNomBacia(String nomBacia) {
        this.nomBacia = nomBacia;
    }

    public LocalDate getDinMedicao() {
        return this.dinMedicao;
    }

    public OnsDadosGrandezasFluviometricasEntity dinMedicao(LocalDate dinMedicao) {
        this.setDinMedicao(dinMedicao);
        return this;
    }

    public void setDinMedicao(LocalDate dinMedicao) {
        this.dinMedicao = dinMedicao;
    }

    public Double getValVazaomedia() {
        return this.valVazaomedia;
    }

    public OnsDadosGrandezasFluviometricasEntity valVazaomedia(Double valVazaomedia) {
        this.setValVazaomedia(valVazaomedia);
        return this;
    }

    public void setValVazaomedia(Double valVazaomedia) {
        this.valVazaomedia = valVazaomedia;
    }

    public Double getValVazaomediaincr() {
        return this.valVazaomediaincr;
    }

    public OnsDadosGrandezasFluviometricasEntity valVazaomediaincr(Double valVazaomediaincr) {
        this.setValVazaomediaincr(valVazaomediaincr);
        return this;
    }

    public void setValVazaomediaincr(Double valVazaomediaincr) {
        this.valVazaomediaincr = valVazaomediaincr;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosGrandezasFluviometricasEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosGrandezasFluviometricasEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosGrandezasFluviometricasEntity{" +
            "id=" + getId() +
            ", idPostofluv='" + getIdPostofluv() + "'" +
            ", nomPostofluviometrico='" + getNomPostofluviometrico() + "'" +
            ", valLatitude=" + getValLatitude() +
            ", valLongitude=" + getValLongitude() +
            ", nomRio='" + getNomRio() + "'" +
            ", nomBacia='" + getNomBacia() + "'" +
            ", dinMedicao='" + getDinMedicao() + "'" +
            ", valVazaomedia=" + getValVazaomedia() +
            ", valVazaomediaincr=" + getValVazaomediaincr() +
            "}";
    }
}
