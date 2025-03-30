package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosDisponibilidadeUsinasEntity.
 */
@Entity
@Table(name = "OnsTable13")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadosdisponibilidadeusinas")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosDisponibilidadeUsinasEntity implements Serializable {

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

    @Column(name = "id_tipousina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idTipousina;

    @Column(name = "nom_tipocombustivel")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTipocombustivel;

    @Column(name = "id_ons")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idOns;

    @Column(name = "ceg")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String ceg;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    @Column(name = "val_potenciainstalada")
    private Double valPotenciainstalada;

    @Column(name = "val_dispoperacional")
    private Double valDispoperacional;

    @Column(name = "val_dispsincronizada")
    private Double valDispsincronizada;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosDisponibilidadeUsinasEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsDadosDisponibilidadeUsinasEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsDadosDisponibilidadeUsinasEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getIdEstado() {
        return this.idEstado;
    }

    public OnsDadosDisponibilidadeUsinasEntity idEstado(String idEstado) {
        this.setIdEstado(idEstado);
        return this;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public OnsDadosDisponibilidadeUsinasEntity nomEstado(String nomEstado) {
        this.setNomEstado(nomEstado);
        return this;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsDadosDisponibilidadeUsinasEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public String getIdTipousina() {
        return this.idTipousina;
    }

    public OnsDadosDisponibilidadeUsinasEntity idTipousina(String idTipousina) {
        this.setIdTipousina(idTipousina);
        return this;
    }

    public void setIdTipousina(String idTipousina) {
        this.idTipousina = idTipousina;
    }

    public String getNomTipocombustivel() {
        return this.nomTipocombustivel;
    }

    public OnsDadosDisponibilidadeUsinasEntity nomTipocombustivel(String nomTipocombustivel) {
        this.setNomTipocombustivel(nomTipocombustivel);
        return this;
    }

    public void setNomTipocombustivel(String nomTipocombustivel) {
        this.nomTipocombustivel = nomTipocombustivel;
    }

    public String getIdOns() {
        return this.idOns;
    }

    public OnsDadosDisponibilidadeUsinasEntity idOns(String idOns) {
        this.setIdOns(idOns);
        return this;
    }

    public void setIdOns(String idOns) {
        this.idOns = idOns;
    }

    public String getCeg() {
        return this.ceg;
    }

    public OnsDadosDisponibilidadeUsinasEntity ceg(String ceg) {
        this.setCeg(ceg);
        return this;
    }

    public void setCeg(String ceg) {
        this.ceg = ceg;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsDadosDisponibilidadeUsinasEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public Double getValPotenciainstalada() {
        return this.valPotenciainstalada;
    }

    public OnsDadosDisponibilidadeUsinasEntity valPotenciainstalada(Double valPotenciainstalada) {
        this.setValPotenciainstalada(valPotenciainstalada);
        return this;
    }

    public void setValPotenciainstalada(Double valPotenciainstalada) {
        this.valPotenciainstalada = valPotenciainstalada;
    }

    public Double getValDispoperacional() {
        return this.valDispoperacional;
    }

    public OnsDadosDisponibilidadeUsinasEntity valDispoperacional(Double valDispoperacional) {
        this.setValDispoperacional(valDispoperacional);
        return this;
    }

    public void setValDispoperacional(Double valDispoperacional) {
        this.valDispoperacional = valDispoperacional;
    }

    public Double getValDispsincronizada() {
        return this.valDispsincronizada;
    }

    public OnsDadosDisponibilidadeUsinasEntity valDispsincronizada(Double valDispsincronizada) {
        this.setValDispsincronizada(valDispsincronizada);
        return this;
    }

    public void setValDispsincronizada(Double valDispsincronizada) {
        this.valDispsincronizada = valDispsincronizada;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosDisponibilidadeUsinasEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosDisponibilidadeUsinasEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosDisponibilidadeUsinasEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", idEstado='" + getIdEstado() + "'" +
            ", nomEstado='" + getNomEstado() + "'" +
            ", nomUsina='" + getNomUsina() + "'" +
            ", idTipousina='" + getIdTipousina() + "'" +
            ", nomTipocombustivel='" + getNomTipocombustivel() + "'" +
            ", idOns='" + getIdOns() + "'" +
            ", ceg='" + getCeg() + "'" +
            ", dinInstante='" + getDinInstante() + "'" +
            ", valPotenciainstalada=" + getValPotenciainstalada() +
            ", valDispoperacional=" + getValDispoperacional() +
            ", valDispsincronizada=" + getValDispsincronizada() +
            "}";
    }
}
