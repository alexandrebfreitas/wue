package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsCapacidadeInstaladaGeracaoEntity.
 */
@Entity
@Table(name = "OnsTable62")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onscapacidadeinstaladageracao")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsCapacidadeInstaladaGeracaoEntity implements Serializable {

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

    @Column(name = "nom_modalidadeoperacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomModalidadeoperacao;

    @Column(name = "nom_agenteproprietario")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomAgenteproprietario;

    @Column(name = "nom_tipousina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTipousina;

    @Column(name = "nom_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsina;

    @Column(name = "ceg")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String ceg;

    @Column(name = "nom_unidadegeradora")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUnidadegeradora;

    @Column(name = "cod_equipamento")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codEquipamento;

    @Column(name = "num_unidadegeradora")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String numUnidadegeradora;

    @Column(name = "nom_combustivel")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomCombustivel;

    @Column(name = "dat_entradateste")
    private LocalDate datEntradateste;

    @Column(name = "dat_entradaoperacao")
    private LocalDate datEntradaoperacao;

    @Column(name = "dat_desativacao")
    private LocalDate datDesativacao;

    @Column(name = "val_potenciaefetiva")
    private Double valPotenciaefetiva;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsCapacidadeInstaladaGeracaoEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsCapacidadeInstaladaGeracaoEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsCapacidadeInstaladaGeracaoEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getIdEstado() {
        return this.idEstado;
    }

    public OnsCapacidadeInstaladaGeracaoEntity idEstado(String idEstado) {
        this.setIdEstado(idEstado);
        return this;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public OnsCapacidadeInstaladaGeracaoEntity nomEstado(String nomEstado) {
        this.setNomEstado(nomEstado);
        return this;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public String getNomModalidadeoperacao() {
        return this.nomModalidadeoperacao;
    }

    public OnsCapacidadeInstaladaGeracaoEntity nomModalidadeoperacao(String nomModalidadeoperacao) {
        this.setNomModalidadeoperacao(nomModalidadeoperacao);
        return this;
    }

    public void setNomModalidadeoperacao(String nomModalidadeoperacao) {
        this.nomModalidadeoperacao = nomModalidadeoperacao;
    }

    public String getNomAgenteproprietario() {
        return this.nomAgenteproprietario;
    }

    public OnsCapacidadeInstaladaGeracaoEntity nomAgenteproprietario(String nomAgenteproprietario) {
        this.setNomAgenteproprietario(nomAgenteproprietario);
        return this;
    }

    public void setNomAgenteproprietario(String nomAgenteproprietario) {
        this.nomAgenteproprietario = nomAgenteproprietario;
    }

    public String getNomTipousina() {
        return this.nomTipousina;
    }

    public OnsCapacidadeInstaladaGeracaoEntity nomTipousina(String nomTipousina) {
        this.setNomTipousina(nomTipousina);
        return this;
    }

    public void setNomTipousina(String nomTipousina) {
        this.nomTipousina = nomTipousina;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsCapacidadeInstaladaGeracaoEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public String getCeg() {
        return this.ceg;
    }

    public OnsCapacidadeInstaladaGeracaoEntity ceg(String ceg) {
        this.setCeg(ceg);
        return this;
    }

    public void setCeg(String ceg) {
        this.ceg = ceg;
    }

    public String getNomUnidadegeradora() {
        return this.nomUnidadegeradora;
    }

    public OnsCapacidadeInstaladaGeracaoEntity nomUnidadegeradora(String nomUnidadegeradora) {
        this.setNomUnidadegeradora(nomUnidadegeradora);
        return this;
    }

    public void setNomUnidadegeradora(String nomUnidadegeradora) {
        this.nomUnidadegeradora = nomUnidadegeradora;
    }

    public String getCodEquipamento() {
        return this.codEquipamento;
    }

    public OnsCapacidadeInstaladaGeracaoEntity codEquipamento(String codEquipamento) {
        this.setCodEquipamento(codEquipamento);
        return this;
    }

    public void setCodEquipamento(String codEquipamento) {
        this.codEquipamento = codEquipamento;
    }

    public String getNumUnidadegeradora() {
        return this.numUnidadegeradora;
    }

    public OnsCapacidadeInstaladaGeracaoEntity numUnidadegeradora(String numUnidadegeradora) {
        this.setNumUnidadegeradora(numUnidadegeradora);
        return this;
    }

    public void setNumUnidadegeradora(String numUnidadegeradora) {
        this.numUnidadegeradora = numUnidadegeradora;
    }

    public String getNomCombustivel() {
        return this.nomCombustivel;
    }

    public OnsCapacidadeInstaladaGeracaoEntity nomCombustivel(String nomCombustivel) {
        this.setNomCombustivel(nomCombustivel);
        return this;
    }

    public void setNomCombustivel(String nomCombustivel) {
        this.nomCombustivel = nomCombustivel;
    }

    public LocalDate getDatEntradateste() {
        return this.datEntradateste;
    }

    public OnsCapacidadeInstaladaGeracaoEntity datEntradateste(LocalDate datEntradateste) {
        this.setDatEntradateste(datEntradateste);
        return this;
    }

    public void setDatEntradateste(LocalDate datEntradateste) {
        this.datEntradateste = datEntradateste;
    }

    public LocalDate getDatEntradaoperacao() {
        return this.datEntradaoperacao;
    }

    public OnsCapacidadeInstaladaGeracaoEntity datEntradaoperacao(LocalDate datEntradaoperacao) {
        this.setDatEntradaoperacao(datEntradaoperacao);
        return this;
    }

    public void setDatEntradaoperacao(LocalDate datEntradaoperacao) {
        this.datEntradaoperacao = datEntradaoperacao;
    }

    public LocalDate getDatDesativacao() {
        return this.datDesativacao;
    }

    public OnsCapacidadeInstaladaGeracaoEntity datDesativacao(LocalDate datDesativacao) {
        this.setDatDesativacao(datDesativacao);
        return this;
    }

    public void setDatDesativacao(LocalDate datDesativacao) {
        this.datDesativacao = datDesativacao;
    }

    public Double getValPotenciaefetiva() {
        return this.valPotenciaefetiva;
    }

    public OnsCapacidadeInstaladaGeracaoEntity valPotenciaefetiva(Double valPotenciaefetiva) {
        this.setValPotenciaefetiva(valPotenciaefetiva);
        return this;
    }

    public void setValPotenciaefetiva(Double valPotenciaefetiva) {
        this.valPotenciaefetiva = valPotenciaefetiva;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsCapacidadeInstaladaGeracaoEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsCapacidadeInstaladaGeracaoEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsCapacidadeInstaladaGeracaoEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", idEstado='" + getIdEstado() + "'" +
            ", nomEstado='" + getNomEstado() + "'" +
            ", nomModalidadeoperacao='" + getNomModalidadeoperacao() + "'" +
            ", nomAgenteproprietario='" + getNomAgenteproprietario() + "'" +
            ", nomTipousina='" + getNomTipousina() + "'" +
            ", nomUsina='" + getNomUsina() + "'" +
            ", ceg='" + getCeg() + "'" +
            ", nomUnidadegeradora='" + getNomUnidadegeradora() + "'" +
            ", codEquipamento='" + getCodEquipamento() + "'" +
            ", numUnidadegeradora='" + getNumUnidadegeradora() + "'" +
            ", nomCombustivel='" + getNomCombustivel() + "'" +
            ", datEntradateste='" + getDatEntradateste() + "'" +
            ", datEntradaoperacao='" + getDatEntradaoperacao() + "'" +
            ", datDesativacao='" + getDatDesativacao() + "'" +
            ", valPotenciaefetiva=" + getValPotenciaefetiva() +
            "}";
    }
}
