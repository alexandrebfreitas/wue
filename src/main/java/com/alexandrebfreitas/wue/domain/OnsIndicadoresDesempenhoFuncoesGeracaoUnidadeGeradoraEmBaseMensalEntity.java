package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.
 */
@Entity
@Table(name = "OnsTable36")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(
    indexName = "onsindicadoresdesempenhofuncoesgeracaounidadegeradoraembasemensal"
)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity implements Serializable {

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

    @Column(name = "id_tipousina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idTipousina;

    @Column(name = "id_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idUsina;

    @Column(name = "nom_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsina;

    @Column(name = "ceg")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String ceg;

    @Column(name = "cod_equipamento")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codEquipamento;

    @Column(name = "num_unidadegeradora")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String numUnidadegeradora;

    @Column(name = "nom_unidadegeradora")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUnidadegeradora;

    @Column(name = "val_potencia")
    private Double valPotencia;

    @Column(name = "val_dispf")
    private Double valDispf;

    @Column(name = "val_indisppf")
    private Double valIndisppf;

    @Column(name = "val_indispff")
    private Double valIndispff;

    @Column(name = "val_dmdff")
    private Double valDmdff;

    @Column(name = "val_fdff")
    private Double valFdff;

    @Column(name = "val_tdff")
    private Double valTdff;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getIdEstado() {
        return this.idEstado;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity idEstado(String idEstado) {
        this.setIdEstado(idEstado);
        return this;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity nomEstado(String nomEstado) {
        this.setNomEstado(nomEstado);
        return this;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public String getNomModalidadeoperacao() {
        return this.nomModalidadeoperacao;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity nomModalidadeoperacao(String nomModalidadeoperacao) {
        this.setNomModalidadeoperacao(nomModalidadeoperacao);
        return this;
    }

    public void setNomModalidadeoperacao(String nomModalidadeoperacao) {
        this.nomModalidadeoperacao = nomModalidadeoperacao;
    }

    public String getNomAgenteproprietario() {
        return this.nomAgenteproprietario;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity nomAgenteproprietario(String nomAgenteproprietario) {
        this.setNomAgenteproprietario(nomAgenteproprietario);
        return this;
    }

    public void setNomAgenteproprietario(String nomAgenteproprietario) {
        this.nomAgenteproprietario = nomAgenteproprietario;
    }

    public String getIdTipousina() {
        return this.idTipousina;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity idTipousina(String idTipousina) {
        this.setIdTipousina(idTipousina);
        return this;
    }

    public void setIdTipousina(String idTipousina) {
        this.idTipousina = idTipousina;
    }

    public String getIdUsina() {
        return this.idUsina;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity idUsina(String idUsina) {
        this.setIdUsina(idUsina);
        return this;
    }

    public void setIdUsina(String idUsina) {
        this.idUsina = idUsina;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public String getCeg() {
        return this.ceg;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity ceg(String ceg) {
        this.setCeg(ceg);
        return this;
    }

    public void setCeg(String ceg) {
        this.ceg = ceg;
    }

    public String getCodEquipamento() {
        return this.codEquipamento;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity codEquipamento(String codEquipamento) {
        this.setCodEquipamento(codEquipamento);
        return this;
    }

    public void setCodEquipamento(String codEquipamento) {
        this.codEquipamento = codEquipamento;
    }

    public String getNumUnidadegeradora() {
        return this.numUnidadegeradora;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity numUnidadegeradora(String numUnidadegeradora) {
        this.setNumUnidadegeradora(numUnidadegeradora);
        return this;
    }

    public void setNumUnidadegeradora(String numUnidadegeradora) {
        this.numUnidadegeradora = numUnidadegeradora;
    }

    public String getNomUnidadegeradora() {
        return this.nomUnidadegeradora;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity nomUnidadegeradora(String nomUnidadegeradora) {
        this.setNomUnidadegeradora(nomUnidadegeradora);
        return this;
    }

    public void setNomUnidadegeradora(String nomUnidadegeradora) {
        this.nomUnidadegeradora = nomUnidadegeradora;
    }

    public Double getValPotencia() {
        return this.valPotencia;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity valPotencia(Double valPotencia) {
        this.setValPotencia(valPotencia);
        return this;
    }

    public void setValPotencia(Double valPotencia) {
        this.valPotencia = valPotencia;
    }

    public Double getValDispf() {
        return this.valDispf;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity valDispf(Double valDispf) {
        this.setValDispf(valDispf);
        return this;
    }

    public void setValDispf(Double valDispf) {
        this.valDispf = valDispf;
    }

    public Double getValIndisppf() {
        return this.valIndisppf;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity valIndisppf(Double valIndisppf) {
        this.setValIndisppf(valIndisppf);
        return this;
    }

    public void setValIndisppf(Double valIndisppf) {
        this.valIndisppf = valIndisppf;
    }

    public Double getValIndispff() {
        return this.valIndispff;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity valIndispff(Double valIndispff) {
        this.setValIndispff(valIndispff);
        return this;
    }

    public void setValIndispff(Double valIndispff) {
        this.valIndispff = valIndispff;
    }

    public Double getValDmdff() {
        return this.valDmdff;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity valDmdff(Double valDmdff) {
        this.setValDmdff(valDmdff);
        return this;
    }

    public void setValDmdff(Double valDmdff) {
        this.valDmdff = valDmdff;
    }

    public Double getValFdff() {
        return this.valFdff;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity valFdff(Double valFdff) {
        this.setValFdff(valFdff);
        return this;
    }

    public void setValFdff(Double valFdff) {
        this.valFdff = valFdff;
    }

    public Double getValTdff() {
        return this.valTdff;
    }

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity valTdff(Double valTdff) {
        this.setValTdff(valTdff);
        return this;
    }

    public void setValTdff(Double valTdff) {
        this.valTdff = valTdff;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity{" +
            "id=" + getId() +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", idEstado='" + getIdEstado() + "'" +
            ", nomEstado='" + getNomEstado() + "'" +
            ", nomModalidadeoperacao='" + getNomModalidadeoperacao() + "'" +
            ", nomAgenteproprietario='" + getNomAgenteproprietario() + "'" +
            ", idTipousina='" + getIdTipousina() + "'" +
            ", idUsina='" + getIdUsina() + "'" +
            ", nomUsina='" + getNomUsina() + "'" +
            ", ceg='" + getCeg() + "'" +
            ", codEquipamento='" + getCodEquipamento() + "'" +
            ", numUnidadegeradora='" + getNumUnidadegeradora() + "'" +
            ", nomUnidadegeradora='" + getNomUnidadegeradora() + "'" +
            ", valPotencia=" + getValPotencia() +
            ", valDispf=" + getValDispf() +
            ", valIndisppf=" + getValIndisppf() +
            ", valIndispff=" + getValIndispff() +
            ", valDmdff=" + getValDmdff() +
            ", valFdff=" + getValFdff() +
            ", valTdff=" + getValTdff() +
            "}";
    }
}
