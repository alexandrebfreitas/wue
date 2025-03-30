package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsOfertasPrecoParaImportacaoEntity.
 */
@Entity
@Table(name = "OnsTable60")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsofertasprecoparaimportacao")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsOfertasPrecoParaImportacaoEntity implements Serializable {

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

    @Column(name = "dat_iniciovalidade")
    private LocalDate datIniciovalidade;

    @Column(name = "dat_fimvalidade")
    private LocalDate datFimvalidade;

    @Column(name = "val_preco")
    private Double valPreco;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsOfertasPrecoParaImportacaoEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPais() {
        return this.nomPais;
    }

    public OnsOfertasPrecoParaImportacaoEntity nomPais(String nomPais) {
        this.setNomPais(nomPais);
        return this;
    }

    public void setNomPais(String nomPais) {
        this.nomPais = nomPais;
    }

    public String getNomAgente() {
        return this.nomAgente;
    }

    public OnsOfertasPrecoParaImportacaoEntity nomAgente(String nomAgente) {
        this.setNomAgente(nomAgente);
        return this;
    }

    public void setNomAgente(String nomAgente) {
        this.nomAgente = nomAgente;
    }

    public String getNomBloco() {
        return this.nomBloco;
    }

    public OnsOfertasPrecoParaImportacaoEntity nomBloco(String nomBloco) {
        this.setNomBloco(nomBloco);
        return this;
    }

    public void setNomBloco(String nomBloco) {
        this.nomBloco = nomBloco;
    }

    public LocalDate getDatIniciovalidade() {
        return this.datIniciovalidade;
    }

    public OnsOfertasPrecoParaImportacaoEntity datIniciovalidade(LocalDate datIniciovalidade) {
        this.setDatIniciovalidade(datIniciovalidade);
        return this;
    }

    public void setDatIniciovalidade(LocalDate datIniciovalidade) {
        this.datIniciovalidade = datIniciovalidade;
    }

    public LocalDate getDatFimvalidade() {
        return this.datFimvalidade;
    }

    public OnsOfertasPrecoParaImportacaoEntity datFimvalidade(LocalDate datFimvalidade) {
        this.setDatFimvalidade(datFimvalidade);
        return this;
    }

    public void setDatFimvalidade(LocalDate datFimvalidade) {
        this.datFimvalidade = datFimvalidade;
    }

    public Double getValPreco() {
        return this.valPreco;
    }

    public OnsOfertasPrecoParaImportacaoEntity valPreco(Double valPreco) {
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
        if (!(o instanceof OnsOfertasPrecoParaImportacaoEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsOfertasPrecoParaImportacaoEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsOfertasPrecoParaImportacaoEntity{" +
            "id=" + getId() +
            ", nomPais='" + getNomPais() + "'" +
            ", nomAgente='" + getNomAgente() + "'" +
            ", nomBloco='" + getNomBloco() + "'" +
            ", datIniciovalidade='" + getDatIniciovalidade() + "'" +
            ", datFimvalidade='" + getDatFimvalidade() + "'" +
            ", valPreco=" + getValPreco() +
            "}";
    }
}
