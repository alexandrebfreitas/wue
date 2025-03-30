package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosHidraulicosReservatorioBaseHorariaEntity.
 */
@Entity
@Table(name = "OnsTable24")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadoshidraulicosreservatoriobasehoraria")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosHidraulicosReservatorioBaseHorariaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "val_volumeutil")
    private Double valVolumeutil;

    @Column(name = "val_vazaoafluente")
    private Double valVazaoafluente;

    @Column(name = "val_vazaodefluente")
    private Double valVazaodefluente;

    @Column(name = "val_vazaoturbinada")
    private Double valVazaoturbinada;

    @Column(name = "val_vazaovertida")
    private Double valVazaovertida;

    @Column(name = "val_vazaooutrasestruturas")
    private Double valVazaooutrasestruturas;

    @Column(name = "val_vazaotransferida")
    private Double valVazaotransferida;

    @Column(name = "val_vazaovertidanaoturbinavel")
    private Double valVazaovertidanaoturbinavel;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosHidraulicosReservatorioBaseHorariaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValVolumeutil() {
        return this.valVolumeutil;
    }

    public OnsDadosHidraulicosReservatorioBaseHorariaEntity valVolumeutil(Double valVolumeutil) {
        this.setValVolumeutil(valVolumeutil);
        return this;
    }

    public void setValVolumeutil(Double valVolumeutil) {
        this.valVolumeutil = valVolumeutil;
    }

    public Double getValVazaoafluente() {
        return this.valVazaoafluente;
    }

    public OnsDadosHidraulicosReservatorioBaseHorariaEntity valVazaoafluente(Double valVazaoafluente) {
        this.setValVazaoafluente(valVazaoafluente);
        return this;
    }

    public void setValVazaoafluente(Double valVazaoafluente) {
        this.valVazaoafluente = valVazaoafluente;
    }

    public Double getValVazaodefluente() {
        return this.valVazaodefluente;
    }

    public OnsDadosHidraulicosReservatorioBaseHorariaEntity valVazaodefluente(Double valVazaodefluente) {
        this.setValVazaodefluente(valVazaodefluente);
        return this;
    }

    public void setValVazaodefluente(Double valVazaodefluente) {
        this.valVazaodefluente = valVazaodefluente;
    }

    public Double getValVazaoturbinada() {
        return this.valVazaoturbinada;
    }

    public OnsDadosHidraulicosReservatorioBaseHorariaEntity valVazaoturbinada(Double valVazaoturbinada) {
        this.setValVazaoturbinada(valVazaoturbinada);
        return this;
    }

    public void setValVazaoturbinada(Double valVazaoturbinada) {
        this.valVazaoturbinada = valVazaoturbinada;
    }

    public Double getValVazaovertida() {
        return this.valVazaovertida;
    }

    public OnsDadosHidraulicosReservatorioBaseHorariaEntity valVazaovertida(Double valVazaovertida) {
        this.setValVazaovertida(valVazaovertida);
        return this;
    }

    public void setValVazaovertida(Double valVazaovertida) {
        this.valVazaovertida = valVazaovertida;
    }

    public Double getValVazaooutrasestruturas() {
        return this.valVazaooutrasestruturas;
    }

    public OnsDadosHidraulicosReservatorioBaseHorariaEntity valVazaooutrasestruturas(Double valVazaooutrasestruturas) {
        this.setValVazaooutrasestruturas(valVazaooutrasestruturas);
        return this;
    }

    public void setValVazaooutrasestruturas(Double valVazaooutrasestruturas) {
        this.valVazaooutrasestruturas = valVazaooutrasestruturas;
    }

    public Double getValVazaotransferida() {
        return this.valVazaotransferida;
    }

    public OnsDadosHidraulicosReservatorioBaseHorariaEntity valVazaotransferida(Double valVazaotransferida) {
        this.setValVazaotransferida(valVazaotransferida);
        return this;
    }

    public void setValVazaotransferida(Double valVazaotransferida) {
        this.valVazaotransferida = valVazaotransferida;
    }

    public Double getValVazaovertidanaoturbinavel() {
        return this.valVazaovertidanaoturbinavel;
    }

    public OnsDadosHidraulicosReservatorioBaseHorariaEntity valVazaovertidanaoturbinavel(Double valVazaovertidanaoturbinavel) {
        this.setValVazaovertidanaoturbinavel(valVazaovertidanaoturbinavel);
        return this;
    }

    public void setValVazaovertidanaoturbinavel(Double valVazaovertidanaoturbinavel) {
        this.valVazaovertidanaoturbinavel = valVazaovertidanaoturbinavel;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosHidraulicosReservatorioBaseHorariaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosHidraulicosReservatorioBaseHorariaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosHidraulicosReservatorioBaseHorariaEntity{" +
            "id=" + getId() +
            ", valVolumeutil=" + getValVolumeutil() +
            ", valVazaoafluente=" + getValVazaoafluente() +
            ", valVazaodefluente=" + getValVazaodefluente() +
            ", valVazaoturbinada=" + getValVazaoturbinada() +
            ", valVazaovertida=" + getValVazaovertida() +
            ", valVazaooutrasestruturas=" + getValVazaooutrasestruturas() +
            ", valVazaotransferida=" + getValVazaotransferida() +
            ", valVazaovertidanaoturbinavel=" + getValVazaovertidanaoturbinavel() +
            "}";
    }
}
