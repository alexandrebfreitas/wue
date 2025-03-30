package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsCvuUsinaTermicasEntity.
 */
@Entity
@Table(name = "OnsTable28")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onscvuusinatermicas")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsCvuUsinaTermicasEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dat_iniciosemana")
    private LocalDate datIniciosemana;

    @Column(name = "dat_fimsemana")
    private LocalDate datFimsemana;

    @Column(name = "ano_referencia")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer anoReferencia;

    @Column(name = "mes_referencia")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer mesReferencia;

    @Column(name = "num_revisao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numRevisao;

    @Column(name = "nom_semanaoperativa")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSemanaoperativa;

    @Column(name = "cod_modelos")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer codModelos;

    @Column(name = "id_subsistema")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idSubsistema;

    @Column(name = "nom_subsistema")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubsistema;

    @Column(name = "nom_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsina;

    @Column(name = "val_cvu", precision = 21, scale = 2)
    private BigDecimal valCvu;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsCvuUsinaTermicasEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatIniciosemana() {
        return this.datIniciosemana;
    }

    public OnsCvuUsinaTermicasEntity datIniciosemana(LocalDate datIniciosemana) {
        this.setDatIniciosemana(datIniciosemana);
        return this;
    }

    public void setDatIniciosemana(LocalDate datIniciosemana) {
        this.datIniciosemana = datIniciosemana;
    }

    public LocalDate getDatFimsemana() {
        return this.datFimsemana;
    }

    public OnsCvuUsinaTermicasEntity datFimsemana(LocalDate datFimsemana) {
        this.setDatFimsemana(datFimsemana);
        return this;
    }

    public void setDatFimsemana(LocalDate datFimsemana) {
        this.datFimsemana = datFimsemana;
    }

    public Integer getAnoReferencia() {
        return this.anoReferencia;
    }

    public OnsCvuUsinaTermicasEntity anoReferencia(Integer anoReferencia) {
        this.setAnoReferencia(anoReferencia);
        return this;
    }

    public void setAnoReferencia(Integer anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public Integer getMesReferencia() {
        return this.mesReferencia;
    }

    public OnsCvuUsinaTermicasEntity mesReferencia(Integer mesReferencia) {
        this.setMesReferencia(mesReferencia);
        return this;
    }

    public void setMesReferencia(Integer mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public Integer getNumRevisao() {
        return this.numRevisao;
    }

    public OnsCvuUsinaTermicasEntity numRevisao(Integer numRevisao) {
        this.setNumRevisao(numRevisao);
        return this;
    }

    public void setNumRevisao(Integer numRevisao) {
        this.numRevisao = numRevisao;
    }

    public String getNomSemanaoperativa() {
        return this.nomSemanaoperativa;
    }

    public OnsCvuUsinaTermicasEntity nomSemanaoperativa(String nomSemanaoperativa) {
        this.setNomSemanaoperativa(nomSemanaoperativa);
        return this;
    }

    public void setNomSemanaoperativa(String nomSemanaoperativa) {
        this.nomSemanaoperativa = nomSemanaoperativa;
    }

    public Integer getCodModelos() {
        return this.codModelos;
    }

    public OnsCvuUsinaTermicasEntity codModelos(Integer codModelos) {
        this.setCodModelos(codModelos);
        return this;
    }

    public void setCodModelos(Integer codModelos) {
        this.codModelos = codModelos;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsCvuUsinaTermicasEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsCvuUsinaTermicasEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsCvuUsinaTermicasEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public BigDecimal getValCvu() {
        return this.valCvu;
    }

    public OnsCvuUsinaTermicasEntity valCvu(BigDecimal valCvu) {
        this.setValCvu(valCvu);
        return this;
    }

    public void setValCvu(BigDecimal valCvu) {
        this.valCvu = valCvu;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsCvuUsinaTermicasEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsCvuUsinaTermicasEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsCvuUsinaTermicasEntity{" +
            "id=" + getId() +
            ", datIniciosemana='" + getDatIniciosemana() + "'" +
            ", datFimsemana='" + getDatFimsemana() + "'" +
            ", anoReferencia=" + getAnoReferencia() +
            ", mesReferencia=" + getMesReferencia() +
            ", numRevisao=" + getNumRevisao() +
            ", nomSemanaoperativa='" + getNomSemanaoperativa() + "'" +
            ", codModelos=" + getCodModelos() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", nomUsina='" + getNomUsina() + "'" +
            ", valCvu=" + getValCvu() +
            "}";
    }
}
