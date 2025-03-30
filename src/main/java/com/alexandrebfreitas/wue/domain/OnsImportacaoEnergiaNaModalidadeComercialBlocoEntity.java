package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity.
 */
@Entity
@Table(name = "OnsTable40")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsimportacaoenergianamodalidadecomercialbloco")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom_pais")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomPais;

    @Column(name = "nom_agente")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomAgente;

    @Column(name = "nom_bloco")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomBloco;

    @Column(name = "cod_bloco")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codBloco;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    @Column(name = "val_importacaoprogramada")
    private Double valImportacaoprogramada;

    @Column(name = "val_importacaodespachada")
    private Double valImportacaodespachada;

    @Column(name = "val_importacaoverificada")
    private Double valImportacaoverificada;

    @Column(name = "val_preco")
    private Double valPreco;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPais() {
        return this.nomPais;
    }

    public OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity nomPais(String nomPais) {
        this.setNomPais(nomPais);
        return this;
    }

    public void setNomPais(String nomPais) {
        this.nomPais = nomPais;
    }

    public String getNomAgente() {
        return this.nomAgente;
    }

    public OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity nomAgente(String nomAgente) {
        this.setNomAgente(nomAgente);
        return this;
    }

    public void setNomAgente(String nomAgente) {
        this.nomAgente = nomAgente;
    }

    public String getNomBloco() {
        return this.nomBloco;
    }

    public OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity nomBloco(String nomBloco) {
        this.setNomBloco(nomBloco);
        return this;
    }

    public void setNomBloco(String nomBloco) {
        this.nomBloco = nomBloco;
    }

    public String getCodBloco() {
        return this.codBloco;
    }

    public OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity codBloco(String codBloco) {
        this.setCodBloco(codBloco);
        return this;
    }

    public void setCodBloco(String codBloco) {
        this.codBloco = codBloco;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public Double getValImportacaoprogramada() {
        return this.valImportacaoprogramada;
    }

    public OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity valImportacaoprogramada(Double valImportacaoprogramada) {
        this.setValImportacaoprogramada(valImportacaoprogramada);
        return this;
    }

    public void setValImportacaoprogramada(Double valImportacaoprogramada) {
        this.valImportacaoprogramada = valImportacaoprogramada;
    }

    public Double getValImportacaodespachada() {
        return this.valImportacaodespachada;
    }

    public OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity valImportacaodespachada(Double valImportacaodespachada) {
        this.setValImportacaodespachada(valImportacaodespachada);
        return this;
    }

    public void setValImportacaodespachada(Double valImportacaodespachada) {
        this.valImportacaodespachada = valImportacaodespachada;
    }

    public Double getValImportacaoverificada() {
        return this.valImportacaoverificada;
    }

    public OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity valImportacaoverificada(Double valImportacaoverificada) {
        this.setValImportacaoverificada(valImportacaoverificada);
        return this;
    }

    public void setValImportacaoverificada(Double valImportacaoverificada) {
        this.valImportacaoverificada = valImportacaoverificada;
    }

    public Double getValPreco() {
        return this.valPreco;
    }

    public OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity valPreco(Double valPreco) {
        this.setValPreco(valPreco);
        return this;
    }

    public void setValPreco(Double valPreco) {
        this.valPreco = valPreco;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity{" +
            "id=" + getId() +
            ", nomPais='" + getNomPais() + "'" +
            ", nomAgente='" + getNomAgente() + "'" +
            ", nomBloco='" + getNomBloco() + "'" +
            ", codBloco='" + getCodBloco() + "'" +
            ", dinInstante='" + getDinInstante() + "'" +
            ", valImportacaoprogramada=" + getValImportacaoprogramada() +
            ", valImportacaodespachada=" + getValImportacaodespachada() +
            ", valImportacaoverificada=" + getValImportacaoverificada() +
            ", valPreco=" + getValPreco() +
            "}";
    }
}
