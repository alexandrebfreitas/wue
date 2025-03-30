package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsIntercambiosEntreSubsistemasEntity.
 */
@Entity
@Table(name = "OnsTable59")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsintercambiosentresubsistemas")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsIntercambiosEntreSubsistemasEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    @Column(name = "id_subsistema_origem")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idSubsistemaOrigem;

    @Column(name = "nom_subsistema_origem")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubsistemaOrigem;

    @Column(name = "id_subsistema_destino")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idSubsistemaDestino;

    @Column(name = "nom_subsistema_destino")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubsistemaDestino;

    @Column(name = "val_intercambiomwmed")
    private Double valIntercambiomwmed;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsIntercambiosEntreSubsistemasEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsIntercambiosEntreSubsistemasEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public String getIdSubsistemaOrigem() {
        return this.idSubsistemaOrigem;
    }

    public OnsIntercambiosEntreSubsistemasEntity idSubsistemaOrigem(String idSubsistemaOrigem) {
        this.setIdSubsistemaOrigem(idSubsistemaOrigem);
        return this;
    }

    public void setIdSubsistemaOrigem(String idSubsistemaOrigem) {
        this.idSubsistemaOrigem = idSubsistemaOrigem;
    }

    public String getNomSubsistemaOrigem() {
        return this.nomSubsistemaOrigem;
    }

    public OnsIntercambiosEntreSubsistemasEntity nomSubsistemaOrigem(String nomSubsistemaOrigem) {
        this.setNomSubsistemaOrigem(nomSubsistemaOrigem);
        return this;
    }

    public void setNomSubsistemaOrigem(String nomSubsistemaOrigem) {
        this.nomSubsistemaOrigem = nomSubsistemaOrigem;
    }

    public String getIdSubsistemaDestino() {
        return this.idSubsistemaDestino;
    }

    public OnsIntercambiosEntreSubsistemasEntity idSubsistemaDestino(String idSubsistemaDestino) {
        this.setIdSubsistemaDestino(idSubsistemaDestino);
        return this;
    }

    public void setIdSubsistemaDestino(String idSubsistemaDestino) {
        this.idSubsistemaDestino = idSubsistemaDestino;
    }

    public String getNomSubsistemaDestino() {
        return this.nomSubsistemaDestino;
    }

    public OnsIntercambiosEntreSubsistemasEntity nomSubsistemaDestino(String nomSubsistemaDestino) {
        this.setNomSubsistemaDestino(nomSubsistemaDestino);
        return this;
    }

    public void setNomSubsistemaDestino(String nomSubsistemaDestino) {
        this.nomSubsistemaDestino = nomSubsistemaDestino;
    }

    public Double getValIntercambiomwmed() {
        return this.valIntercambiomwmed;
    }

    public OnsIntercambiosEntreSubsistemasEntity valIntercambiomwmed(Double valIntercambiomwmed) {
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
        if (!(o instanceof OnsIntercambiosEntreSubsistemasEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsIntercambiosEntreSubsistemasEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsIntercambiosEntreSubsistemasEntity{" +
            "id=" + getId() +
            ", dinInstante='" + getDinInstante() + "'" +
            ", idSubsistemaOrigem='" + getIdSubsistemaOrigem() + "'" +
            ", nomSubsistemaOrigem='" + getNomSubsistemaOrigem() + "'" +
            ", idSubsistemaDestino='" + getIdSubsistemaDestino() + "'" +
            ", nomSubsistemaDestino='" + getNomSubsistemaDestino() + "'" +
            ", valIntercambiomwmed=" + getValIntercambiomwmed() +
            "}";
    }
}
