package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.
 */
@Entity
@Table(name = "OnsTable5")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(
    indexName = "onsdadosrestricaooperacaoconstrainedoffusinasfotovoltaicasdetalhamentousinas"
)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity implements Serializable {

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

    @Column(name = "val_irradianciaverificado")
    private Double valIrradianciaverificado;

    @Column(name = "flg_dadoirradianciainvalido")
    private Double flgDadoirradianciainvalido;

    @Column(name = "val_geracaoestimada")
    private Double valGeracaoestimada;

    @Column(name = "val_geracaoverificada")
    private Double valGeracaoverificada;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomModalidadeoperacao() {
        return this.nomModalidadeoperacao;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity nomModalidadeoperacao(
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

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity idEstado(String idEstado) {
        this.setIdEstado(idEstado);
        return this;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomConjuntousina() {
        return this.nomConjuntousina;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity nomConjuntousina(String nomConjuntousina) {
        this.setNomConjuntousina(nomConjuntousina);
        return this;
    }

    public void setNomConjuntousina(String nomConjuntousina) {
        this.nomConjuntousina = nomConjuntousina;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public String getIdOns() {
        return this.idOns;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity idOns(String idOns) {
        this.setIdOns(idOns);
        return this;
    }

    public void setIdOns(String idOns) {
        this.idOns = idOns;
    }

    public String getCeg() {
        return this.ceg;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity ceg(String ceg) {
        this.setCeg(ceg);
        return this;
    }

    public void setCeg(String ceg) {
        this.ceg = ceg;
    }

    public Double getValIrradianciaverificado() {
        return this.valIrradianciaverificado;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity valIrradianciaverificado(
        Double valIrradianciaverificado
    ) {
        this.setValIrradianciaverificado(valIrradianciaverificado);
        return this;
    }

    public void setValIrradianciaverificado(Double valIrradianciaverificado) {
        this.valIrradianciaverificado = valIrradianciaverificado;
    }

    public Double getFlgDadoirradianciainvalido() {
        return this.flgDadoirradianciainvalido;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity flgDadoirradianciainvalido(
        Double flgDadoirradianciainvalido
    ) {
        this.setFlgDadoirradianciainvalido(flgDadoirradianciainvalido);
        return this;
    }

    public void setFlgDadoirradianciainvalido(Double flgDadoirradianciainvalido) {
        this.flgDadoirradianciainvalido = flgDadoirradianciainvalido;
    }

    public Double getValGeracaoestimada() {
        return this.valGeracaoestimada;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity valGeracaoestimada(
        Double valGeracaoestimada
    ) {
        this.setValGeracaoestimada(valGeracaoestimada);
        return this;
    }

    public void setValGeracaoestimada(Double valGeracaoestimada) {
        this.valGeracaoestimada = valGeracaoestimada;
    }

    public Double getValGeracaoverificada() {
        return this.valGeracaoverificada;
    }

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity valGeracaoverificada(
        Double valGeracaoverificada
    ) {
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
        if (!(o instanceof OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity)) {
            return false;
        }
        return (
            getId() != null &&
            getId().equals(((OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity) o).getId())
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
        return "OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomModalidadeoperacao='" + getNomModalidadeoperacao() + "'" +
            ", idEstado='" + getIdEstado() + "'" +
            ", nomConjuntousina='" + getNomConjuntousina() + "'" +
            ", nomUsina='" + getNomUsina() + "'" +
            ", dinInstante='" + getDinInstante() + "'" +
            ", idOns='" + getIdOns() + "'" +
            ", ceg='" + getCeg() + "'" +
            ", valIrradianciaverificado=" + getValIrradianciaverificado() +
            ", flgDadoirradianciainvalido=" + getFlgDadoirradianciainvalido() +
            ", valGeracaoestimada=" + getValGeracaoestimada() +
            ", valGeracaoverificada=" + getValGeracaoverificada() +
            "}";
    }
}
