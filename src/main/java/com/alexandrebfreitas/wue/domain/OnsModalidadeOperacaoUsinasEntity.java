package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsModalidadeOperacaoUsinasEntity.
 */
@Entity
@Table(name = "OnsTable10")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsmodalidadeoperacaousinas")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsModalidadeOperacaoUsinasEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsina;

    @Column(name = "ceg")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String ceg;

    @Column(name = "nom_modalidadeoperacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomModalidadeoperacao;

    @Column(name = "val_potenciaautorizada")
    private Double valPotenciaautorizada;

    @Column(name = "sgl_centrooperacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String sglCentrooperacao;

    @Column(name = "nom_pontoconexao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomPontoconexao;

    @Column(name = "id_estado")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idEstado;

    @Column(name = "nom_estado")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomEstado;

    @Column(name = "sts_aneel")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String stsAneel;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsModalidadeOperacaoUsinasEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsModalidadeOperacaoUsinasEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public String getCeg() {
        return this.ceg;
    }

    public OnsModalidadeOperacaoUsinasEntity ceg(String ceg) {
        this.setCeg(ceg);
        return this;
    }

    public void setCeg(String ceg) {
        this.ceg = ceg;
    }

    public String getNomModalidadeoperacao() {
        return this.nomModalidadeoperacao;
    }

    public OnsModalidadeOperacaoUsinasEntity nomModalidadeoperacao(String nomModalidadeoperacao) {
        this.setNomModalidadeoperacao(nomModalidadeoperacao);
        return this;
    }

    public void setNomModalidadeoperacao(String nomModalidadeoperacao) {
        this.nomModalidadeoperacao = nomModalidadeoperacao;
    }

    public Double getValPotenciaautorizada() {
        return this.valPotenciaautorizada;
    }

    public OnsModalidadeOperacaoUsinasEntity valPotenciaautorizada(Double valPotenciaautorizada) {
        this.setValPotenciaautorizada(valPotenciaautorizada);
        return this;
    }

    public void setValPotenciaautorizada(Double valPotenciaautorizada) {
        this.valPotenciaautorizada = valPotenciaautorizada;
    }

    public String getSglCentrooperacao() {
        return this.sglCentrooperacao;
    }

    public OnsModalidadeOperacaoUsinasEntity sglCentrooperacao(String sglCentrooperacao) {
        this.setSglCentrooperacao(sglCentrooperacao);
        return this;
    }

    public void setSglCentrooperacao(String sglCentrooperacao) {
        this.sglCentrooperacao = sglCentrooperacao;
    }

    public String getNomPontoconexao() {
        return this.nomPontoconexao;
    }

    public OnsModalidadeOperacaoUsinasEntity nomPontoconexao(String nomPontoconexao) {
        this.setNomPontoconexao(nomPontoconexao);
        return this;
    }

    public void setNomPontoconexao(String nomPontoconexao) {
        this.nomPontoconexao = nomPontoconexao;
    }

    public String getIdEstado() {
        return this.idEstado;
    }

    public OnsModalidadeOperacaoUsinasEntity idEstado(String idEstado) {
        this.setIdEstado(idEstado);
        return this;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public OnsModalidadeOperacaoUsinasEntity nomEstado(String nomEstado) {
        this.setNomEstado(nomEstado);
        return this;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public String getStsAneel() {
        return this.stsAneel;
    }

    public OnsModalidadeOperacaoUsinasEntity stsAneel(String stsAneel) {
        this.setStsAneel(stsAneel);
        return this;
    }

    public void setStsAneel(String stsAneel) {
        this.stsAneel = stsAneel;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsModalidadeOperacaoUsinasEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsModalidadeOperacaoUsinasEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsModalidadeOperacaoUsinasEntity{" +
            "id=" + getId() +
            ", nomUsina='" + getNomUsina() + "'" +
            ", ceg='" + getCeg() + "'" +
            ", nomModalidadeoperacao='" + getNomModalidadeoperacao() + "'" +
            ", valPotenciaautorizada=" + getValPotenciaautorizada() +
            ", sglCentrooperacao='" + getSglCentrooperacao() + "'" +
            ", nomPontoconexao='" + getNomPontoconexao() + "'" +
            ", idEstado='" + getIdEstado() + "'" +
            ", nomEstado='" + getNomEstado() + "'" +
            ", stsAneel='" + getStsAneel() + "'" +
            "}";
    }
}
