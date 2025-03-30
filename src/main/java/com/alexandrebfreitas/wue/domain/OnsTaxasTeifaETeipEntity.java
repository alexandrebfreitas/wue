package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsTaxasTeifaETeipEntity.
 */
@Entity
@Table(name = "OnsTable49")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onstaxasteifaeteip")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsTaxasTeifaETeipEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsina;

    @Column(name = "cod_ceg")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codCeg;

    @Column(name = "id_tipousina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idTipousina;

    @Column(name = "din_mes")
    private LocalDate dinMes;

    @Column(name = "nom_taxa")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTaxa;

    @Column(name = "val_taxa")
    private Double valTaxa;

    @Column(name = "num_versao")
    private Double numVersao;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsTaxasTeifaETeipEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsTaxasTeifaETeipEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public String getCodCeg() {
        return this.codCeg;
    }

    public OnsTaxasTeifaETeipEntity codCeg(String codCeg) {
        this.setCodCeg(codCeg);
        return this;
    }

    public void setCodCeg(String codCeg) {
        this.codCeg = codCeg;
    }

    public String getIdTipousina() {
        return this.idTipousina;
    }

    public OnsTaxasTeifaETeipEntity idTipousina(String idTipousina) {
        this.setIdTipousina(idTipousina);
        return this;
    }

    public void setIdTipousina(String idTipousina) {
        this.idTipousina = idTipousina;
    }

    public LocalDate getDinMes() {
        return this.dinMes;
    }

    public OnsTaxasTeifaETeipEntity dinMes(LocalDate dinMes) {
        this.setDinMes(dinMes);
        return this;
    }

    public void setDinMes(LocalDate dinMes) {
        this.dinMes = dinMes;
    }

    public String getNomTaxa() {
        return this.nomTaxa;
    }

    public OnsTaxasTeifaETeipEntity nomTaxa(String nomTaxa) {
        this.setNomTaxa(nomTaxa);
        return this;
    }

    public void setNomTaxa(String nomTaxa) {
        this.nomTaxa = nomTaxa;
    }

    public Double getValTaxa() {
        return this.valTaxa;
    }

    public OnsTaxasTeifaETeipEntity valTaxa(Double valTaxa) {
        this.setValTaxa(valTaxa);
        return this;
    }

    public void setValTaxa(Double valTaxa) {
        this.valTaxa = valTaxa;
    }

    public Double getNumVersao() {
        return this.numVersao;
    }

    public OnsTaxasTeifaETeipEntity numVersao(Double numVersao) {
        this.setNumVersao(numVersao);
        return this;
    }

    public void setNumVersao(Double numVersao) {
        this.numVersao = numVersao;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsTaxasTeifaETeipEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsTaxasTeifaETeipEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsTaxasTeifaETeipEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsTaxasTeifaETeipEntity{" +
            "id=" + getId() +
            ", nomUsina='" + getNomUsina() + "'" +
            ", codCeg='" + getCodCeg() + "'" +
            ", idTipousina='" + getIdTipousina() + "'" +
            ", dinMes='" + getDinMes() + "'" +
            ", nomTaxa='" + getNomTaxa() + "'" +
            ", valTaxa=" + getValTaxa() +
            ", numVersao=" + getNumVersao() +
            ", dinInstante='" + getDinInstante() + "'" +
            "}";
    }
}
