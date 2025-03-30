package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosRelacionamentoEntreConjuntosEUsinasEntity.
 */
@Entity
@Table(name = "OnsTable19")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadosrelacionamentoentreconjuntoseusinas")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosRelacionamentoEntreConjuntosEUsinasEntity implements Serializable {

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

    @Column(name = "id_conjuntousina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer idConjuntousina;

    @Column(name = "id_ons_conjunto")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idOnsConjunto;

    @Column(name = "id_ons_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idOnsUsina;

    @Column(name = "nom_conjunto")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomConjunto;

    @Column(name = "nom_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsina;

    @Column(name = "ceg")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String ceg;

    @Column(name = "dat_iniciorelacionamento")
    private LocalDate datIniciorelacionamento;

    @Column(name = "dat_fimrelacionamento")
    private LocalDate datFimrelacionamento;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getEstadId() {
        return this.estadId;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity estadId(String estadId) {
        this.setEstadId(estadId);
        return this;
    }

    public void setEstadId(String estadId) {
        this.estadId = estadId;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity nomEstado(String nomEstado) {
        this.setNomEstado(nomEstado);
        return this;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public String getIdTipousina() {
        return this.idTipousina;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity idTipousina(String idTipousina) {
        this.setIdTipousina(idTipousina);
        return this;
    }

    public void setIdTipousina(String idTipousina) {
        this.idTipousina = idTipousina;
    }

    public Integer getIdConjuntousina() {
        return this.idConjuntousina;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity idConjuntousina(Integer idConjuntousina) {
        this.setIdConjuntousina(idConjuntousina);
        return this;
    }

    public void setIdConjuntousina(Integer idConjuntousina) {
        this.idConjuntousina = idConjuntousina;
    }

    public String getIdOnsConjunto() {
        return this.idOnsConjunto;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity idOnsConjunto(String idOnsConjunto) {
        this.setIdOnsConjunto(idOnsConjunto);
        return this;
    }

    public void setIdOnsConjunto(String idOnsConjunto) {
        this.idOnsConjunto = idOnsConjunto;
    }

    public String getIdOnsUsina() {
        return this.idOnsUsina;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity idOnsUsina(String idOnsUsina) {
        this.setIdOnsUsina(idOnsUsina);
        return this;
    }

    public void setIdOnsUsina(String idOnsUsina) {
        this.idOnsUsina = idOnsUsina;
    }

    public String getNomConjunto() {
        return this.nomConjunto;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity nomConjunto(String nomConjunto) {
        this.setNomConjunto(nomConjunto);
        return this;
    }

    public void setNomConjunto(String nomConjunto) {
        this.nomConjunto = nomConjunto;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public String getCeg() {
        return this.ceg;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity ceg(String ceg) {
        this.setCeg(ceg);
        return this;
    }

    public void setCeg(String ceg) {
        this.ceg = ceg;
    }

    public LocalDate getDatIniciorelacionamento() {
        return this.datIniciorelacionamento;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity datIniciorelacionamento(LocalDate datIniciorelacionamento) {
        this.setDatIniciorelacionamento(datIniciorelacionamento);
        return this;
    }

    public void setDatIniciorelacionamento(LocalDate datIniciorelacionamento) {
        this.datIniciorelacionamento = datIniciorelacionamento;
    }

    public LocalDate getDatFimrelacionamento() {
        return this.datFimrelacionamento;
    }

    public OnsDadosRelacionamentoEntreConjuntosEUsinasEntity datFimrelacionamento(LocalDate datFimrelacionamento) {
        this.setDatFimrelacionamento(datFimrelacionamento);
        return this;
    }

    public void setDatFimrelacionamento(LocalDate datFimrelacionamento) {
        this.datFimrelacionamento = datFimrelacionamento;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosRelacionamentoEntreConjuntosEUsinasEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosRelacionamentoEntreConjuntosEUsinasEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosRelacionamentoEntreConjuntosEUsinasEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", estadId='" + getEstadId() + "'" +
            ", nomEstado='" + getNomEstado() + "'" +
            ", idTipousina='" + getIdTipousina() + "'" +
            ", idConjuntousina=" + getIdConjuntousina() +
            ", idOnsConjunto='" + getIdOnsConjunto() + "'" +
            ", idOnsUsina='" + getIdOnsUsina() + "'" +
            ", nomConjunto='" + getNomConjunto() + "'" +
            ", nomUsina='" + getNomUsina() + "'" +
            ", ceg='" + getCeg() + "'" +
            ", datIniciorelacionamento='" + getDatIniciorelacionamento() + "'" +
            ", datFimrelacionamento='" + getDatFimrelacionamento() + "'" +
            "}";
    }
}
