package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.
 */
@Entity
@Table(name = "OnsTable61")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadosrelacionamentoentregrupospequenasusinaseusinas")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity implements Serializable {

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

    @Column(name = "estad_id")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String estadId;

    @Column(name = "nom_estado")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomEstado;

    @Column(name = "id_tipousina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idTipousina;

    @Column(name = "id_ons_pequenasusinas")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idOnsPequenasusinas;

    @Column(name = "id_ons_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idOnsUsina;

    @Column(name = "nom_pequenasusinas")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomPequenasusinas;

    @Column(name = "nom_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsina;

    @Column(name = "ceg")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String ceg;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getEstadId() {
        return this.estadId;
    }

    public OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity estadId(String estadId) {
        this.setEstadId(estadId);
        return this;
    }

    public void setEstadId(String estadId) {
        this.estadId = estadId;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity nomEstado(String nomEstado) {
        this.setNomEstado(nomEstado);
        return this;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public String getIdTipousina() {
        return this.idTipousina;
    }

    public OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity idTipousina(String idTipousina) {
        this.setIdTipousina(idTipousina);
        return this;
    }

    public void setIdTipousina(String idTipousina) {
        this.idTipousina = idTipousina;
    }

    public String getIdOnsPequenasusinas() {
        return this.idOnsPequenasusinas;
    }

    public OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity idOnsPequenasusinas(String idOnsPequenasusinas) {
        this.setIdOnsPequenasusinas(idOnsPequenasusinas);
        return this;
    }

    public void setIdOnsPequenasusinas(String idOnsPequenasusinas) {
        this.idOnsPequenasusinas = idOnsPequenasusinas;
    }

    public String getIdOnsUsina() {
        return this.idOnsUsina;
    }

    public OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity idOnsUsina(String idOnsUsina) {
        this.setIdOnsUsina(idOnsUsina);
        return this;
    }

    public void setIdOnsUsina(String idOnsUsina) {
        this.idOnsUsina = idOnsUsina;
    }

    public String getNomPequenasusinas() {
        return this.nomPequenasusinas;
    }

    public OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity nomPequenasusinas(String nomPequenasusinas) {
        this.setNomPequenasusinas(nomPequenasusinas);
        return this;
    }

    public void setNomPequenasusinas(String nomPequenasusinas) {
        this.nomPequenasusinas = nomPequenasusinas;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public String getCeg() {
        return this.ceg;
    }

    public OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity ceg(String ceg) {
        this.setCeg(ceg);
        return this;
    }

    public void setCeg(String ceg) {
        this.ceg = ceg;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", estadId='" + getEstadId() + "'" +
            ", nomEstado='" + getNomEstado() + "'" +
            ", idTipousina='" + getIdTipousina() + "'" +
            ", idOnsPequenasusinas='" + getIdOnsPequenasusinas() + "'" +
            ", idOnsUsina='" + getIdOnsUsina() + "'" +
            ", nomPequenasusinas='" + getNomPequenasusinas() + "'" +
            ", nomUsina='" + getNomUsina() + "'" +
            ", ceg='" + getCeg() + "'" +
            "}";
    }
}
