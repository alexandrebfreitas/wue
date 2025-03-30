package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsCapacidadeTransformacaoRedeBasicaEntity.
 */
@Entity
@Table(name = "OnsTable3")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onscapacidadetransformacaoredebasica")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsCapacidadeTransformacaoRedeBasicaEntity implements Serializable {

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

    @Column(name = "nom_tipotransformador")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTipotransformador;

    @Column(name = "nom_agenteproprietario")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomAgenteproprietario;

    @Column(name = "nom_subestacao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubestacao;

    @Column(name = "nom_transformador")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTransformador;

    @Column(name = "cod_equipamento")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codEquipamento;

    @Column(name = "dat_entradaoperacao")
    private LocalDate datEntradaoperacao;

    @Column(name = "dat_desativacao")
    private LocalDate datDesativacao;

    @Column(name = "val_tensaoprimario_kv")
    private Double valTensaoprimarioKv;

    @Column(name = "val_tensaosecundario_kv")
    private Double valTensaosecundarioKv;

    @Column(name = "val_tensaoterciario_kv")
    private Double valTensaoterciarioKv;

    @Column(name = "val_potencianominal_mva")
    private Double valPotencianominalMva;

    @Column(name = "nom_tipoderede")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTipoderede;

    @Column(name = "num_barra_primario")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numBarraPrimario;

    @Column(name = "num_barra_secundario")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numBarraSecundario;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getIdEstado() {
        return this.idEstado;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity idEstado(String idEstado) {
        this.setIdEstado(idEstado);
        return this;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity nomEstado(String nomEstado) {
        this.setNomEstado(nomEstado);
        return this;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public String getNomTipotransformador() {
        return this.nomTipotransformador;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity nomTipotransformador(String nomTipotransformador) {
        this.setNomTipotransformador(nomTipotransformador);
        return this;
    }

    public void setNomTipotransformador(String nomTipotransformador) {
        this.nomTipotransformador = nomTipotransformador;
    }

    public String getNomAgenteproprietario() {
        return this.nomAgenteproprietario;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity nomAgenteproprietario(String nomAgenteproprietario) {
        this.setNomAgenteproprietario(nomAgenteproprietario);
        return this;
    }

    public void setNomAgenteproprietario(String nomAgenteproprietario) {
        this.nomAgenteproprietario = nomAgenteproprietario;
    }

    public String getNomSubestacao() {
        return this.nomSubestacao;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity nomSubestacao(String nomSubestacao) {
        this.setNomSubestacao(nomSubestacao);
        return this;
    }

    public void setNomSubestacao(String nomSubestacao) {
        this.nomSubestacao = nomSubestacao;
    }

    public String getNomTransformador() {
        return this.nomTransformador;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity nomTransformador(String nomTransformador) {
        this.setNomTransformador(nomTransformador);
        return this;
    }

    public void setNomTransformador(String nomTransformador) {
        this.nomTransformador = nomTransformador;
    }

    public String getCodEquipamento() {
        return this.codEquipamento;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity codEquipamento(String codEquipamento) {
        this.setCodEquipamento(codEquipamento);
        return this;
    }

    public void setCodEquipamento(String codEquipamento) {
        this.codEquipamento = codEquipamento;
    }

    public LocalDate getDatEntradaoperacao() {
        return this.datEntradaoperacao;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity datEntradaoperacao(LocalDate datEntradaoperacao) {
        this.setDatEntradaoperacao(datEntradaoperacao);
        return this;
    }

    public void setDatEntradaoperacao(LocalDate datEntradaoperacao) {
        this.datEntradaoperacao = datEntradaoperacao;
    }

    public LocalDate getDatDesativacao() {
        return this.datDesativacao;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity datDesativacao(LocalDate datDesativacao) {
        this.setDatDesativacao(datDesativacao);
        return this;
    }

    public void setDatDesativacao(LocalDate datDesativacao) {
        this.datDesativacao = datDesativacao;
    }

    public Double getValTensaoprimarioKv() {
        return this.valTensaoprimarioKv;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity valTensaoprimarioKv(Double valTensaoprimarioKv) {
        this.setValTensaoprimarioKv(valTensaoprimarioKv);
        return this;
    }

    public void setValTensaoprimarioKv(Double valTensaoprimarioKv) {
        this.valTensaoprimarioKv = valTensaoprimarioKv;
    }

    public Double getValTensaosecundarioKv() {
        return this.valTensaosecundarioKv;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity valTensaosecundarioKv(Double valTensaosecundarioKv) {
        this.setValTensaosecundarioKv(valTensaosecundarioKv);
        return this;
    }

    public void setValTensaosecundarioKv(Double valTensaosecundarioKv) {
        this.valTensaosecundarioKv = valTensaosecundarioKv;
    }

    public Double getValTensaoterciarioKv() {
        return this.valTensaoterciarioKv;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity valTensaoterciarioKv(Double valTensaoterciarioKv) {
        this.setValTensaoterciarioKv(valTensaoterciarioKv);
        return this;
    }

    public void setValTensaoterciarioKv(Double valTensaoterciarioKv) {
        this.valTensaoterciarioKv = valTensaoterciarioKv;
    }

    public Double getValPotencianominalMva() {
        return this.valPotencianominalMva;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity valPotencianominalMva(Double valPotencianominalMva) {
        this.setValPotencianominalMva(valPotencianominalMva);
        return this;
    }

    public void setValPotencianominalMva(Double valPotencianominalMva) {
        this.valPotencianominalMva = valPotencianominalMva;
    }

    public String getNomTipoderede() {
        return this.nomTipoderede;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity nomTipoderede(String nomTipoderede) {
        this.setNomTipoderede(nomTipoderede);
        return this;
    }

    public void setNomTipoderede(String nomTipoderede) {
        this.nomTipoderede = nomTipoderede;
    }

    public Integer getNumBarraPrimario() {
        return this.numBarraPrimario;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity numBarraPrimario(Integer numBarraPrimario) {
        this.setNumBarraPrimario(numBarraPrimario);
        return this;
    }

    public void setNumBarraPrimario(Integer numBarraPrimario) {
        this.numBarraPrimario = numBarraPrimario;
    }

    public Integer getNumBarraSecundario() {
        return this.numBarraSecundario;
    }

    public OnsCapacidadeTransformacaoRedeBasicaEntity numBarraSecundario(Integer numBarraSecundario) {
        this.setNumBarraSecundario(numBarraSecundario);
        return this;
    }

    public void setNumBarraSecundario(Integer numBarraSecundario) {
        this.numBarraSecundario = numBarraSecundario;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsCapacidadeTransformacaoRedeBasicaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsCapacidadeTransformacaoRedeBasicaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsCapacidadeTransformacaoRedeBasicaEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", idEstado='" + getIdEstado() + "'" +
            ", nomEstado='" + getNomEstado() + "'" +
            ", nomTipotransformador='" + getNomTipotransformador() + "'" +
            ", nomAgenteproprietario='" + getNomAgenteproprietario() + "'" +
            ", nomSubestacao='" + getNomSubestacao() + "'" +
            ", nomTransformador='" + getNomTransformador() + "'" +
            ", codEquipamento='" + getCodEquipamento() + "'" +
            ", datEntradaoperacao='" + getDatEntradaoperacao() + "'" +
            ", datDesativacao='" + getDatDesativacao() + "'" +
            ", valTensaoprimarioKv=" + getValTensaoprimarioKv() +
            ", valTensaosecundarioKv=" + getValTensaosecundarioKv() +
            ", valTensaoterciarioKv=" + getValTensaoterciarioKv() +
            ", valPotencianominalMva=" + getValPotencianominalMva() +
            ", nomTipoderede='" + getNomTipoderede() + "'" +
            ", numBarraPrimario=" + getNumBarraPrimario() +
            ", numBarraSecundario=" + getNumBarraSecundario() +
            "}";
    }
}
