package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsGeracaoUsinaEmBaseHorariaEntity.
 */
@Entity
@Table(name = "OnsTable52")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsgeracaousinaembasehoraria")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsGeracaoUsinaEmBaseHorariaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

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

    @Column(name = "cod_modalidadeoperacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codModalidadeoperacao;

    @Column(name = "nom_tipousina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTipousina;

    @Column(name = "nom_tipocombustivel")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTipocombustivel;

    @Column(name = "nom_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsina;

    @Column(name = "id_ons")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idOns;

    @Column(name = "ceg")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String ceg;

    @Column(name = "val_geracao")
    private Double valGeracao;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getIdEstado() {
        return this.idEstado;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity idEstado(String idEstado) {
        this.setIdEstado(idEstado);
        return this;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity nomEstado(String nomEstado) {
        this.setNomEstado(nomEstado);
        return this;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public String getCodModalidadeoperacao() {
        return this.codModalidadeoperacao;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity codModalidadeoperacao(String codModalidadeoperacao) {
        this.setCodModalidadeoperacao(codModalidadeoperacao);
        return this;
    }

    public void setCodModalidadeoperacao(String codModalidadeoperacao) {
        this.codModalidadeoperacao = codModalidadeoperacao;
    }

    public String getNomTipousina() {
        return this.nomTipousina;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity nomTipousina(String nomTipousina) {
        this.setNomTipousina(nomTipousina);
        return this;
    }

    public void setNomTipousina(String nomTipousina) {
        this.nomTipousina = nomTipousina;
    }

    public String getNomTipocombustivel() {
        return this.nomTipocombustivel;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity nomTipocombustivel(String nomTipocombustivel) {
        this.setNomTipocombustivel(nomTipocombustivel);
        return this;
    }

    public void setNomTipocombustivel(String nomTipocombustivel) {
        this.nomTipocombustivel = nomTipocombustivel;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public String getIdOns() {
        return this.idOns;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity idOns(String idOns) {
        this.setIdOns(idOns);
        return this;
    }

    public void setIdOns(String idOns) {
        this.idOns = idOns;
    }

    public String getCeg() {
        return this.ceg;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity ceg(String ceg) {
        this.setCeg(ceg);
        return this;
    }

    public void setCeg(String ceg) {
        this.ceg = ceg;
    }

    public Double getValGeracao() {
        return this.valGeracao;
    }

    public OnsGeracaoUsinaEmBaseHorariaEntity valGeracao(Double valGeracao) {
        this.setValGeracao(valGeracao);
        return this;
    }

    public void setValGeracao(Double valGeracao) {
        this.valGeracao = valGeracao;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsGeracaoUsinaEmBaseHorariaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsGeracaoUsinaEmBaseHorariaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsGeracaoUsinaEmBaseHorariaEntity{" +
            "id=" + getId() +
            ", dinInstante='" + getDinInstante() + "'" +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", idEstado='" + getIdEstado() + "'" +
            ", nomEstado='" + getNomEstado() + "'" +
            ", codModalidadeoperacao='" + getCodModalidadeoperacao() + "'" +
            ", nomTipousina='" + getNomTipousina() + "'" +
            ", nomTipocombustivel='" + getNomTipocombustivel() + "'" +
            ", nomUsina='" + getNomUsina() + "'" +
            ", idOns='" + getIdOns() + "'" +
            ", ceg='" + getCeg() + "'" +
            ", valGeracao=" + getValGeracao() +
            "}";
    }
}
