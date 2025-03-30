package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsEarDiarioReservatorioEntity.
 */
@Entity
@Table(name = "OnsTable53")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onseardiarioreservatorio")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsEarDiarioReservatorioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_subsistema_jusante")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idSubsistemaJusante;

    @Column(name = "nom_subsistema_jusante")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubsistemaJusante;

    @Column(name = "ear_data")
    private LocalDate earData;

    @Column(name = "ear_reservatorio_subsistema_proprio_mwmes")
    private Double earReservatorioSubsistemaProprioMwmes;

    @Column(name = "ear_reservatorio_subsistema_jusante_mwmes")
    private Double earReservatorioSubsistemaJusanteMwmes;

    @Column(name = "earmax_reservatorio_subsistema_proprio_mwmes")
    private Double earmaxReservatorioSubsistemaProprioMwmes;

    @Column(name = "earmax_reservatorio_subsistema_jusante_mwmes")
    private Double earmaxReservatorioSubsistemaJusanteMwmes;

    @Column(name = "ear_reservatorio_percentual")
    private Double earReservatorioPercentual;

    @Column(name = "ear_total_mwmes")
    private Double earTotalMwmes;

    @Column(name = "ear_maxima_total_mwmes")
    private Double earMaximaTotalMwmes;

    @Column(name = "val_contribearbacia")
    private Double valContribearbacia;

    @Column(name = "val_contribearmaxbacia")
    private Double valContribearmaxbacia;

    @Column(name = "val_contribearsubsistema")
    private Double valContribearsubsistema;

    @Column(name = "val_contribearmaxsubsistema")
    private Double valContribearmaxsubsistema;

    @Column(name = "val_contribearsubsistemajusante")
    private Double valContribearsubsistemajusante;

    @Column(name = "val_contribearmaxsubsistemajusante")
    private Double valContribearmaxsubsistemajusante;

    @Column(name = "val_contribearsin")
    private Double valContribearsin;

    @Column(name = "val_contribearmaxsin")
    private Double valContribearmaxsin;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsEarDiarioReservatorioEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistemaJusante() {
        return this.idSubsistemaJusante;
    }

    public OnsEarDiarioReservatorioEntity idSubsistemaJusante(String idSubsistemaJusante) {
        this.setIdSubsistemaJusante(idSubsistemaJusante);
        return this;
    }

    public void setIdSubsistemaJusante(String idSubsistemaJusante) {
        this.idSubsistemaJusante = idSubsistemaJusante;
    }

    public String getNomSubsistemaJusante() {
        return this.nomSubsistemaJusante;
    }

    public OnsEarDiarioReservatorioEntity nomSubsistemaJusante(String nomSubsistemaJusante) {
        this.setNomSubsistemaJusante(nomSubsistemaJusante);
        return this;
    }

    public void setNomSubsistemaJusante(String nomSubsistemaJusante) {
        this.nomSubsistemaJusante = nomSubsistemaJusante;
    }

    public LocalDate getEarData() {
        return this.earData;
    }

    public OnsEarDiarioReservatorioEntity earData(LocalDate earData) {
        this.setEarData(earData);
        return this;
    }

    public void setEarData(LocalDate earData) {
        this.earData = earData;
    }

    public Double getEarReservatorioSubsistemaProprioMwmes() {
        return this.earReservatorioSubsistemaProprioMwmes;
    }

    public OnsEarDiarioReservatorioEntity earReservatorioSubsistemaProprioMwmes(Double earReservatorioSubsistemaProprioMwmes) {
        this.setEarReservatorioSubsistemaProprioMwmes(earReservatorioSubsistemaProprioMwmes);
        return this;
    }

    public void setEarReservatorioSubsistemaProprioMwmes(Double earReservatorioSubsistemaProprioMwmes) {
        this.earReservatorioSubsistemaProprioMwmes = earReservatorioSubsistemaProprioMwmes;
    }

    public Double getEarReservatorioSubsistemaJusanteMwmes() {
        return this.earReservatorioSubsistemaJusanteMwmes;
    }

    public OnsEarDiarioReservatorioEntity earReservatorioSubsistemaJusanteMwmes(Double earReservatorioSubsistemaJusanteMwmes) {
        this.setEarReservatorioSubsistemaJusanteMwmes(earReservatorioSubsistemaJusanteMwmes);
        return this;
    }

    public void setEarReservatorioSubsistemaJusanteMwmes(Double earReservatorioSubsistemaJusanteMwmes) {
        this.earReservatorioSubsistemaJusanteMwmes = earReservatorioSubsistemaJusanteMwmes;
    }

    public Double getEarmaxReservatorioSubsistemaProprioMwmes() {
        return this.earmaxReservatorioSubsistemaProprioMwmes;
    }

    public OnsEarDiarioReservatorioEntity earmaxReservatorioSubsistemaProprioMwmes(Double earmaxReservatorioSubsistemaProprioMwmes) {
        this.setEarmaxReservatorioSubsistemaProprioMwmes(earmaxReservatorioSubsistemaProprioMwmes);
        return this;
    }

    public void setEarmaxReservatorioSubsistemaProprioMwmes(Double earmaxReservatorioSubsistemaProprioMwmes) {
        this.earmaxReservatorioSubsistemaProprioMwmes = earmaxReservatorioSubsistemaProprioMwmes;
    }

    public Double getEarmaxReservatorioSubsistemaJusanteMwmes() {
        return this.earmaxReservatorioSubsistemaJusanteMwmes;
    }

    public OnsEarDiarioReservatorioEntity earmaxReservatorioSubsistemaJusanteMwmes(Double earmaxReservatorioSubsistemaJusanteMwmes) {
        this.setEarmaxReservatorioSubsistemaJusanteMwmes(earmaxReservatorioSubsistemaJusanteMwmes);
        return this;
    }

    public void setEarmaxReservatorioSubsistemaJusanteMwmes(Double earmaxReservatorioSubsistemaJusanteMwmes) {
        this.earmaxReservatorioSubsistemaJusanteMwmes = earmaxReservatorioSubsistemaJusanteMwmes;
    }

    public Double getEarReservatorioPercentual() {
        return this.earReservatorioPercentual;
    }

    public OnsEarDiarioReservatorioEntity earReservatorioPercentual(Double earReservatorioPercentual) {
        this.setEarReservatorioPercentual(earReservatorioPercentual);
        return this;
    }

    public void setEarReservatorioPercentual(Double earReservatorioPercentual) {
        this.earReservatorioPercentual = earReservatorioPercentual;
    }

    public Double getEarTotalMwmes() {
        return this.earTotalMwmes;
    }

    public OnsEarDiarioReservatorioEntity earTotalMwmes(Double earTotalMwmes) {
        this.setEarTotalMwmes(earTotalMwmes);
        return this;
    }

    public void setEarTotalMwmes(Double earTotalMwmes) {
        this.earTotalMwmes = earTotalMwmes;
    }

    public Double getEarMaximaTotalMwmes() {
        return this.earMaximaTotalMwmes;
    }

    public OnsEarDiarioReservatorioEntity earMaximaTotalMwmes(Double earMaximaTotalMwmes) {
        this.setEarMaximaTotalMwmes(earMaximaTotalMwmes);
        return this;
    }

    public void setEarMaximaTotalMwmes(Double earMaximaTotalMwmes) {
        this.earMaximaTotalMwmes = earMaximaTotalMwmes;
    }

    public Double getValContribearbacia() {
        return this.valContribearbacia;
    }

    public OnsEarDiarioReservatorioEntity valContribearbacia(Double valContribearbacia) {
        this.setValContribearbacia(valContribearbacia);
        return this;
    }

    public void setValContribearbacia(Double valContribearbacia) {
        this.valContribearbacia = valContribearbacia;
    }

    public Double getValContribearmaxbacia() {
        return this.valContribearmaxbacia;
    }

    public OnsEarDiarioReservatorioEntity valContribearmaxbacia(Double valContribearmaxbacia) {
        this.setValContribearmaxbacia(valContribearmaxbacia);
        return this;
    }

    public void setValContribearmaxbacia(Double valContribearmaxbacia) {
        this.valContribearmaxbacia = valContribearmaxbacia;
    }

    public Double getValContribearsubsistema() {
        return this.valContribearsubsistema;
    }

    public OnsEarDiarioReservatorioEntity valContribearsubsistema(Double valContribearsubsistema) {
        this.setValContribearsubsistema(valContribearsubsistema);
        return this;
    }

    public void setValContribearsubsistema(Double valContribearsubsistema) {
        this.valContribearsubsistema = valContribearsubsistema;
    }

    public Double getValContribearmaxsubsistema() {
        return this.valContribearmaxsubsistema;
    }

    public OnsEarDiarioReservatorioEntity valContribearmaxsubsistema(Double valContribearmaxsubsistema) {
        this.setValContribearmaxsubsistema(valContribearmaxsubsistema);
        return this;
    }

    public void setValContribearmaxsubsistema(Double valContribearmaxsubsistema) {
        this.valContribearmaxsubsistema = valContribearmaxsubsistema;
    }

    public Double getValContribearsubsistemajusante() {
        return this.valContribearsubsistemajusante;
    }

    public OnsEarDiarioReservatorioEntity valContribearsubsistemajusante(Double valContribearsubsistemajusante) {
        this.setValContribearsubsistemajusante(valContribearsubsistemajusante);
        return this;
    }

    public void setValContribearsubsistemajusante(Double valContribearsubsistemajusante) {
        this.valContribearsubsistemajusante = valContribearsubsistemajusante;
    }

    public Double getValContribearmaxsubsistemajusante() {
        return this.valContribearmaxsubsistemajusante;
    }

    public OnsEarDiarioReservatorioEntity valContribearmaxsubsistemajusante(Double valContribearmaxsubsistemajusante) {
        this.setValContribearmaxsubsistemajusante(valContribearmaxsubsistemajusante);
        return this;
    }

    public void setValContribearmaxsubsistemajusante(Double valContribearmaxsubsistemajusante) {
        this.valContribearmaxsubsistemajusante = valContribearmaxsubsistemajusante;
    }

    public Double getValContribearsin() {
        return this.valContribearsin;
    }

    public OnsEarDiarioReservatorioEntity valContribearsin(Double valContribearsin) {
        this.setValContribearsin(valContribearsin);
        return this;
    }

    public void setValContribearsin(Double valContribearsin) {
        this.valContribearsin = valContribearsin;
    }

    public Double getValContribearmaxsin() {
        return this.valContribearmaxsin;
    }

    public OnsEarDiarioReservatorioEntity valContribearmaxsin(Double valContribearmaxsin) {
        this.setValContribearmaxsin(valContribearmaxsin);
        return this;
    }

    public void setValContribearmaxsin(Double valContribearmaxsin) {
        this.valContribearmaxsin = valContribearmaxsin;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsEarDiarioReservatorioEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsEarDiarioReservatorioEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsEarDiarioReservatorioEntity{" +
            "id=" + getId() +
            ", idSubsistemaJusante='" + getIdSubsistemaJusante() + "'" +
            ", nomSubsistemaJusante='" + getNomSubsistemaJusante() + "'" +
            ", earData='" + getEarData() + "'" +
            ", earReservatorioSubsistemaProprioMwmes=" + getEarReservatorioSubsistemaProprioMwmes() +
            ", earReservatorioSubsistemaJusanteMwmes=" + getEarReservatorioSubsistemaJusanteMwmes() +
            ", earmaxReservatorioSubsistemaProprioMwmes=" + getEarmaxReservatorioSubsistemaProprioMwmes() +
            ", earmaxReservatorioSubsistemaJusanteMwmes=" + getEarmaxReservatorioSubsistemaJusanteMwmes() +
            ", earReservatorioPercentual=" + getEarReservatorioPercentual() +
            ", earTotalMwmes=" + getEarTotalMwmes() +
            ", earMaximaTotalMwmes=" + getEarMaximaTotalMwmes() +
            ", valContribearbacia=" + getValContribearbacia() +
            ", valContribearmaxbacia=" + getValContribearmaxbacia() +
            ", valContribearsubsistema=" + getValContribearsubsistema() +
            ", valContribearmaxsubsistema=" + getValContribearmaxsubsistema() +
            ", valContribearsubsistemajusante=" + getValContribearsubsistemajusante() +
            ", valContribearmaxsubsistemajusante=" + getValContribearmaxsubsistemajusante() +
            ", valContribearsin=" + getValContribearsin() +
            ", valContribearmaxsin=" + getValContribearmaxsin() +
            "}";
    }
}
