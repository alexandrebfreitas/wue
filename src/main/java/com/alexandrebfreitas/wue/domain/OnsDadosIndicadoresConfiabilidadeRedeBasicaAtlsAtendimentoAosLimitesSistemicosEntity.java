package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.
 */
@Entity
@Table(name = "OnsTable12")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(
    indexName = "onsdadosindicadoresconfiabilidaderedebasicaatlsatendimentoaoslimitessistemicos"
)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom_fluxo")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomFluxo;

    @Column(name = "id_periodicidade")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idPeriodicidade;

    @Column(name = "din_referencia")
    private LocalDate dinReferencia;

    @Column(name = "val_atls")
    private Double valAtls;

    @Column(name = "num_horasviolacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numHorasviolacao;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomFluxo() {
        return this.nomFluxo;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity nomFluxo(String nomFluxo) {
        this.setNomFluxo(nomFluxo);
        return this;
    }

    public void setNomFluxo(String nomFluxo) {
        this.nomFluxo = nomFluxo;
    }

    public String getIdPeriodicidade() {
        return this.idPeriodicidade;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity idPeriodicidade(String idPeriodicidade) {
        this.setIdPeriodicidade(idPeriodicidade);
        return this;
    }

    public void setIdPeriodicidade(String idPeriodicidade) {
        this.idPeriodicidade = idPeriodicidade;
    }

    public LocalDate getDinReferencia() {
        return this.dinReferencia;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity dinReferencia(LocalDate dinReferencia) {
        this.setDinReferencia(dinReferencia);
        return this;
    }

    public void setDinReferencia(LocalDate dinReferencia) {
        this.dinReferencia = dinReferencia;
    }

    public Double getValAtls() {
        return this.valAtls;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity valAtls(Double valAtls) {
        this.setValAtls(valAtls);
        return this;
    }

    public void setValAtls(Double valAtls) {
        this.valAtls = valAtls;
    }

    public Integer getNumHorasviolacao() {
        return this.numHorasviolacao;
    }

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity numHorasviolacao(Integer numHorasviolacao) {
        this.setNumHorasviolacao(numHorasviolacao);
        return this;
    }

    public void setNumHorasviolacao(Integer numHorasviolacao) {
        this.numHorasviolacao = numHorasviolacao;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity)) {
            return false;
        }
        return (
            getId() != null &&
            getId().equals(((OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity) o).getId())
        );
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity{" +
            "id=" + getId() +
            ", nomFluxo='" + getNomFluxo() + "'" +
            ", idPeriodicidade='" + getIdPeriodicidade() + "'" +
            ", dinReferencia='" + getDinReferencia() + "'" +
            ", valAtls=" + getValAtls() +
            ", numHorasviolacao=" + getNumHorasviolacao() +
            "}";
    }
}
