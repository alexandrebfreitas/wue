package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosHidraulicosReservatorioBaseDiariaEntity.
 */
@Entity
@Table(name = "OnsTable32")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadoshidraulicosreservatoriobasediaria")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosHidraulicosReservatorioBaseDiariaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "val_nivelmontante")
    private Double valNivelmontante;

    @Column(name = "val_niveljusante")
    private Double valNiveljusante;

    @Column(name = "val_volumeutilcon")
    private Double valVolumeutilcon;

    @Column(name = "val_vazaoafluente")
    private Double valVazaoafluente;

    @Column(name = "val_vazaoturbinada")
    private Double valVazaoturbinada;

    @Column(name = "val_vazaovertida")
    private Double valVazaovertida;

    @Column(name = "val_vazaooutrasestruturas")
    private Double valVazaooutrasestruturas;

    @Column(name = "val_vazaodefluente")
    private Double valVazaodefluente;

    @Column(name = "val_vazaotransferida")
    private Double valVazaotransferida;

    @Column(name = "val_vazaonatural")
    private Double valVazaonatural;

    @Column(name = "val_vazaoartificial")
    private Double valVazaoartificial;

    @Column(name = "val_vazaoincremental")
    private Double valVazaoincremental;

    @Column(name = "val_vazaoevaporacaoliquida")
    private Double valVazaoevaporacaoliquida;

    @Column(name = "val_vazaousoconsuntivo")
    private Double valVazaousoconsuntivo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValNivelmontante() {
        return this.valNivelmontante;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valNivelmontante(Double valNivelmontante) {
        this.setValNivelmontante(valNivelmontante);
        return this;
    }

    public void setValNivelmontante(Double valNivelmontante) {
        this.valNivelmontante = valNivelmontante;
    }

    public Double getValNiveljusante() {
        return this.valNiveljusante;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valNiveljusante(Double valNiveljusante) {
        this.setValNiveljusante(valNiveljusante);
        return this;
    }

    public void setValNiveljusante(Double valNiveljusante) {
        this.valNiveljusante = valNiveljusante;
    }

    public Double getValVolumeutilcon() {
        return this.valVolumeutilcon;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valVolumeutilcon(Double valVolumeutilcon) {
        this.setValVolumeutilcon(valVolumeutilcon);
        return this;
    }

    public void setValVolumeutilcon(Double valVolumeutilcon) {
        this.valVolumeutilcon = valVolumeutilcon;
    }

    public Double getValVazaoafluente() {
        return this.valVazaoafluente;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valVazaoafluente(Double valVazaoafluente) {
        this.setValVazaoafluente(valVazaoafluente);
        return this;
    }

    public void setValVazaoafluente(Double valVazaoafluente) {
        this.valVazaoafluente = valVazaoafluente;
    }

    public Double getValVazaoturbinada() {
        return this.valVazaoturbinada;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valVazaoturbinada(Double valVazaoturbinada) {
        this.setValVazaoturbinada(valVazaoturbinada);
        return this;
    }

    public void setValVazaoturbinada(Double valVazaoturbinada) {
        this.valVazaoturbinada = valVazaoturbinada;
    }

    public Double getValVazaovertida() {
        return this.valVazaovertida;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valVazaovertida(Double valVazaovertida) {
        this.setValVazaovertida(valVazaovertida);
        return this;
    }

    public void setValVazaovertida(Double valVazaovertida) {
        this.valVazaovertida = valVazaovertida;
    }

    public Double getValVazaooutrasestruturas() {
        return this.valVazaooutrasestruturas;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valVazaooutrasestruturas(Double valVazaooutrasestruturas) {
        this.setValVazaooutrasestruturas(valVazaooutrasestruturas);
        return this;
    }

    public void setValVazaooutrasestruturas(Double valVazaooutrasestruturas) {
        this.valVazaooutrasestruturas = valVazaooutrasestruturas;
    }

    public Double getValVazaodefluente() {
        return this.valVazaodefluente;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valVazaodefluente(Double valVazaodefluente) {
        this.setValVazaodefluente(valVazaodefluente);
        return this;
    }

    public void setValVazaodefluente(Double valVazaodefluente) {
        this.valVazaodefluente = valVazaodefluente;
    }

    public Double getValVazaotransferida() {
        return this.valVazaotransferida;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valVazaotransferida(Double valVazaotransferida) {
        this.setValVazaotransferida(valVazaotransferida);
        return this;
    }

    public void setValVazaotransferida(Double valVazaotransferida) {
        this.valVazaotransferida = valVazaotransferida;
    }

    public Double getValVazaonatural() {
        return this.valVazaonatural;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valVazaonatural(Double valVazaonatural) {
        this.setValVazaonatural(valVazaonatural);
        return this;
    }

    public void setValVazaonatural(Double valVazaonatural) {
        this.valVazaonatural = valVazaonatural;
    }

    public Double getValVazaoartificial() {
        return this.valVazaoartificial;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valVazaoartificial(Double valVazaoartificial) {
        this.setValVazaoartificial(valVazaoartificial);
        return this;
    }

    public void setValVazaoartificial(Double valVazaoartificial) {
        this.valVazaoartificial = valVazaoartificial;
    }

    public Double getValVazaoincremental() {
        return this.valVazaoincremental;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valVazaoincremental(Double valVazaoincremental) {
        this.setValVazaoincremental(valVazaoincremental);
        return this;
    }

    public void setValVazaoincremental(Double valVazaoincremental) {
        this.valVazaoincremental = valVazaoincremental;
    }

    public Double getValVazaoevaporacaoliquida() {
        return this.valVazaoevaporacaoliquida;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valVazaoevaporacaoliquida(Double valVazaoevaporacaoliquida) {
        this.setValVazaoevaporacaoliquida(valVazaoevaporacaoliquida);
        return this;
    }

    public void setValVazaoevaporacaoliquida(Double valVazaoevaporacaoliquida) {
        this.valVazaoevaporacaoliquida = valVazaoevaporacaoliquida;
    }

    public Double getValVazaousoconsuntivo() {
        return this.valVazaousoconsuntivo;
    }

    public OnsDadosHidraulicosReservatorioBaseDiariaEntity valVazaousoconsuntivo(Double valVazaousoconsuntivo) {
        this.setValVazaousoconsuntivo(valVazaousoconsuntivo);
        return this;
    }

    public void setValVazaousoconsuntivo(Double valVazaousoconsuntivo) {
        this.valVazaousoconsuntivo = valVazaousoconsuntivo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosHidraulicosReservatorioBaseDiariaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosHidraulicosReservatorioBaseDiariaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosHidraulicosReservatorioBaseDiariaEntity{" +
            "id=" + getId() +
            ", valNivelmontante=" + getValNivelmontante() +
            ", valNiveljusante=" + getValNiveljusante() +
            ", valVolumeutilcon=" + getValVolumeutilcon() +
            ", valVazaoafluente=" + getValVazaoafluente() +
            ", valVazaoturbinada=" + getValVazaoturbinada() +
            ", valVazaovertida=" + getValVazaovertida() +
            ", valVazaooutrasestruturas=" + getValVazaooutrasestruturas() +
            ", valVazaodefluente=" + getValVazaodefluente() +
            ", valVazaotransferida=" + getValVazaotransferida() +
            ", valVazaonatural=" + getValVazaonatural() +
            ", valVazaoartificial=" + getValVazaoartificial() +
            ", valVazaoincremental=" + getValVazaoincremental() +
            ", valVazaoevaporacaoliquida=" + getValVazaoevaporacaoliquida() +
            ", valVazaousoconsuntivo=" + getValVazaousoconsuntivo() +
            "}";
    }
}
