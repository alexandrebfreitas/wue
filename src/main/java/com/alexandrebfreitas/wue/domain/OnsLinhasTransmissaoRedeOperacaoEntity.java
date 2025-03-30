package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsLinhasTransmissaoRedeOperacaoEntity.
 */
@Entity
@Table(name = "OnsTable25")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onslinhastransmissaoredeoperacao")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsLinhasTransmissaoRedeOperacaoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_subsistema_terminalde")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idSubsistemaTerminalde;

    @Column(name = "nom_subsistema_terminalde")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubsistemaTerminalde;

    @Column(name = "id_subsistema_terminalpara")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idSubsistemaTerminalpara;

    @Column(name = "nom_subsistema_terminalpara")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubsistemaTerminalpara;

    @Column(name = "id_estado_terminalde")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idEstadoTerminalde;

    @Column(name = "nom_estado_de")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomEstadoDe;

    @Column(name = "id_estado_terminalpara")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idEstadoTerminalpara;

    @Column(name = "nom_estado_para")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomEstadoPara;

    @Column(name = "nom_subestacao_de")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubestacaoDe;

    @Column(name = "nom_subestacao_para")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubestacaoPara;

    @Column(name = "val_niveltensao_kv")
    private Double valNiveltensaoKv;

    @Column(name = "nom_tipoderede")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTipoderede;

    @Column(name = "nom_tipolinha")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTipolinha;

    @Column(name = "nom_agenteproprietario")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomAgenteproprietario;

    @Column(name = "nom_linhadetransmissao")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomLinhadetransmissao;

    @Column(name = "cod_equipamento")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codEquipamento;

    @Column(name = "dat_entradaoperacao")
    private LocalDate datEntradaoperacao;

    @Column(name = "dat_desativacao")
    private LocalDate datDesativacao;

    @Column(name = "dat_prevista")
    private LocalDate datPrevista;

    @Column(name = "val_comprimento")
    private Double valComprimento;

    @Column(name = "val_resistencia")
    private Double valResistencia;

    @Column(name = "val_reatancia")
    private Double valReatancia;

    @Column(name = "val_shunt")
    private Double valShunt;

    @Column(name = "val_capacoperlongasemlimit")
    private Double valCapacoperlongasemlimit;

    @Column(name = "val_capacoperlongacomlimit")
    private Double valCapacoperlongacomlimit;

    @Column(name = "val_capacopercurtasemlimit")
    private Double valCapacopercurtasemlimit;

    @Column(name = "val_capacopercurtacomlimit")
    private Double valCapacopercurtacomlimit;

    @Column(name = "val_capacidadeoperveraodialonga")
    private Double valCapacidadeoperveraodialonga;

    @Column(name = "val_capacoperinvernodialonga")
    private Double valCapacoperinvernodialonga;

    @Column(name = "val_capacoperinvernonoitelonga")
    private Double valCapacoperinvernonoitelonga;

    @Column(name = "val_capacoperveradiacurta")
    private Double valCapacoperveradiacurta;

    @Column(name = "val_capacoperveraonoitecurta")
    private Double valCapacoperveraonoitecurta;

    @Column(name = "val_capacoperinvernodiacurta")
    private Double valCapacoperinvernodiacurta;

    @Column(name = "val_capacoperinvernonoitecurta")
    private Double valCapacoperinvernonoitecurta;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSubsistemaTerminalde() {
        return this.idSubsistemaTerminalde;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity idSubsistemaTerminalde(String idSubsistemaTerminalde) {
        this.setIdSubsistemaTerminalde(idSubsistemaTerminalde);
        return this;
    }

    public void setIdSubsistemaTerminalde(String idSubsistemaTerminalde) {
        this.idSubsistemaTerminalde = idSubsistemaTerminalde;
    }

    public String getNomSubsistemaTerminalde() {
        return this.nomSubsistemaTerminalde;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity nomSubsistemaTerminalde(String nomSubsistemaTerminalde) {
        this.setNomSubsistemaTerminalde(nomSubsistemaTerminalde);
        return this;
    }

    public void setNomSubsistemaTerminalde(String nomSubsistemaTerminalde) {
        this.nomSubsistemaTerminalde = nomSubsistemaTerminalde;
    }

    public String getIdSubsistemaTerminalpara() {
        return this.idSubsistemaTerminalpara;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity idSubsistemaTerminalpara(String idSubsistemaTerminalpara) {
        this.setIdSubsistemaTerminalpara(idSubsistemaTerminalpara);
        return this;
    }

    public void setIdSubsistemaTerminalpara(String idSubsistemaTerminalpara) {
        this.idSubsistemaTerminalpara = idSubsistemaTerminalpara;
    }

    public String getNomSubsistemaTerminalpara() {
        return this.nomSubsistemaTerminalpara;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity nomSubsistemaTerminalpara(String nomSubsistemaTerminalpara) {
        this.setNomSubsistemaTerminalpara(nomSubsistemaTerminalpara);
        return this;
    }

    public void setNomSubsistemaTerminalpara(String nomSubsistemaTerminalpara) {
        this.nomSubsistemaTerminalpara = nomSubsistemaTerminalpara;
    }

    public String getIdEstadoTerminalde() {
        return this.idEstadoTerminalde;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity idEstadoTerminalde(String idEstadoTerminalde) {
        this.setIdEstadoTerminalde(idEstadoTerminalde);
        return this;
    }

    public void setIdEstadoTerminalde(String idEstadoTerminalde) {
        this.idEstadoTerminalde = idEstadoTerminalde;
    }

    public String getNomEstadoDe() {
        return this.nomEstadoDe;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity nomEstadoDe(String nomEstadoDe) {
        this.setNomEstadoDe(nomEstadoDe);
        return this;
    }

    public void setNomEstadoDe(String nomEstadoDe) {
        this.nomEstadoDe = nomEstadoDe;
    }

    public String getIdEstadoTerminalpara() {
        return this.idEstadoTerminalpara;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity idEstadoTerminalpara(String idEstadoTerminalpara) {
        this.setIdEstadoTerminalpara(idEstadoTerminalpara);
        return this;
    }

    public void setIdEstadoTerminalpara(String idEstadoTerminalpara) {
        this.idEstadoTerminalpara = idEstadoTerminalpara;
    }

    public String getNomEstadoPara() {
        return this.nomEstadoPara;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity nomEstadoPara(String nomEstadoPara) {
        this.setNomEstadoPara(nomEstadoPara);
        return this;
    }

    public void setNomEstadoPara(String nomEstadoPara) {
        this.nomEstadoPara = nomEstadoPara;
    }

    public String getNomSubestacaoDe() {
        return this.nomSubestacaoDe;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity nomSubestacaoDe(String nomSubestacaoDe) {
        this.setNomSubestacaoDe(nomSubestacaoDe);
        return this;
    }

    public void setNomSubestacaoDe(String nomSubestacaoDe) {
        this.nomSubestacaoDe = nomSubestacaoDe;
    }

    public String getNomSubestacaoPara() {
        return this.nomSubestacaoPara;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity nomSubestacaoPara(String nomSubestacaoPara) {
        this.setNomSubestacaoPara(nomSubestacaoPara);
        return this;
    }

    public void setNomSubestacaoPara(String nomSubestacaoPara) {
        this.nomSubestacaoPara = nomSubestacaoPara;
    }

    public Double getValNiveltensaoKv() {
        return this.valNiveltensaoKv;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valNiveltensaoKv(Double valNiveltensaoKv) {
        this.setValNiveltensaoKv(valNiveltensaoKv);
        return this;
    }

    public void setValNiveltensaoKv(Double valNiveltensaoKv) {
        this.valNiveltensaoKv = valNiveltensaoKv;
    }

    public String getNomTipoderede() {
        return this.nomTipoderede;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity nomTipoderede(String nomTipoderede) {
        this.setNomTipoderede(nomTipoderede);
        return this;
    }

    public void setNomTipoderede(String nomTipoderede) {
        this.nomTipoderede = nomTipoderede;
    }

    public String getNomTipolinha() {
        return this.nomTipolinha;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity nomTipolinha(String nomTipolinha) {
        this.setNomTipolinha(nomTipolinha);
        return this;
    }

    public void setNomTipolinha(String nomTipolinha) {
        this.nomTipolinha = nomTipolinha;
    }

    public String getNomAgenteproprietario() {
        return this.nomAgenteproprietario;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity nomAgenteproprietario(String nomAgenteproprietario) {
        this.setNomAgenteproprietario(nomAgenteproprietario);
        return this;
    }

    public void setNomAgenteproprietario(String nomAgenteproprietario) {
        this.nomAgenteproprietario = nomAgenteproprietario;
    }

    public String getNomLinhadetransmissao() {
        return this.nomLinhadetransmissao;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity nomLinhadetransmissao(String nomLinhadetransmissao) {
        this.setNomLinhadetransmissao(nomLinhadetransmissao);
        return this;
    }

    public void setNomLinhadetransmissao(String nomLinhadetransmissao) {
        this.nomLinhadetransmissao = nomLinhadetransmissao;
    }

    public String getCodEquipamento() {
        return this.codEquipamento;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity codEquipamento(String codEquipamento) {
        this.setCodEquipamento(codEquipamento);
        return this;
    }

    public void setCodEquipamento(String codEquipamento) {
        this.codEquipamento = codEquipamento;
    }

    public LocalDate getDatEntradaoperacao() {
        return this.datEntradaoperacao;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity datEntradaoperacao(LocalDate datEntradaoperacao) {
        this.setDatEntradaoperacao(datEntradaoperacao);
        return this;
    }

    public void setDatEntradaoperacao(LocalDate datEntradaoperacao) {
        this.datEntradaoperacao = datEntradaoperacao;
    }

    public LocalDate getDatDesativacao() {
        return this.datDesativacao;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity datDesativacao(LocalDate datDesativacao) {
        this.setDatDesativacao(datDesativacao);
        return this;
    }

    public void setDatDesativacao(LocalDate datDesativacao) {
        this.datDesativacao = datDesativacao;
    }

    public LocalDate getDatPrevista() {
        return this.datPrevista;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity datPrevista(LocalDate datPrevista) {
        this.setDatPrevista(datPrevista);
        return this;
    }

    public void setDatPrevista(LocalDate datPrevista) {
        this.datPrevista = datPrevista;
    }

    public Double getValComprimento() {
        return this.valComprimento;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valComprimento(Double valComprimento) {
        this.setValComprimento(valComprimento);
        return this;
    }

    public void setValComprimento(Double valComprimento) {
        this.valComprimento = valComprimento;
    }

    public Double getValResistencia() {
        return this.valResistencia;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valResistencia(Double valResistencia) {
        this.setValResistencia(valResistencia);
        return this;
    }

    public void setValResistencia(Double valResistencia) {
        this.valResistencia = valResistencia;
    }

    public Double getValReatancia() {
        return this.valReatancia;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valReatancia(Double valReatancia) {
        this.setValReatancia(valReatancia);
        return this;
    }

    public void setValReatancia(Double valReatancia) {
        this.valReatancia = valReatancia;
    }

    public Double getValShunt() {
        return this.valShunt;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valShunt(Double valShunt) {
        this.setValShunt(valShunt);
        return this;
    }

    public void setValShunt(Double valShunt) {
        this.valShunt = valShunt;
    }

    public Double getValCapacoperlongasemlimit() {
        return this.valCapacoperlongasemlimit;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valCapacoperlongasemlimit(Double valCapacoperlongasemlimit) {
        this.setValCapacoperlongasemlimit(valCapacoperlongasemlimit);
        return this;
    }

    public void setValCapacoperlongasemlimit(Double valCapacoperlongasemlimit) {
        this.valCapacoperlongasemlimit = valCapacoperlongasemlimit;
    }

    public Double getValCapacoperlongacomlimit() {
        return this.valCapacoperlongacomlimit;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valCapacoperlongacomlimit(Double valCapacoperlongacomlimit) {
        this.setValCapacoperlongacomlimit(valCapacoperlongacomlimit);
        return this;
    }

    public void setValCapacoperlongacomlimit(Double valCapacoperlongacomlimit) {
        this.valCapacoperlongacomlimit = valCapacoperlongacomlimit;
    }

    public Double getValCapacopercurtasemlimit() {
        return this.valCapacopercurtasemlimit;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valCapacopercurtasemlimit(Double valCapacopercurtasemlimit) {
        this.setValCapacopercurtasemlimit(valCapacopercurtasemlimit);
        return this;
    }

    public void setValCapacopercurtasemlimit(Double valCapacopercurtasemlimit) {
        this.valCapacopercurtasemlimit = valCapacopercurtasemlimit;
    }

    public Double getValCapacopercurtacomlimit() {
        return this.valCapacopercurtacomlimit;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valCapacopercurtacomlimit(Double valCapacopercurtacomlimit) {
        this.setValCapacopercurtacomlimit(valCapacopercurtacomlimit);
        return this;
    }

    public void setValCapacopercurtacomlimit(Double valCapacopercurtacomlimit) {
        this.valCapacopercurtacomlimit = valCapacopercurtacomlimit;
    }

    public Double getValCapacidadeoperveraodialonga() {
        return this.valCapacidadeoperveraodialonga;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valCapacidadeoperveraodialonga(Double valCapacidadeoperveraodialonga) {
        this.setValCapacidadeoperveraodialonga(valCapacidadeoperveraodialonga);
        return this;
    }

    public void setValCapacidadeoperveraodialonga(Double valCapacidadeoperveraodialonga) {
        this.valCapacidadeoperveraodialonga = valCapacidadeoperveraodialonga;
    }

    public Double getValCapacoperinvernodialonga() {
        return this.valCapacoperinvernodialonga;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valCapacoperinvernodialonga(Double valCapacoperinvernodialonga) {
        this.setValCapacoperinvernodialonga(valCapacoperinvernodialonga);
        return this;
    }

    public void setValCapacoperinvernodialonga(Double valCapacoperinvernodialonga) {
        this.valCapacoperinvernodialonga = valCapacoperinvernodialonga;
    }

    public Double getValCapacoperinvernonoitelonga() {
        return this.valCapacoperinvernonoitelonga;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valCapacoperinvernonoitelonga(Double valCapacoperinvernonoitelonga) {
        this.setValCapacoperinvernonoitelonga(valCapacoperinvernonoitelonga);
        return this;
    }

    public void setValCapacoperinvernonoitelonga(Double valCapacoperinvernonoitelonga) {
        this.valCapacoperinvernonoitelonga = valCapacoperinvernonoitelonga;
    }

    public Double getValCapacoperveradiacurta() {
        return this.valCapacoperveradiacurta;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valCapacoperveradiacurta(Double valCapacoperveradiacurta) {
        this.setValCapacoperveradiacurta(valCapacoperveradiacurta);
        return this;
    }

    public void setValCapacoperveradiacurta(Double valCapacoperveradiacurta) {
        this.valCapacoperveradiacurta = valCapacoperveradiacurta;
    }

    public Double getValCapacoperveraonoitecurta() {
        return this.valCapacoperveraonoitecurta;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valCapacoperveraonoitecurta(Double valCapacoperveraonoitecurta) {
        this.setValCapacoperveraonoitecurta(valCapacoperveraonoitecurta);
        return this;
    }

    public void setValCapacoperveraonoitecurta(Double valCapacoperveraonoitecurta) {
        this.valCapacoperveraonoitecurta = valCapacoperveraonoitecurta;
    }

    public Double getValCapacoperinvernodiacurta() {
        return this.valCapacoperinvernodiacurta;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valCapacoperinvernodiacurta(Double valCapacoperinvernodiacurta) {
        this.setValCapacoperinvernodiacurta(valCapacoperinvernodiacurta);
        return this;
    }

    public void setValCapacoperinvernodiacurta(Double valCapacoperinvernodiacurta) {
        this.valCapacoperinvernodiacurta = valCapacoperinvernodiacurta;
    }

    public Double getValCapacoperinvernonoitecurta() {
        return this.valCapacoperinvernonoitecurta;
    }

    public OnsLinhasTransmissaoRedeOperacaoEntity valCapacoperinvernonoitecurta(Double valCapacoperinvernonoitecurta) {
        this.setValCapacoperinvernonoitecurta(valCapacoperinvernonoitecurta);
        return this;
    }

    public void setValCapacoperinvernonoitecurta(Double valCapacoperinvernonoitecurta) {
        this.valCapacoperinvernonoitecurta = valCapacoperinvernonoitecurta;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsLinhasTransmissaoRedeOperacaoEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsLinhasTransmissaoRedeOperacaoEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsLinhasTransmissaoRedeOperacaoEntity{" +
            "id=" + getId() +
            ", idSubsistemaTerminalde='" + getIdSubsistemaTerminalde() + "'" +
            ", nomSubsistemaTerminalde='" + getNomSubsistemaTerminalde() + "'" +
            ", idSubsistemaTerminalpara='" + getIdSubsistemaTerminalpara() + "'" +
            ", nomSubsistemaTerminalpara='" + getNomSubsistemaTerminalpara() + "'" +
            ", idEstadoTerminalde='" + getIdEstadoTerminalde() + "'" +
            ", nomEstadoDe='" + getNomEstadoDe() + "'" +
            ", idEstadoTerminalpara='" + getIdEstadoTerminalpara() + "'" +
            ", nomEstadoPara='" + getNomEstadoPara() + "'" +
            ", nomSubestacaoDe='" + getNomSubestacaoDe() + "'" +
            ", nomSubestacaoPara='" + getNomSubestacaoPara() + "'" +
            ", valNiveltensaoKv=" + getValNiveltensaoKv() +
            ", nomTipoderede='" + getNomTipoderede() + "'" +
            ", nomTipolinha='" + getNomTipolinha() + "'" +
            ", nomAgenteproprietario='" + getNomAgenteproprietario() + "'" +
            ", nomLinhadetransmissao='" + getNomLinhadetransmissao() + "'" +
            ", codEquipamento='" + getCodEquipamento() + "'" +
            ", datEntradaoperacao='" + getDatEntradaoperacao() + "'" +
            ", datDesativacao='" + getDatDesativacao() + "'" +
            ", datPrevista='" + getDatPrevista() + "'" +
            ", valComprimento=" + getValComprimento() +
            ", valResistencia=" + getValResistencia() +
            ", valReatancia=" + getValReatancia() +
            ", valShunt=" + getValShunt() +
            ", valCapacoperlongasemlimit=" + getValCapacoperlongasemlimit() +
            ", valCapacoperlongacomlimit=" + getValCapacoperlongacomlimit() +
            ", valCapacopercurtasemlimit=" + getValCapacopercurtasemlimit() +
            ", valCapacopercurtacomlimit=" + getValCapacopercurtacomlimit() +
            ", valCapacidadeoperveraodialonga=" + getValCapacidadeoperveraodialonga() +
            ", valCapacoperinvernodialonga=" + getValCapacoperinvernodialonga() +
            ", valCapacoperinvernonoitelonga=" + getValCapacoperinvernonoitelonga() +
            ", valCapacoperveradiacurta=" + getValCapacoperveradiacurta() +
            ", valCapacoperveraonoitecurta=" + getValCapacoperveraonoitecurta() +
            ", valCapacoperinvernodiacurta=" + getValCapacoperinvernodiacurta() +
            ", valCapacoperinvernonoitecurta=" + getValCapacoperinvernonoitecurta() +
            "}";
    }
}
