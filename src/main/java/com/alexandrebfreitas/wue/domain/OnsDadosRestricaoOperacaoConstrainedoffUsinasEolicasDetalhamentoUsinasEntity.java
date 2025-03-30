package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.
 */
@Entity
@Table(name = "OnsTable51")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(
    indexName = "onsdadosrestricaooperacaoconstrainedoffusinaseolicasdetalhamentousinas"
)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_subsistema")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idSubsistema;

    @Column(name = "nom_modalidadeoperacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomModalidadeoperacao;

    @Column(name = "id_estado")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idEstado;

    @Column(name = "nom_conjuntousina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomConjuntousina;

    @Column(name = "nom_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsina;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    @Column(name = "id_ons")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idOns;

    @Column(name = "ceg")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String ceg;

    @Column(name = "val_ventoverificado")
    private Double valVentoverificado;

    @Column(name = "flg_dadoventoinvalido")
    private Double flgDadoventoinvalido;

    @Column(name = "val_geracaoestimada")
    private Double valGeracaoestimada;

    @Column(name = "val_geracaoverificada")
    private Double valGeracaoverificada;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomModalidadeoperacao() {
        return this.nomModalidadeoperacao;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity nomModalidadeoperacao(
        String nomModalidadeoperacao
    ) {
        this.setNomModalidadeoperacao(nomModalidadeoperacao);
        return this;
    }

    public void setNomModalidadeoperacao(String nomModalidadeoperacao) {
        this.nomModalidadeoperacao = nomModalidadeoperacao;
    }

    public String getIdEstado() {
        return this.idEstado;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity idEstado(String idEstado) {
        this.setIdEstado(idEstado);
        return this;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomConjuntousina() {
        return this.nomConjuntousina;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity nomConjuntousina(String nomConjuntousina) {
        this.setNomConjuntousina(nomConjuntousina);
        return this;
    }

    public void setNomConjuntousina(String nomConjuntousina) {
        this.nomConjuntousina = nomConjuntousina;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public String getIdOns() {
        return this.idOns;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity idOns(String idOns) {
        this.setIdOns(idOns);
        return this;
    }

    public void setIdOns(String idOns) {
        this.idOns = idOns;
    }

    public String getCeg() {
        return this.ceg;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity ceg(String ceg) {
        this.setCeg(ceg);
        return this;
    }

    public void setCeg(String ceg) {
        this.ceg = ceg;
    }

    public Double getValVentoverificado() {
        return this.valVentoverificado;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity valVentoverificado(Double valVentoverificado) {
        this.setValVentoverificado(valVentoverificado);
        return this;
    }

    public void setValVentoverificado(Double valVentoverificado) {
        this.valVentoverificado = valVentoverificado;
    }

    public Double getFlgDadoventoinvalido() {
        return this.flgDadoventoinvalido;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity flgDadoventoinvalido(Double flgDadoventoinvalido) {
        this.setFlgDadoventoinvalido(flgDadoventoinvalido);
        return this;
    }

    public void setFlgDadoventoinvalido(Double flgDadoventoinvalido) {
        this.flgDadoventoinvalido = flgDadoventoinvalido;
    }

    public Double getValGeracaoestimada() {
        return this.valGeracaoestimada;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity valGeracaoestimada(Double valGeracaoestimada) {
        this.setValGeracaoestimada(valGeracaoestimada);
        return this;
    }

    public void setValGeracaoestimada(Double valGeracaoestimada) {
        this.valGeracaoestimada = valGeracaoestimada;
    }

    public Double getValGeracaoverificada() {
        return this.valGeracaoverificada;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity valGeracaoverificada(Double valGeracaoverificada) {
        this.setValGeracaoverificada(valGeracaoverificada);
        return this;
    }

    public void setValGeracaoverificada(Double valGeracaoverificada) {
        this.valGeracaoverificada = valGeracaoverificada;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity)) {
            return false;
        }
        return (
            getId() != null && getId().equals(((OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity) o).getId())
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
        return "OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomModalidadeoperacao='" + getNomModalidadeoperacao() + "'" +
            ", idEstado='" + getIdEstado() + "'" +
            ", nomConjuntousina='" + getNomConjuntousina() + "'" +
            ", nomUsina='" + getNomUsina() + "'" +
            ", dinInstante='" + getDinInstante() + "'" +
            ", idOns='" + getIdOns() + "'" +
            ", ceg='" + getCeg() + "'" +
            ", valVentoverificado=" + getValVentoverificado() +
            ", flgDadoventoinvalido=" + getFlgDadoventoinvalido() +
            ", valGeracaoestimada=" + getValGeracaoestimada() +
            ", valGeracaoverificada=" + getValGeracaoverificada() +
            "}";
    }
}
