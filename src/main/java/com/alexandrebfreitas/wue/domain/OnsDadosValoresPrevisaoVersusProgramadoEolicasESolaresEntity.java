package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.
 */
@Entity
@Table(name = "OnsTable47")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadosvaloresprevisaoversusprogramadoeolicasesolares")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dat_programacao")
    private LocalDate datProgramacao;

    @Column(name = "num_patamar")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numPatamar;

    @Column(name = "cod_usinapdp")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codUsinapdp;

    @Column(name = "nom_usinapdp")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsinapdp;

    @Column(name = "val_previsao")
    private Double valPrevisao;

    @Column(name = "val_programado")
    private Double valProgramado;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatProgramacao() {
        return this.datProgramacao;
    }

    public OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity datProgramacao(LocalDate datProgramacao) {
        this.setDatProgramacao(datProgramacao);
        return this;
    }

    public void setDatProgramacao(LocalDate datProgramacao) {
        this.datProgramacao = datProgramacao;
    }

    public Integer getNumPatamar() {
        return this.numPatamar;
    }

    public OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity numPatamar(Integer numPatamar) {
        this.setNumPatamar(numPatamar);
        return this;
    }

    public void setNumPatamar(Integer numPatamar) {
        this.numPatamar = numPatamar;
    }

    public String getCodUsinapdp() {
        return this.codUsinapdp;
    }

    public OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity codUsinapdp(String codUsinapdp) {
        this.setCodUsinapdp(codUsinapdp);
        return this;
    }

    public void setCodUsinapdp(String codUsinapdp) {
        this.codUsinapdp = codUsinapdp;
    }

    public String getNomUsinapdp() {
        return this.nomUsinapdp;
    }

    public OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity nomUsinapdp(String nomUsinapdp) {
        this.setNomUsinapdp(nomUsinapdp);
        return this;
    }

    public void setNomUsinapdp(String nomUsinapdp) {
        this.nomUsinapdp = nomUsinapdp;
    }

    public Double getValPrevisao() {
        return this.valPrevisao;
    }

    public OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity valPrevisao(Double valPrevisao) {
        this.setValPrevisao(valPrevisao);
        return this;
    }

    public void setValPrevisao(Double valPrevisao) {
        this.valPrevisao = valPrevisao;
    }

    public Double getValProgramado() {
        return this.valProgramado;
    }

    public OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity valProgramado(Double valProgramado) {
        this.setValProgramado(valProgramado);
        return this;
    }

    public void setValProgramado(Double valProgramado) {
        this.valProgramado = valProgramado;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity{" +
            "id=" + getId() +
            ", datProgramacao='" + getDatProgramacao() + "'" +
            ", numPatamar=" + getNumPatamar() +
            ", codUsinapdp='" + getCodUsinapdp() + "'" +
            ", nomUsinapdp='" + getNomUsinapdp() + "'" +
            ", valPrevisao=" + getValPrevisao() +
            ", valProgramado=" + getValProgramado() +
            "}";
    }
}
