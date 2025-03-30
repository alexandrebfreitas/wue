package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosValoresProgramacaoDiariaEntity.
 */
@Entity
@Table(name = "OnsTable27")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadosvaloresprogramacaodiaria")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosValoresProgramacaoDiariaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "din_programacaodia")
    private LocalDate dinProgramacaodia;

    @Column(name = "num_patamar")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numPatamar;

    @Column(name = "cod_exibicaousina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codExibicaousina;

    @Column(name = "nom_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsina;

    @Column(name = "tip_geracao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String tipGeracao;

    @Column(name = "nom_modalidadeoperacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomModalidadeoperacao;

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

    @Column(name = "val_geracaoprogramada")
    private Double valGeracaoprogramada;

    @Column(name = "val_disponibilidade")
    private Double valDisponibilidade;

    @Column(name = "val_ordemmerito")
    private Double valOrdemmerito;

    @Column(name = "val_inflexibilidade")
    private Double valInflexibilidade;

    @Column(name = "val_uc")
    private Double valUc;

    @Column(name = "val_razaoeletrica")
    private Double valRazaoeletrica;

    @Column(name = "val_geracaoenergetica")
    private Double valGeracaoenergetica;

    @Column(name = "val_gesubgsub")
    private Double valGesubgsub;

    @Column(name = "val_exportacao")
    private Double valExportacao;

    @Column(name = "val_reposicaoexportacao")
    private Double valReposicaoexportacao;

    @Column(name = "val_faltacombustivel")
    private Double valFaltacombustivel;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosValoresProgramacaoDiariaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDinProgramacaodia() {
        return this.dinProgramacaodia;
    }

    public OnsDadosValoresProgramacaoDiariaEntity dinProgramacaodia(LocalDate dinProgramacaodia) {
        this.setDinProgramacaodia(dinProgramacaodia);
        return this;
    }

    public void setDinProgramacaodia(LocalDate dinProgramacaodia) {
        this.dinProgramacaodia = dinProgramacaodia;
    }

    public Integer getNumPatamar() {
        return this.numPatamar;
    }

    public OnsDadosValoresProgramacaoDiariaEntity numPatamar(Integer numPatamar) {
        this.setNumPatamar(numPatamar);
        return this;
    }

    public void setNumPatamar(Integer numPatamar) {
        this.numPatamar = numPatamar;
    }

    public String getCodExibicaousina() {
        return this.codExibicaousina;
    }

    public OnsDadosValoresProgramacaoDiariaEntity codExibicaousina(String codExibicaousina) {
        this.setCodExibicaousina(codExibicaousina);
        return this;
    }

    public void setCodExibicaousina(String codExibicaousina) {
        this.codExibicaousina = codExibicaousina;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsDadosValoresProgramacaoDiariaEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public String getTipGeracao() {
        return this.tipGeracao;
    }

    public OnsDadosValoresProgramacaoDiariaEntity tipGeracao(String tipGeracao) {
        this.setTipGeracao(tipGeracao);
        return this;
    }

    public void setTipGeracao(String tipGeracao) {
        this.tipGeracao = tipGeracao;
    }

    public String getNomModalidadeoperacao() {
        return this.nomModalidadeoperacao;
    }

    public OnsDadosValoresProgramacaoDiariaEntity nomModalidadeoperacao(String nomModalidadeoperacao) {
        this.setNomModalidadeoperacao(nomModalidadeoperacao);
        return this;
    }

    public void setNomModalidadeoperacao(String nomModalidadeoperacao) {
        this.nomModalidadeoperacao = nomModalidadeoperacao;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsDadosValoresProgramacaoDiariaEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsDadosValoresProgramacaoDiariaEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getIdEstado() {
        return this.idEstado;
    }

    public OnsDadosValoresProgramacaoDiariaEntity idEstado(String idEstado) {
        this.setIdEstado(idEstado);
        return this;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public OnsDadosValoresProgramacaoDiariaEntity nomEstado(String nomEstado) {
        this.setNomEstado(nomEstado);
        return this;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public Double getValGeracaoprogramada() {
        return this.valGeracaoprogramada;
    }

    public OnsDadosValoresProgramacaoDiariaEntity valGeracaoprogramada(Double valGeracaoprogramada) {
        this.setValGeracaoprogramada(valGeracaoprogramada);
        return this;
    }

    public void setValGeracaoprogramada(Double valGeracaoprogramada) {
        this.valGeracaoprogramada = valGeracaoprogramada;
    }

    public Double getValDisponibilidade() {
        return this.valDisponibilidade;
    }

    public OnsDadosValoresProgramacaoDiariaEntity valDisponibilidade(Double valDisponibilidade) {
        this.setValDisponibilidade(valDisponibilidade);
        return this;
    }

    public void setValDisponibilidade(Double valDisponibilidade) {
        this.valDisponibilidade = valDisponibilidade;
    }

    public Double getValOrdemmerito() {
        return this.valOrdemmerito;
    }

    public OnsDadosValoresProgramacaoDiariaEntity valOrdemmerito(Double valOrdemmerito) {
        this.setValOrdemmerito(valOrdemmerito);
        return this;
    }

    public void setValOrdemmerito(Double valOrdemmerito) {
        this.valOrdemmerito = valOrdemmerito;
    }

    public Double getValInflexibilidade() {
        return this.valInflexibilidade;
    }

    public OnsDadosValoresProgramacaoDiariaEntity valInflexibilidade(Double valInflexibilidade) {
        this.setValInflexibilidade(valInflexibilidade);
        return this;
    }

    public void setValInflexibilidade(Double valInflexibilidade) {
        this.valInflexibilidade = valInflexibilidade;
    }

    public Double getValUc() {
        return this.valUc;
    }

    public OnsDadosValoresProgramacaoDiariaEntity valUc(Double valUc) {
        this.setValUc(valUc);
        return this;
    }

    public void setValUc(Double valUc) {
        this.valUc = valUc;
    }

    public Double getValRazaoeletrica() {
        return this.valRazaoeletrica;
    }

    public OnsDadosValoresProgramacaoDiariaEntity valRazaoeletrica(Double valRazaoeletrica) {
        this.setValRazaoeletrica(valRazaoeletrica);
        return this;
    }

    public void setValRazaoeletrica(Double valRazaoeletrica) {
        this.valRazaoeletrica = valRazaoeletrica;
    }

    public Double getValGeracaoenergetica() {
        return this.valGeracaoenergetica;
    }

    public OnsDadosValoresProgramacaoDiariaEntity valGeracaoenergetica(Double valGeracaoenergetica) {
        this.setValGeracaoenergetica(valGeracaoenergetica);
        return this;
    }

    public void setValGeracaoenergetica(Double valGeracaoenergetica) {
        this.valGeracaoenergetica = valGeracaoenergetica;
    }

    public Double getValGesubgsub() {
        return this.valGesubgsub;
    }

    public OnsDadosValoresProgramacaoDiariaEntity valGesubgsub(Double valGesubgsub) {
        this.setValGesubgsub(valGesubgsub);
        return this;
    }

    public void setValGesubgsub(Double valGesubgsub) {
        this.valGesubgsub = valGesubgsub;
    }

    public Double getValExportacao() {
        return this.valExportacao;
    }

    public OnsDadosValoresProgramacaoDiariaEntity valExportacao(Double valExportacao) {
        this.setValExportacao(valExportacao);
        return this;
    }

    public void setValExportacao(Double valExportacao) {
        this.valExportacao = valExportacao;
    }

    public Double getValReposicaoexportacao() {
        return this.valReposicaoexportacao;
    }

    public OnsDadosValoresProgramacaoDiariaEntity valReposicaoexportacao(Double valReposicaoexportacao) {
        this.setValReposicaoexportacao(valReposicaoexportacao);
        return this;
    }

    public void setValReposicaoexportacao(Double valReposicaoexportacao) {
        this.valReposicaoexportacao = valReposicaoexportacao;
    }

    public Double getValFaltacombustivel() {
        return this.valFaltacombustivel;
    }

    public OnsDadosValoresProgramacaoDiariaEntity valFaltacombustivel(Double valFaltacombustivel) {
        this.setValFaltacombustivel(valFaltacombustivel);
        return this;
    }

    public void setValFaltacombustivel(Double valFaltacombustivel) {
        this.valFaltacombustivel = valFaltacombustivel;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosValoresProgramacaoDiariaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosValoresProgramacaoDiariaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosValoresProgramacaoDiariaEntity{" +
            "id=" + getId() +
            ", dinProgramacaodia='" + getDinProgramacaodia() + "'" +
            ", numPatamar=" + getNumPatamar() +
            ", codExibicaousina='" + getCodExibicaousina() + "'" +
            ", nomUsina='" + getNomUsina() + "'" +
            ", tipGeracao='" + getTipGeracao() + "'" +
            ", nomModalidadeoperacao='" + getNomModalidadeoperacao() + "'" +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", idEstado='" + getIdEstado() + "'" +
            ", nomEstado='" + getNomEstado() + "'" +
            ", valGeracaoprogramada=" + getValGeracaoprogramada() +
            ", valDisponibilidade=" + getValDisponibilidade() +
            ", valOrdemmerito=" + getValOrdemmerito() +
            ", valInflexibilidade=" + getValInflexibilidade() +
            ", valUc=" + getValUc() +
            ", valRazaoeletrica=" + getValRazaoeletrica() +
            ", valGeracaoenergetica=" + getValGeracaoenergetica() +
            ", valGesubgsub=" + getValGesubgsub() +
            ", valExportacao=" + getValExportacao() +
            ", valReposicaoexportacao=" + getValReposicaoexportacao() +
            ", valFaltacombustivel=" + getValFaltacombustivel() +
            "}";
    }
}
