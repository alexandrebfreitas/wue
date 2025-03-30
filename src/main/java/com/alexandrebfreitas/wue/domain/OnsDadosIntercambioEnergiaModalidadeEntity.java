package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosIntercambioEnergiaModalidadeEntity.
 */
@Entity
@Table(name = "OnsTable42")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadosintercambioenergiamodalidade")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosIntercambioEnergiaModalidadeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom_conversora")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomConversora;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    @Column(name = "val_modalidadecontratual")
    private Double valModalidadecontratual;

    @Column(name = "val_modalidadeemergencial")
    private Double valModalidadeemergencial;

    @Column(name = "val_modalidadeoportunidade")
    private Double valModalidadeoportunidade;

    @Column(name = "val_modalidadeteste")
    private Double valModalidadeteste;

    @Column(name = "val_modalidadeexcepcional")
    private Double valModalidadeexcepcional;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosIntercambioEnergiaModalidadeEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomConversora() {
        return this.nomConversora;
    }

    public OnsDadosIntercambioEnergiaModalidadeEntity nomConversora(String nomConversora) {
        this.setNomConversora(nomConversora);
        return this;
    }

    public void setNomConversora(String nomConversora) {
        this.nomConversora = nomConversora;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsDadosIntercambioEnergiaModalidadeEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public Double getValModalidadecontratual() {
        return this.valModalidadecontratual;
    }

    public OnsDadosIntercambioEnergiaModalidadeEntity valModalidadecontratual(Double valModalidadecontratual) {
        this.setValModalidadecontratual(valModalidadecontratual);
        return this;
    }

    public void setValModalidadecontratual(Double valModalidadecontratual) {
        this.valModalidadecontratual = valModalidadecontratual;
    }

    public Double getValModalidadeemergencial() {
        return this.valModalidadeemergencial;
    }

    public OnsDadosIntercambioEnergiaModalidadeEntity valModalidadeemergencial(Double valModalidadeemergencial) {
        this.setValModalidadeemergencial(valModalidadeemergencial);
        return this;
    }

    public void setValModalidadeemergencial(Double valModalidadeemergencial) {
        this.valModalidadeemergencial = valModalidadeemergencial;
    }

    public Double getValModalidadeoportunidade() {
        return this.valModalidadeoportunidade;
    }

    public OnsDadosIntercambioEnergiaModalidadeEntity valModalidadeoportunidade(Double valModalidadeoportunidade) {
        this.setValModalidadeoportunidade(valModalidadeoportunidade);
        return this;
    }

    public void setValModalidadeoportunidade(Double valModalidadeoportunidade) {
        this.valModalidadeoportunidade = valModalidadeoportunidade;
    }

    public Double getValModalidadeteste() {
        return this.valModalidadeteste;
    }

    public OnsDadosIntercambioEnergiaModalidadeEntity valModalidadeteste(Double valModalidadeteste) {
        this.setValModalidadeteste(valModalidadeteste);
        return this;
    }

    public void setValModalidadeteste(Double valModalidadeteste) {
        this.valModalidadeteste = valModalidadeteste;
    }

    public Double getValModalidadeexcepcional() {
        return this.valModalidadeexcepcional;
    }

    public OnsDadosIntercambioEnergiaModalidadeEntity valModalidadeexcepcional(Double valModalidadeexcepcional) {
        this.setValModalidadeexcepcional(valModalidadeexcepcional);
        return this;
    }

    public void setValModalidadeexcepcional(Double valModalidadeexcepcional) {
        this.valModalidadeexcepcional = valModalidadeexcepcional;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosIntercambioEnergiaModalidadeEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosIntercambioEnergiaModalidadeEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosIntercambioEnergiaModalidadeEntity{" +
            "id=" + getId() +
            ", nomConversora='" + getNomConversora() + "'" +
            ", dinInstante='" + getDinInstante() + "'" +
            ", valModalidadecontratual=" + getValModalidadecontratual() +
            ", valModalidadeemergencial=" + getValModalidadeemergencial() +
            ", valModalidadeoportunidade=" + getValModalidadeoportunidade() +
            ", valModalidadeteste=" + getValModalidadeteste() +
            ", valModalidadeexcepcional=" + getValModalidadeexcepcional() +
            "}";
    }
}
