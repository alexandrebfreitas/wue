package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.
 */
@Entity
@Table(name = "OnsTable46")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadosrestricaooperacaoconstrainedoffusinaseolicas")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_subsistema")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idSubsistema;

    @Column(name = "nom_subsistema")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubsistema;

    @Column(name = "id_estado")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idEstado;

    @Column(name = "nom_estado")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomEstado;

    @Column(name = "nom_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsina;

    @Column(name = "id_ons")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idOns;

    @Column(name = "ceg")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String ceg;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    @Column(name = "val_geracao")
    private Double valGeracao;

    @Column(name = "val_geracaolimitada")
    private Double valGeracaolimitada;

    @Column(name = "val_disponibilidade")
    private Double valDisponibilidade;

    @Column(name = "val_geracaoreferencia")
    private Double valGeracaoreferencia;

    @Column(name = "val_geracaoreferenciafinal")
    private Double valGeracaoreferenciafinal;

    @Column(name = "cod_razaorestricao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codRazaorestricao;

    @Column(name = "cod_origemrestricao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codOrigemrestricao;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getIdEstado() {
        return this.idEstado;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity idEstado(String idEstado) {
        this.setIdEstado(idEstado);
        return this;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity nomEstado(String nomEstado) {
        this.setNomEstado(nomEstado);
        return this;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public String getIdOns() {
        return this.idOns;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity idOns(String idOns) {
        this.setIdOns(idOns);
        return this;
    }

    public void setIdOns(String idOns) {
        this.idOns = idOns;
    }

    public String getCeg() {
        return this.ceg;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity ceg(String ceg) {
        this.setCeg(ceg);
        return this;
    }

    public void setCeg(String ceg) {
        this.ceg = ceg;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public Double getValGeracao() {
        return this.valGeracao;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity valGeracao(Double valGeracao) {
        this.setValGeracao(valGeracao);
        return this;
    }

    public void setValGeracao(Double valGeracao) {
        this.valGeracao = valGeracao;
    }

    public Double getValGeracaolimitada() {
        return this.valGeracaolimitada;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity valGeracaolimitada(Double valGeracaolimitada) {
        this.setValGeracaolimitada(valGeracaolimitada);
        return this;
    }

    public void setValGeracaolimitada(Double valGeracaolimitada) {
        this.valGeracaolimitada = valGeracaolimitada;
    }

    public Double getValDisponibilidade() {
        return this.valDisponibilidade;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity valDisponibilidade(Double valDisponibilidade) {
        this.setValDisponibilidade(valDisponibilidade);
        return this;
    }

    public void setValDisponibilidade(Double valDisponibilidade) {
        this.valDisponibilidade = valDisponibilidade;
    }

    public Double getValGeracaoreferencia() {
        return this.valGeracaoreferencia;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity valGeracaoreferencia(Double valGeracaoreferencia) {
        this.setValGeracaoreferencia(valGeracaoreferencia);
        return this;
    }

    public void setValGeracaoreferencia(Double valGeracaoreferencia) {
        this.valGeracaoreferencia = valGeracaoreferencia;
    }

    public Double getValGeracaoreferenciafinal() {
        return this.valGeracaoreferenciafinal;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity valGeracaoreferenciafinal(Double valGeracaoreferenciafinal) {
        this.setValGeracaoreferenciafinal(valGeracaoreferenciafinal);
        return this;
    }

    public void setValGeracaoreferenciafinal(Double valGeracaoreferenciafinal) {
        this.valGeracaoreferenciafinal = valGeracaoreferenciafinal;
    }

    public String getCodRazaorestricao() {
        return this.codRazaorestricao;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity codRazaorestricao(String codRazaorestricao) {
        this.setCodRazaorestricao(codRazaorestricao);
        return this;
    }

    public void setCodRazaorestricao(String codRazaorestricao) {
        this.codRazaorestricao = codRazaorestricao;
    }

    public String getCodOrigemrestricao() {
        return this.codOrigemrestricao;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity codOrigemrestricao(String codOrigemrestricao) {
        this.setCodOrigemrestricao(codOrigemrestricao);
        return this;
    }

    public void setCodOrigemrestricao(String codOrigemrestricao) {
        this.codOrigemrestricao = codOrigemrestricao;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", idEstado='" + getIdEstado() + "'" +
            ", nomEstado='" + getNomEstado() + "'" +
            ", nomUsina='" + getNomUsina() + "'" +
            ", idOns='" + getIdOns() + "'" +
            ", ceg='" + getCeg() + "'" +
            ", dinInstante='" + getDinInstante() + "'" +
            ", valGeracao=" + getValGeracao() +
            ", valGeracaolimitada=" + getValGeracaolimitada() +
            ", valDisponibilidade=" + getValDisponibilidade() +
            ", valGeracaoreferencia=" + getValGeracaoreferencia() +
            ", valGeracaoreferenciafinal=" + getValGeracaoreferenciafinal() +
            ", codRazaorestricao='" + getCodRazaorestricao() + "'" +
            ", codOrigemrestricao='" + getCodOrigemrestricao() + "'" +
            "}";
    }
}
