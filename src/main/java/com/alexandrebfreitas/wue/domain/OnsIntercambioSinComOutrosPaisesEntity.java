package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsIntercambioSinComOutrosPaisesEntity.
 */
@Entity
@Table(name = "OnsTable54")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsintercambiosincomoutrospaises")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsIntercambioSinComOutrosPaisesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    @Column(name = "nom_paisdestino")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomPaisdestino;

    @Column(name = "val_intercambiomwmed")
    private Double valIntercambiomwmed;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsIntercambioSinComOutrosPaisesEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsIntercambioSinComOutrosPaisesEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public String getNomPaisdestino() {
        return this.nomPaisdestino;
    }

    public OnsIntercambioSinComOutrosPaisesEntity nomPaisdestino(String nomPaisdestino) {
        this.setNomPaisdestino(nomPaisdestino);
        return this;
    }

    public void setNomPaisdestino(String nomPaisdestino) {
        this.nomPaisdestino = nomPaisdestino;
    }

    public Double getValIntercambiomwmed() {
        return this.valIntercambiomwmed;
    }

    public OnsIntercambioSinComOutrosPaisesEntity valIntercambiomwmed(Double valIntercambiomwmed) {
        this.setValIntercambiomwmed(valIntercambiomwmed);
        return this;
    }

    public void setValIntercambiomwmed(Double valIntercambiomwmed) {
        this.valIntercambiomwmed = valIntercambiomwmed;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsIntercambioSinComOutrosPaisesEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsIntercambioSinComOutrosPaisesEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsIntercambioSinComOutrosPaisesEntity{" +
            "id=" + getId() +
            ", dinInstante='" + getDinInstante() + "'" +
            ", nomPaisdestino='" + getNomPaisdestino() + "'" +
            ", valIntercambiomwmed=" + getValIntercambiomwmed() +
            "}";
    }
}
