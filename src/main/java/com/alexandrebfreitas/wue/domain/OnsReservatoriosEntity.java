package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsReservatoriosEntity.
 */
@Entity
@Table(name = "OnsTable11")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsreservatorios")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsReservatoriosEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom_ree")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomRee;

    @Column(name = "dat_entrada")
    private LocalDate datEntrada;

    @Column(name = "val_cotamaxima")
    private Double valCotamaxima;

    @Column(name = "val_cotaminima")
    private Double valCotaminima;

    @Column(name = "val_volmax")
    private Double valVolmax;

    @Column(name = "val_volmin")
    private Double valVolmin;

    @Column(name = "val_volutiltot")
    private Double valVolutiltot;

    @Column(name = "val_produtibilidadeespecifica")
    private Double valProdutibilidadeespecifica;

    @Column(name = "val_produtividade_65_volutil")
    private Double valProdutividade65volutil;

    @Column(name = "val_tipoperda")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String valTipoperda;

    @Column(name = "val_perda")
    private Double valPerda;

    @Column(name = "val_latitude")
    private Double valLatitude;

    @Column(name = "val_longitude")
    private Double valLongitude;

    @Column(name = "id_reservatorio")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idReservatorio;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsReservatoriosEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomRee() {
        return this.nomRee;
    }

    public OnsReservatoriosEntity nomRee(String nomRee) {
        this.setNomRee(nomRee);
        return this;
    }

    public void setNomRee(String nomRee) {
        this.nomRee = nomRee;
    }

    public LocalDate getDatEntrada() {
        return this.datEntrada;
    }

    public OnsReservatoriosEntity datEntrada(LocalDate datEntrada) {
        this.setDatEntrada(datEntrada);
        return this;
    }

    public void setDatEntrada(LocalDate datEntrada) {
        this.datEntrada = datEntrada;
    }

    public Double getValCotamaxima() {
        return this.valCotamaxima;
    }

    public OnsReservatoriosEntity valCotamaxima(Double valCotamaxima) {
        this.setValCotamaxima(valCotamaxima);
        return this;
    }

    public void setValCotamaxima(Double valCotamaxima) {
        this.valCotamaxima = valCotamaxima;
    }

    public Double getValCotaminima() {
        return this.valCotaminima;
    }

    public OnsReservatoriosEntity valCotaminima(Double valCotaminima) {
        this.setValCotaminima(valCotaminima);
        return this;
    }

    public void setValCotaminima(Double valCotaminima) {
        this.valCotaminima = valCotaminima;
    }

    public Double getValVolmax() {
        return this.valVolmax;
    }

    public OnsReservatoriosEntity valVolmax(Double valVolmax) {
        this.setValVolmax(valVolmax);
        return this;
    }

    public void setValVolmax(Double valVolmax) {
        this.valVolmax = valVolmax;
    }

    public Double getValVolmin() {
        return this.valVolmin;
    }

    public OnsReservatoriosEntity valVolmin(Double valVolmin) {
        this.setValVolmin(valVolmin);
        return this;
    }

    public void setValVolmin(Double valVolmin) {
        this.valVolmin = valVolmin;
    }

    public Double getValVolutiltot() {
        return this.valVolutiltot;
    }

    public OnsReservatoriosEntity valVolutiltot(Double valVolutiltot) {
        this.setValVolutiltot(valVolutiltot);
        return this;
    }

    public void setValVolutiltot(Double valVolutiltot) {
        this.valVolutiltot = valVolutiltot;
    }

    public Double getValProdutibilidadeespecifica() {
        return this.valProdutibilidadeespecifica;
    }

    public OnsReservatoriosEntity valProdutibilidadeespecifica(Double valProdutibilidadeespecifica) {
        this.setValProdutibilidadeespecifica(valProdutibilidadeespecifica);
        return this;
    }

    public void setValProdutibilidadeespecifica(Double valProdutibilidadeespecifica) {
        this.valProdutibilidadeespecifica = valProdutibilidadeespecifica;
    }

    public Double getValProdutividade65volutil() {
        return this.valProdutividade65volutil;
    }

    public OnsReservatoriosEntity valProdutividade65volutil(Double valProdutividade65volutil) {
        this.setValProdutividade65volutil(valProdutividade65volutil);
        return this;
    }

    public void setValProdutividade65volutil(Double valProdutividade65volutil) {
        this.valProdutividade65volutil = valProdutividade65volutil;
    }

    public String getValTipoperda() {
        return this.valTipoperda;
    }

    public OnsReservatoriosEntity valTipoperda(String valTipoperda) {
        this.setValTipoperda(valTipoperda);
        return this;
    }

    public void setValTipoperda(String valTipoperda) {
        this.valTipoperda = valTipoperda;
    }

    public Double getValPerda() {
        return this.valPerda;
    }

    public OnsReservatoriosEntity valPerda(Double valPerda) {
        this.setValPerda(valPerda);
        return this;
    }

    public void setValPerda(Double valPerda) {
        this.valPerda = valPerda;
    }

    public Double getValLatitude() {
        return this.valLatitude;
    }

    public OnsReservatoriosEntity valLatitude(Double valLatitude) {
        this.setValLatitude(valLatitude);
        return this;
    }

    public void setValLatitude(Double valLatitude) {
        this.valLatitude = valLatitude;
    }

    public Double getValLongitude() {
        return this.valLongitude;
    }

    public OnsReservatoriosEntity valLongitude(Double valLongitude) {
        this.setValLongitude(valLongitude);
        return this;
    }

    public void setValLongitude(Double valLongitude) {
        this.valLongitude = valLongitude;
    }

    public String getIdReservatorio() {
        return this.idReservatorio;
    }

    public OnsReservatoriosEntity idReservatorio(String idReservatorio) {
        this.setIdReservatorio(idReservatorio);
        return this;
    }

    public void setIdReservatorio(String idReservatorio) {
        this.idReservatorio = idReservatorio;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsReservatoriosEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsReservatoriosEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsReservatoriosEntity{" +
            "id=" + getId() +
            ", nomRee='" + getNomRee() + "'" +
            ", datEntrada='" + getDatEntrada() + "'" +
            ", valCotamaxima=" + getValCotamaxima() +
            ", valCotaminima=" + getValCotaminima() +
            ", valVolmax=" + getValVolmax() +
            ", valVolmin=" + getValVolmin() +
            ", valVolutiltot=" + getValVolutiltot() +
            ", valProdutibilidadeespecifica=" + getValProdutibilidadeespecifica() +
            ", valProdutividade65volutil=" + getValProdutividade65volutil() +
            ", valTipoperda='" + getValTipoperda() + "'" +
            ", valPerda=" + getValPerda() +
            ", valLatitude=" + getValLatitude() +
            ", valLongitude=" + getValLongitude() +
            ", idReservatorio='" + getIdReservatorio() + "'" +
            "}";
    }
}
