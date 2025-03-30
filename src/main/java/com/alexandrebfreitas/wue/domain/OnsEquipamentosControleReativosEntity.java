package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsEquipamentosControleReativosEntity.
 */
@Entity
@Table(name = "OnsTable55")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsequipamentoscontrolereativos")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsEquipamentosControleReativosEntity implements Serializable {

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

    @Column(name = "nom_subestacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubestacao;

    @Column(name = "nom_agente_proprietario")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomAgenteProprietario;

    @Column(name = "nom_tipoderede")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTipoderede;

    @Column(name = "nom_tipoequipamento")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTipoequipamento;

    @Column(name = "nom_equipamento")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomEquipamento;

    @Column(name = "val_potreativanominal_mvar")
    private Double valPotreativanominalMvar;

    @Column(name = "val_limiteinferior_mvar")
    private Double valLimiteinferiorMvar;

    @Column(name = "val_limitesuperior_mvar")
    private Double valLimitesuperiorMvar;

    @Column(name = "dat_entradaoperacao")
    private LocalDate datEntradaoperacao;

    @Column(name = "dat_desativacao")
    private LocalDate datDesativacao;

    @Column(name = "cod_equipamento")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codEquipamento;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsEquipamentosControleReativosEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsEquipamentosControleReativosEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsEquipamentosControleReativosEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getIdEstado() {
        return this.idEstado;
    }

    public OnsEquipamentosControleReativosEntity idEstado(String idEstado) {
        this.setIdEstado(idEstado);
        return this;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public OnsEquipamentosControleReativosEntity nomEstado(String nomEstado) {
        this.setNomEstado(nomEstado);
        return this;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public String getNomSubestacao() {
        return this.nomSubestacao;
    }

    public OnsEquipamentosControleReativosEntity nomSubestacao(String nomSubestacao) {
        this.setNomSubestacao(nomSubestacao);
        return this;
    }

    public void setNomSubestacao(String nomSubestacao) {
        this.nomSubestacao = nomSubestacao;
    }

    public String getNomAgenteProprietario() {
        return this.nomAgenteProprietario;
    }

    public OnsEquipamentosControleReativosEntity nomAgenteProprietario(String nomAgenteProprietario) {
        this.setNomAgenteProprietario(nomAgenteProprietario);
        return this;
    }

    public void setNomAgenteProprietario(String nomAgenteProprietario) {
        this.nomAgenteProprietario = nomAgenteProprietario;
    }

    public String getNomTipoderede() {
        return this.nomTipoderede;
    }

    public OnsEquipamentosControleReativosEntity nomTipoderede(String nomTipoderede) {
        this.setNomTipoderede(nomTipoderede);
        return this;
    }

    public void setNomTipoderede(String nomTipoderede) {
        this.nomTipoderede = nomTipoderede;
    }

    public String getNomTipoequipamento() {
        return this.nomTipoequipamento;
    }

    public OnsEquipamentosControleReativosEntity nomTipoequipamento(String nomTipoequipamento) {
        this.setNomTipoequipamento(nomTipoequipamento);
        return this;
    }

    public void setNomTipoequipamento(String nomTipoequipamento) {
        this.nomTipoequipamento = nomTipoequipamento;
    }

    public String getNomEquipamento() {
        return this.nomEquipamento;
    }

    public OnsEquipamentosControleReativosEntity nomEquipamento(String nomEquipamento) {
        this.setNomEquipamento(nomEquipamento);
        return this;
    }

    public void setNomEquipamento(String nomEquipamento) {
        this.nomEquipamento = nomEquipamento;
    }

    public Double getValPotreativanominalMvar() {
        return this.valPotreativanominalMvar;
    }

    public OnsEquipamentosControleReativosEntity valPotreativanominalMvar(Double valPotreativanominalMvar) {
        this.setValPotreativanominalMvar(valPotreativanominalMvar);
        return this;
    }

    public void setValPotreativanominalMvar(Double valPotreativanominalMvar) {
        this.valPotreativanominalMvar = valPotreativanominalMvar;
    }

    public Double getValLimiteinferiorMvar() {
        return this.valLimiteinferiorMvar;
    }

    public OnsEquipamentosControleReativosEntity valLimiteinferiorMvar(Double valLimiteinferiorMvar) {
        this.setValLimiteinferiorMvar(valLimiteinferiorMvar);
        return this;
    }

    public void setValLimiteinferiorMvar(Double valLimiteinferiorMvar) {
        this.valLimiteinferiorMvar = valLimiteinferiorMvar;
    }

    public Double getValLimitesuperiorMvar() {
        return this.valLimitesuperiorMvar;
    }

    public OnsEquipamentosControleReativosEntity valLimitesuperiorMvar(Double valLimitesuperiorMvar) {
        this.setValLimitesuperiorMvar(valLimitesuperiorMvar);
        return this;
    }

    public void setValLimitesuperiorMvar(Double valLimitesuperiorMvar) {
        this.valLimitesuperiorMvar = valLimitesuperiorMvar;
    }

    public LocalDate getDatEntradaoperacao() {
        return this.datEntradaoperacao;
    }

    public OnsEquipamentosControleReativosEntity datEntradaoperacao(LocalDate datEntradaoperacao) {
        this.setDatEntradaoperacao(datEntradaoperacao);
        return this;
    }

    public void setDatEntradaoperacao(LocalDate datEntradaoperacao) {
        this.datEntradaoperacao = datEntradaoperacao;
    }

    public LocalDate getDatDesativacao() {
        return this.datDesativacao;
    }

    public OnsEquipamentosControleReativosEntity datDesativacao(LocalDate datDesativacao) {
        this.setDatDesativacao(datDesativacao);
        return this;
    }

    public void setDatDesativacao(LocalDate datDesativacao) {
        this.datDesativacao = datDesativacao;
    }

    public String getCodEquipamento() {
        return this.codEquipamento;
    }

    public OnsEquipamentosControleReativosEntity codEquipamento(String codEquipamento) {
        this.setCodEquipamento(codEquipamento);
        return this;
    }

    public void setCodEquipamento(String codEquipamento) {
        this.codEquipamento = codEquipamento;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsEquipamentosControleReativosEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsEquipamentosControleReativosEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsEquipamentosControleReativosEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", idEstado='" + getIdEstado() + "'" +
            ", nomEstado='" + getNomEstado() + "'" +
            ", nomSubestacao='" + getNomSubestacao() + "'" +
            ", nomAgenteProprietario='" + getNomAgenteProprietario() + "'" +
            ", nomTipoderede='" + getNomTipoderede() + "'" +
            ", nomTipoequipamento='" + getNomTipoequipamento() + "'" +
            ", nomEquipamento='" + getNomEquipamento() + "'" +
            ", valPotreativanominalMvar=" + getValPotreativanominalMvar() +
            ", valLimiteinferiorMvar=" + getValLimiteinferiorMvar() +
            ", valLimitesuperiorMvar=" + getValLimitesuperiorMvar() +
            ", datEntradaoperacao='" + getDatEntradaoperacao() + "'" +
            ", datDesativacao='" + getDatDesativacao() + "'" +
            ", codEquipamento='" + getCodEquipamento() + "'" +
            "}";
    }
}
