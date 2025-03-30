package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsFatorCapacidadeGeracaoEolicaESolarEntity.
 */
@Entity
@Table(name = "OnsTable9")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsfatorcapacidadegeracaoeolicaesolar")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsFatorCapacidadeGeracaoEolicaESolarEntity implements Serializable {

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

    @Column(name = "cod_pontoconexao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codPontoconexao;

    @Column(name = "nom_pontoconexao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomPontoconexao;

    @Column(name = "nom_localizacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomLocalizacao;

    @Column(name = "val_latitudesecoletora")
    private Double valLatitudesecoletora;

    @Column(name = "val_longitudesecoletora")
    private Double valLongitudesecoletora;

    @Column(name = "val_latitudepontoconexao")
    private Double valLatitudepontoconexao;

    @Column(name = "val_longitudepontoconexao")
    private Double valLongitudepontoconexao;

    @Column(name = "nom_modalidadeoperacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomModalidadeoperacao;

    @Column(name = "nom_tipousina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTipousina;

    @Column(name = "nom_usina_conjunto")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsinaConjunto;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    @Column(name = "id_ons")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idOns;

    @Column(name = "ceg")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String ceg;

    @Column(name = "val_geracaoprogramada")
    private Double valGeracaoprogramada;

    @Column(name = "val_geracaoverificada")
    private Double valGeracaoverificada;

    @Column(name = "val_capacidadeinstalada")
    private Double valCapacidadeinstalada;

    @Column(name = "val_fatorcapacidade")
    private Double valFatorcapacidade;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getIdEstado() {
        return this.idEstado;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity idEstado(String idEstado) {
        this.setIdEstado(idEstado);
        return this;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity nomEstado(String nomEstado) {
        this.setNomEstado(nomEstado);
        return this;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public String getCodPontoconexao() {
        return this.codPontoconexao;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity codPontoconexao(String codPontoconexao) {
        this.setCodPontoconexao(codPontoconexao);
        return this;
    }

    public void setCodPontoconexao(String codPontoconexao) {
        this.codPontoconexao = codPontoconexao;
    }

    public String getNomPontoconexao() {
        return this.nomPontoconexao;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity nomPontoconexao(String nomPontoconexao) {
        this.setNomPontoconexao(nomPontoconexao);
        return this;
    }

    public void setNomPontoconexao(String nomPontoconexao) {
        this.nomPontoconexao = nomPontoconexao;
    }

    public String getNomLocalizacao() {
        return this.nomLocalizacao;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity nomLocalizacao(String nomLocalizacao) {
        this.setNomLocalizacao(nomLocalizacao);
        return this;
    }

    public void setNomLocalizacao(String nomLocalizacao) {
        this.nomLocalizacao = nomLocalizacao;
    }

    public Double getValLatitudesecoletora() {
        return this.valLatitudesecoletora;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity valLatitudesecoletora(Double valLatitudesecoletora) {
        this.setValLatitudesecoletora(valLatitudesecoletora);
        return this;
    }

    public void setValLatitudesecoletora(Double valLatitudesecoletora) {
        this.valLatitudesecoletora = valLatitudesecoletora;
    }

    public Double getValLongitudesecoletora() {
        return this.valLongitudesecoletora;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity valLongitudesecoletora(Double valLongitudesecoletora) {
        this.setValLongitudesecoletora(valLongitudesecoletora);
        return this;
    }

    public void setValLongitudesecoletora(Double valLongitudesecoletora) {
        this.valLongitudesecoletora = valLongitudesecoletora;
    }

    public Double getValLatitudepontoconexao() {
        return this.valLatitudepontoconexao;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity valLatitudepontoconexao(Double valLatitudepontoconexao) {
        this.setValLatitudepontoconexao(valLatitudepontoconexao);
        return this;
    }

    public void setValLatitudepontoconexao(Double valLatitudepontoconexao) {
        this.valLatitudepontoconexao = valLatitudepontoconexao;
    }

    public Double getValLongitudepontoconexao() {
        return this.valLongitudepontoconexao;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity valLongitudepontoconexao(Double valLongitudepontoconexao) {
        this.setValLongitudepontoconexao(valLongitudepontoconexao);
        return this;
    }

    public void setValLongitudepontoconexao(Double valLongitudepontoconexao) {
        this.valLongitudepontoconexao = valLongitudepontoconexao;
    }

    public String getNomModalidadeoperacao() {
        return this.nomModalidadeoperacao;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity nomModalidadeoperacao(String nomModalidadeoperacao) {
        this.setNomModalidadeoperacao(nomModalidadeoperacao);
        return this;
    }

    public void setNomModalidadeoperacao(String nomModalidadeoperacao) {
        this.nomModalidadeoperacao = nomModalidadeoperacao;
    }

    public String getNomTipousina() {
        return this.nomTipousina;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity nomTipousina(String nomTipousina) {
        this.setNomTipousina(nomTipousina);
        return this;
    }

    public void setNomTipousina(String nomTipousina) {
        this.nomTipousina = nomTipousina;
    }

    public String getNomUsinaConjunto() {
        return this.nomUsinaConjunto;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity nomUsinaConjunto(String nomUsinaConjunto) {
        this.setNomUsinaConjunto(nomUsinaConjunto);
        return this;
    }

    public void setNomUsinaConjunto(String nomUsinaConjunto) {
        this.nomUsinaConjunto = nomUsinaConjunto;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public String getIdOns() {
        return this.idOns;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity idOns(String idOns) {
        this.setIdOns(idOns);
        return this;
    }

    public void setIdOns(String idOns) {
        this.idOns = idOns;
    }

    public String getCeg() {
        return this.ceg;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity ceg(String ceg) {
        this.setCeg(ceg);
        return this;
    }

    public void setCeg(String ceg) {
        this.ceg = ceg;
    }

    public Double getValGeracaoprogramada() {
        return this.valGeracaoprogramada;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity valGeracaoprogramada(Double valGeracaoprogramada) {
        this.setValGeracaoprogramada(valGeracaoprogramada);
        return this;
    }

    public void setValGeracaoprogramada(Double valGeracaoprogramada) {
        this.valGeracaoprogramada = valGeracaoprogramada;
    }

    public Double getValGeracaoverificada() {
        return this.valGeracaoverificada;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity valGeracaoverificada(Double valGeracaoverificada) {
        this.setValGeracaoverificada(valGeracaoverificada);
        return this;
    }

    public void setValGeracaoverificada(Double valGeracaoverificada) {
        this.valGeracaoverificada = valGeracaoverificada;
    }

    public Double getValCapacidadeinstalada() {
        return this.valCapacidadeinstalada;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity valCapacidadeinstalada(Double valCapacidadeinstalada) {
        this.setValCapacidadeinstalada(valCapacidadeinstalada);
        return this;
    }

    public void setValCapacidadeinstalada(Double valCapacidadeinstalada) {
        this.valCapacidadeinstalada = valCapacidadeinstalada;
    }

    public Double getValFatorcapacidade() {
        return this.valFatorcapacidade;
    }

    public OnsFatorCapacidadeGeracaoEolicaESolarEntity valFatorcapacidade(Double valFatorcapacidade) {
        this.setValFatorcapacidade(valFatorcapacidade);
        return this;
    }

    public void setValFatorcapacidade(Double valFatorcapacidade) {
        this.valFatorcapacidade = valFatorcapacidade;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsFatorCapacidadeGeracaoEolicaESolarEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsFatorCapacidadeGeracaoEolicaESolarEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsFatorCapacidadeGeracaoEolicaESolarEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", idEstado='" + getIdEstado() + "'" +
            ", nomEstado='" + getNomEstado() + "'" +
            ", codPontoconexao='" + getCodPontoconexao() + "'" +
            ", nomPontoconexao='" + getNomPontoconexao() + "'" +
            ", nomLocalizacao='" + getNomLocalizacao() + "'" +
            ", valLatitudesecoletora=" + getValLatitudesecoletora() +
            ", valLongitudesecoletora=" + getValLongitudesecoletora() +
            ", valLatitudepontoconexao=" + getValLatitudepontoconexao() +
            ", valLongitudepontoconexao=" + getValLongitudepontoconexao() +
            ", nomModalidadeoperacao='" + getNomModalidadeoperacao() + "'" +
            ", nomTipousina='" + getNomTipousina() + "'" +
            ", nomUsinaConjunto='" + getNomUsinaConjunto() + "'" +
            ", dinInstante='" + getDinInstante() + "'" +
            ", idOns='" + getIdOns() + "'" +
            ", ceg='" + getCeg() + "'" +
            ", valGeracaoprogramada=" + getValGeracaoprogramada() +
            ", valGeracaoverificada=" + getValGeracaoverificada() +
            ", valCapacidadeinstalada=" + getValCapacidadeinstalada() +
            ", valFatorcapacidade=" + getValFatorcapacidade() +
            "}";
    }
}
