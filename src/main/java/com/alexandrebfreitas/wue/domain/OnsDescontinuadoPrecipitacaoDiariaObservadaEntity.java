package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDescontinuadoPrecipitacaoDiariaObservadaEntity.
 */
@Entity
@Table(name = "OnsTable63")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdescontinuadoprecipitacaodiariaobservada")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDescontinuadoPrecipitacaoDiariaObservadaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cod_estacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codEstacao;

    @Column(name = "val_latitude")
    private Double valLatitude;

    @Column(name = "val_longitude")
    private Double valLongitude;

    @Column(name = "val_medida")
    private Double valMedida;

    @Column(name = "dat_observada")
    private LocalDate datObservada;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDescontinuadoPrecipitacaoDiariaObservadaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodEstacao() {
        return this.codEstacao;
    }

    public OnsDescontinuadoPrecipitacaoDiariaObservadaEntity codEstacao(String codEstacao) {
        this.setCodEstacao(codEstacao);
        return this;
    }

    public void setCodEstacao(String codEstacao) {
        this.codEstacao = codEstacao;
    }

    public Double getValLatitude() {
        return this.valLatitude;
    }

    public OnsDescontinuadoPrecipitacaoDiariaObservadaEntity valLatitude(Double valLatitude) {
        this.setValLatitude(valLatitude);
        return this;
    }

    public void setValLatitude(Double valLatitude) {
        this.valLatitude = valLatitude;
    }

    public Double getValLongitude() {
        return this.valLongitude;
    }

    public OnsDescontinuadoPrecipitacaoDiariaObservadaEntity valLongitude(Double valLongitude) {
        this.setValLongitude(valLongitude);
        return this;
    }

    public void setValLongitude(Double valLongitude) {
        this.valLongitude = valLongitude;
    }

    public Double getValMedida() {
        return this.valMedida;
    }

    public OnsDescontinuadoPrecipitacaoDiariaObservadaEntity valMedida(Double valMedida) {
        this.setValMedida(valMedida);
        return this;
    }

    public void setValMedida(Double valMedida) {
        this.valMedida = valMedida;
    }

    public LocalDate getDatObservada() {
        return this.datObservada;
    }

    public OnsDescontinuadoPrecipitacaoDiariaObservadaEntity datObservada(LocalDate datObservada) {
        this.setDatObservada(datObservada);
        return this;
    }

    public void setDatObservada(LocalDate datObservada) {
        this.datObservada = datObservada;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDescontinuadoPrecipitacaoDiariaObservadaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDescontinuadoPrecipitacaoDiariaObservadaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDescontinuadoPrecipitacaoDiariaObservadaEntity{" +
            "id=" + getId() +
            ", codEstacao='" + getCodEstacao() + "'" +
            ", valLatitude=" + getValLatitude() +
            ", valLongitude=" + getValLongitude() +
            ", valMedida=" + getValMedida() +
            ", datObservada='" + getDatObservada() + "'" +
            "}";
    }
}
