package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsGeracaoTermicaMotivoDespachoEntity.
 */
@Entity
@Table(name = "OnsTable6")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsgeracaotermicamotivodespacho")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsGeracaoTermicaMotivoDespachoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "din_instante")
    private LocalDate dinInstante;

    @Column(name = "nom_tipopatamar")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomTipopatamar;

    @Column(name = "id_subsistema")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String idSubsistema;

    @Column(name = "nom_subsistema")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomSubsistema;

    @Column(name = "nom_usina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsina;

    @Column(name = "cod_usinaplanejamento")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer codUsinaplanejamento;

    @Column(name = "ceg")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String ceg;

    @Column(name = "val_proggeracao")
    private Double valProggeracao;

    @Column(name = "val_progordemmerito")
    private Double valProgordemmerito;

    @Column(name = "val_progordemdemeritoref")
    private Double valProgordemdemeritoref;

    @Column(name = "val_progordemdemeritoacimadainflex")
    private Double valProgordemdemeritoacimadainflex;

    @Column(name = "val_proginflexibilidade")
    private Double valProginflexibilidade;

    @Column(name = "val_proginflexembutmerito")
    private Double valProginflexembutmerito;

    @Column(name = "val_proginflexpura")
    private Double valProginflexpura;

    @Column(name = "val_prograzaoeletrica")
    private Double valPrograzaoeletrica;

    @Column(name = "val_proggarantiaenergetica")
    private Double valProggarantiaenergetica;

    @Column(name = "val_proggfom")
    private Double valProggfom;

    @Column(name = "val_progreposicaoperdas")
    private Double valProgreposicaoperdas;

    @Column(name = "val_progexportacao")
    private Double valProgexportacao;

    @Column(name = "val_progreservapotencia")
    private Double valProgreservapotencia;

    @Column(name = "val_proggsub")
    private Double valProggsub;

    @Column(name = "val_progunitcommitment")
    private Double valProgunitcommitment;

    @Column(name = "val_progconstrainedoff")
    private Double valProgconstrainedoff;

    @Column(name = "val_proginflexibilidadedessem")
    private Double valProginflexibilidadedessem;

    @Column(name = "val_verifgeracao")
    private Double valVerifgeracao;

    @Column(name = "val_verifordemmerito")
    private Double valVerifordemmerito;

    @Column(name = "val_verifordemdemeritoacimadainflex")
    private Double valVerifordemdemeritoacimadainflex;

    @Column(name = "val_verifinflexibilidade")
    private Double valVerifinflexibilidade;

    @Column(name = "val_verifinflexembutmerito")
    private Double valVerifinflexembutmerito;

    @Column(name = "val_verifinflexpura")
    private Double valVerifinflexpura;

    @Column(name = "val_verifrazaoeletrica")
    private Double valVerifrazaoeletrica;

    @Column(name = "val_verifgarantiaenergetica")
    private Double valVerifgarantiaenergetica;

    @Column(name = "val_verifgfom")
    private Double valVerifgfom;

    @Column(name = "val_verifreposicaoperdas")
    private Double valVerifreposicaoperdas;

    @Column(name = "val_verifexportacao")
    private Double valVerifexportacao;

    @Column(name = "val_fdexp")
    private Double valFdexp;

    @Column(name = "val_verifreservapotencia")
    private Double valVerifreservapotencia;

    @Column(name = "val_atendsatisfatoriorpo")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer valAtendsatisfatoriorpo;

    @Column(name = "val_verifgsub")
    private Double valVerifgsub;

    @Column(name = "val_verifunitcommitment")
    private Double valVerifunitcommitment;

    @Column(name = "val_verifconstrainedoff")
    private Double valVerifconstrainedoff;

    @Column(name = "tip_restricaoeletrica")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer tipRestricaoeletrica;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDinInstante() {
        return this.dinInstante;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity dinInstante(LocalDate dinInstante) {
        this.setDinInstante(dinInstante);
        return this;
    }

    public void setDinInstante(LocalDate dinInstante) {
        this.dinInstante = dinInstante;
    }

    public String getNomTipopatamar() {
        return this.nomTipopatamar;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity nomTipopatamar(String nomTipopatamar) {
        this.setNomTipopatamar(nomTipopatamar);
        return this;
    }

    public void setNomTipopatamar(String nomTipopatamar) {
        this.nomTipopatamar = nomTipopatamar;
    }

    public String getIdSubsistema() {
        return this.idSubsistema;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity idSubsistema(String idSubsistema) {
        this.setIdSubsistema(idSubsistema);
        return this;
    }

    public void setIdSubsistema(String idSubsistema) {
        this.idSubsistema = idSubsistema;
    }

    public String getNomSubsistema() {
        return this.nomSubsistema;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity nomSubsistema(String nomSubsistema) {
        this.setNomSubsistema(nomSubsistema);
        return this;
    }

    public void setNomSubsistema(String nomSubsistema) {
        this.nomSubsistema = nomSubsistema;
    }

    public String getNomUsina() {
        return this.nomUsina;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity nomUsina(String nomUsina) {
        this.setNomUsina(nomUsina);
        return this;
    }

    public void setNomUsina(String nomUsina) {
        this.nomUsina = nomUsina;
    }

    public Integer getCodUsinaplanejamento() {
        return this.codUsinaplanejamento;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity codUsinaplanejamento(Integer codUsinaplanejamento) {
        this.setCodUsinaplanejamento(codUsinaplanejamento);
        return this;
    }

    public void setCodUsinaplanejamento(Integer codUsinaplanejamento) {
        this.codUsinaplanejamento = codUsinaplanejamento;
    }

    public String getCeg() {
        return this.ceg;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity ceg(String ceg) {
        this.setCeg(ceg);
        return this;
    }

    public void setCeg(String ceg) {
        this.ceg = ceg;
    }

    public Double getValProggeracao() {
        return this.valProggeracao;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProggeracao(Double valProggeracao) {
        this.setValProggeracao(valProggeracao);
        return this;
    }

    public void setValProggeracao(Double valProggeracao) {
        this.valProggeracao = valProggeracao;
    }

    public Double getValProgordemmerito() {
        return this.valProgordemmerito;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProgordemmerito(Double valProgordemmerito) {
        this.setValProgordemmerito(valProgordemmerito);
        return this;
    }

    public void setValProgordemmerito(Double valProgordemmerito) {
        this.valProgordemmerito = valProgordemmerito;
    }

    public Double getValProgordemdemeritoref() {
        return this.valProgordemdemeritoref;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProgordemdemeritoref(Double valProgordemdemeritoref) {
        this.setValProgordemdemeritoref(valProgordemdemeritoref);
        return this;
    }

    public void setValProgordemdemeritoref(Double valProgordemdemeritoref) {
        this.valProgordemdemeritoref = valProgordemdemeritoref;
    }

    public Double getValProgordemdemeritoacimadainflex() {
        return this.valProgordemdemeritoacimadainflex;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProgordemdemeritoacimadainflex(Double valProgordemdemeritoacimadainflex) {
        this.setValProgordemdemeritoacimadainflex(valProgordemdemeritoacimadainflex);
        return this;
    }

    public void setValProgordemdemeritoacimadainflex(Double valProgordemdemeritoacimadainflex) {
        this.valProgordemdemeritoacimadainflex = valProgordemdemeritoacimadainflex;
    }

    public Double getValProginflexibilidade() {
        return this.valProginflexibilidade;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProginflexibilidade(Double valProginflexibilidade) {
        this.setValProginflexibilidade(valProginflexibilidade);
        return this;
    }

    public void setValProginflexibilidade(Double valProginflexibilidade) {
        this.valProginflexibilidade = valProginflexibilidade;
    }

    public Double getValProginflexembutmerito() {
        return this.valProginflexembutmerito;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProginflexembutmerito(Double valProginflexembutmerito) {
        this.setValProginflexembutmerito(valProginflexembutmerito);
        return this;
    }

    public void setValProginflexembutmerito(Double valProginflexembutmerito) {
        this.valProginflexembutmerito = valProginflexembutmerito;
    }

    public Double getValProginflexpura() {
        return this.valProginflexpura;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProginflexpura(Double valProginflexpura) {
        this.setValProginflexpura(valProginflexpura);
        return this;
    }

    public void setValProginflexpura(Double valProginflexpura) {
        this.valProginflexpura = valProginflexpura;
    }

    public Double getValPrograzaoeletrica() {
        return this.valPrograzaoeletrica;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valPrograzaoeletrica(Double valPrograzaoeletrica) {
        this.setValPrograzaoeletrica(valPrograzaoeletrica);
        return this;
    }

    public void setValPrograzaoeletrica(Double valPrograzaoeletrica) {
        this.valPrograzaoeletrica = valPrograzaoeletrica;
    }

    public Double getValProggarantiaenergetica() {
        return this.valProggarantiaenergetica;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProggarantiaenergetica(Double valProggarantiaenergetica) {
        this.setValProggarantiaenergetica(valProggarantiaenergetica);
        return this;
    }

    public void setValProggarantiaenergetica(Double valProggarantiaenergetica) {
        this.valProggarantiaenergetica = valProggarantiaenergetica;
    }

    public Double getValProggfom() {
        return this.valProggfom;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProggfom(Double valProggfom) {
        this.setValProggfom(valProggfom);
        return this;
    }

    public void setValProggfom(Double valProggfom) {
        this.valProggfom = valProggfom;
    }

    public Double getValProgreposicaoperdas() {
        return this.valProgreposicaoperdas;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProgreposicaoperdas(Double valProgreposicaoperdas) {
        this.setValProgreposicaoperdas(valProgreposicaoperdas);
        return this;
    }

    public void setValProgreposicaoperdas(Double valProgreposicaoperdas) {
        this.valProgreposicaoperdas = valProgreposicaoperdas;
    }

    public Double getValProgexportacao() {
        return this.valProgexportacao;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProgexportacao(Double valProgexportacao) {
        this.setValProgexportacao(valProgexportacao);
        return this;
    }

    public void setValProgexportacao(Double valProgexportacao) {
        this.valProgexportacao = valProgexportacao;
    }

    public Double getValProgreservapotencia() {
        return this.valProgreservapotencia;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProgreservapotencia(Double valProgreservapotencia) {
        this.setValProgreservapotencia(valProgreservapotencia);
        return this;
    }

    public void setValProgreservapotencia(Double valProgreservapotencia) {
        this.valProgreservapotencia = valProgreservapotencia;
    }

    public Double getValProggsub() {
        return this.valProggsub;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProggsub(Double valProggsub) {
        this.setValProggsub(valProggsub);
        return this;
    }

    public void setValProggsub(Double valProggsub) {
        this.valProggsub = valProggsub;
    }

    public Double getValProgunitcommitment() {
        return this.valProgunitcommitment;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProgunitcommitment(Double valProgunitcommitment) {
        this.setValProgunitcommitment(valProgunitcommitment);
        return this;
    }

    public void setValProgunitcommitment(Double valProgunitcommitment) {
        this.valProgunitcommitment = valProgunitcommitment;
    }

    public Double getValProgconstrainedoff() {
        return this.valProgconstrainedoff;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProgconstrainedoff(Double valProgconstrainedoff) {
        this.setValProgconstrainedoff(valProgconstrainedoff);
        return this;
    }

    public void setValProgconstrainedoff(Double valProgconstrainedoff) {
        this.valProgconstrainedoff = valProgconstrainedoff;
    }

    public Double getValProginflexibilidadedessem() {
        return this.valProginflexibilidadedessem;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valProginflexibilidadedessem(Double valProginflexibilidadedessem) {
        this.setValProginflexibilidadedessem(valProginflexibilidadedessem);
        return this;
    }

    public void setValProginflexibilidadedessem(Double valProginflexibilidadedessem) {
        this.valProginflexibilidadedessem = valProginflexibilidadedessem;
    }

    public Double getValVerifgeracao() {
        return this.valVerifgeracao;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifgeracao(Double valVerifgeracao) {
        this.setValVerifgeracao(valVerifgeracao);
        return this;
    }

    public void setValVerifgeracao(Double valVerifgeracao) {
        this.valVerifgeracao = valVerifgeracao;
    }

    public Double getValVerifordemmerito() {
        return this.valVerifordemmerito;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifordemmerito(Double valVerifordemmerito) {
        this.setValVerifordemmerito(valVerifordemmerito);
        return this;
    }

    public void setValVerifordemmerito(Double valVerifordemmerito) {
        this.valVerifordemmerito = valVerifordemmerito;
    }

    public Double getValVerifordemdemeritoacimadainflex() {
        return this.valVerifordemdemeritoacimadainflex;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifordemdemeritoacimadainflex(Double valVerifordemdemeritoacimadainflex) {
        this.setValVerifordemdemeritoacimadainflex(valVerifordemdemeritoacimadainflex);
        return this;
    }

    public void setValVerifordemdemeritoacimadainflex(Double valVerifordemdemeritoacimadainflex) {
        this.valVerifordemdemeritoacimadainflex = valVerifordemdemeritoacimadainflex;
    }

    public Double getValVerifinflexibilidade() {
        return this.valVerifinflexibilidade;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifinflexibilidade(Double valVerifinflexibilidade) {
        this.setValVerifinflexibilidade(valVerifinflexibilidade);
        return this;
    }

    public void setValVerifinflexibilidade(Double valVerifinflexibilidade) {
        this.valVerifinflexibilidade = valVerifinflexibilidade;
    }

    public Double getValVerifinflexembutmerito() {
        return this.valVerifinflexembutmerito;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifinflexembutmerito(Double valVerifinflexembutmerito) {
        this.setValVerifinflexembutmerito(valVerifinflexembutmerito);
        return this;
    }

    public void setValVerifinflexembutmerito(Double valVerifinflexembutmerito) {
        this.valVerifinflexembutmerito = valVerifinflexembutmerito;
    }

    public Double getValVerifinflexpura() {
        return this.valVerifinflexpura;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifinflexpura(Double valVerifinflexpura) {
        this.setValVerifinflexpura(valVerifinflexpura);
        return this;
    }

    public void setValVerifinflexpura(Double valVerifinflexpura) {
        this.valVerifinflexpura = valVerifinflexpura;
    }

    public Double getValVerifrazaoeletrica() {
        return this.valVerifrazaoeletrica;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifrazaoeletrica(Double valVerifrazaoeletrica) {
        this.setValVerifrazaoeletrica(valVerifrazaoeletrica);
        return this;
    }

    public void setValVerifrazaoeletrica(Double valVerifrazaoeletrica) {
        this.valVerifrazaoeletrica = valVerifrazaoeletrica;
    }

    public Double getValVerifgarantiaenergetica() {
        return this.valVerifgarantiaenergetica;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifgarantiaenergetica(Double valVerifgarantiaenergetica) {
        this.setValVerifgarantiaenergetica(valVerifgarantiaenergetica);
        return this;
    }

    public void setValVerifgarantiaenergetica(Double valVerifgarantiaenergetica) {
        this.valVerifgarantiaenergetica = valVerifgarantiaenergetica;
    }

    public Double getValVerifgfom() {
        return this.valVerifgfom;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifgfom(Double valVerifgfom) {
        this.setValVerifgfom(valVerifgfom);
        return this;
    }

    public void setValVerifgfom(Double valVerifgfom) {
        this.valVerifgfom = valVerifgfom;
    }

    public Double getValVerifreposicaoperdas() {
        return this.valVerifreposicaoperdas;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifreposicaoperdas(Double valVerifreposicaoperdas) {
        this.setValVerifreposicaoperdas(valVerifreposicaoperdas);
        return this;
    }

    public void setValVerifreposicaoperdas(Double valVerifreposicaoperdas) {
        this.valVerifreposicaoperdas = valVerifreposicaoperdas;
    }

    public Double getValVerifexportacao() {
        return this.valVerifexportacao;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifexportacao(Double valVerifexportacao) {
        this.setValVerifexportacao(valVerifexportacao);
        return this;
    }

    public void setValVerifexportacao(Double valVerifexportacao) {
        this.valVerifexportacao = valVerifexportacao;
    }

    public Double getValFdexp() {
        return this.valFdexp;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valFdexp(Double valFdexp) {
        this.setValFdexp(valFdexp);
        return this;
    }

    public void setValFdexp(Double valFdexp) {
        this.valFdexp = valFdexp;
    }

    public Double getValVerifreservapotencia() {
        return this.valVerifreservapotencia;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifreservapotencia(Double valVerifreservapotencia) {
        this.setValVerifreservapotencia(valVerifreservapotencia);
        return this;
    }

    public void setValVerifreservapotencia(Double valVerifreservapotencia) {
        this.valVerifreservapotencia = valVerifreservapotencia;
    }

    public Integer getValAtendsatisfatoriorpo() {
        return this.valAtendsatisfatoriorpo;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valAtendsatisfatoriorpo(Integer valAtendsatisfatoriorpo) {
        this.setValAtendsatisfatoriorpo(valAtendsatisfatoriorpo);
        return this;
    }

    public void setValAtendsatisfatoriorpo(Integer valAtendsatisfatoriorpo) {
        this.valAtendsatisfatoriorpo = valAtendsatisfatoriorpo;
    }

    public Double getValVerifgsub() {
        return this.valVerifgsub;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifgsub(Double valVerifgsub) {
        this.setValVerifgsub(valVerifgsub);
        return this;
    }

    public void setValVerifgsub(Double valVerifgsub) {
        this.valVerifgsub = valVerifgsub;
    }

    public Double getValVerifunitcommitment() {
        return this.valVerifunitcommitment;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifunitcommitment(Double valVerifunitcommitment) {
        this.setValVerifunitcommitment(valVerifunitcommitment);
        return this;
    }

    public void setValVerifunitcommitment(Double valVerifunitcommitment) {
        this.valVerifunitcommitment = valVerifunitcommitment;
    }

    public Double getValVerifconstrainedoff() {
        return this.valVerifconstrainedoff;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity valVerifconstrainedoff(Double valVerifconstrainedoff) {
        this.setValVerifconstrainedoff(valVerifconstrainedoff);
        return this;
    }

    public void setValVerifconstrainedoff(Double valVerifconstrainedoff) {
        this.valVerifconstrainedoff = valVerifconstrainedoff;
    }

    public Integer getTipRestricaoeletrica() {
        return this.tipRestricaoeletrica;
    }

    public OnsGeracaoTermicaMotivoDespachoEntity tipRestricaoeletrica(Integer tipRestricaoeletrica) {
        this.setTipRestricaoeletrica(tipRestricaoeletrica);
        return this;
    }

    public void setTipRestricaoeletrica(Integer tipRestricaoeletrica) {
        this.tipRestricaoeletrica = tipRestricaoeletrica;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsGeracaoTermicaMotivoDespachoEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsGeracaoTermicaMotivoDespachoEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsGeracaoTermicaMotivoDespachoEntity{" +
            "id=" + getId() +
            ", dinInstante='" + getDinInstante() + "'" +
            ", nomTipopatamar='" + getNomTipopatamar() + "'" +
            ", idSubsistema='" + getIdSubsistema() + "'" +
            ", nomSubsistema='" + getNomSubsistema() + "'" +
            ", nomUsina='" + getNomUsina() + "'" +
            ", codUsinaplanejamento=" + getCodUsinaplanejamento() +
            ", ceg='" + getCeg() + "'" +
            ", valProggeracao=" + getValProggeracao() +
            ", valProgordemmerito=" + getValProgordemmerito() +
            ", valProgordemdemeritoref=" + getValProgordemdemeritoref() +
            ", valProgordemdemeritoacimadainflex=" + getValProgordemdemeritoacimadainflex() +
            ", valProginflexibilidade=" + getValProginflexibilidade() +
            ", valProginflexembutmerito=" + getValProginflexembutmerito() +
            ", valProginflexpura=" + getValProginflexpura() +
            ", valPrograzaoeletrica=" + getValPrograzaoeletrica() +
            ", valProggarantiaenergetica=" + getValProggarantiaenergetica() +
            ", valProggfom=" + getValProggfom() +
            ", valProgreposicaoperdas=" + getValProgreposicaoperdas() +
            ", valProgexportacao=" + getValProgexportacao() +
            ", valProgreservapotencia=" + getValProgreservapotencia() +
            ", valProggsub=" + getValProggsub() +
            ", valProgunitcommitment=" + getValProgunitcommitment() +
            ", valProgconstrainedoff=" + getValProgconstrainedoff() +
            ", valProginflexibilidadedessem=" + getValProginflexibilidadedessem() +
            ", valVerifgeracao=" + getValVerifgeracao() +
            ", valVerifordemmerito=" + getValVerifordemmerito() +
            ", valVerifordemdemeritoacimadainflex=" + getValVerifordemdemeritoacimadainflex() +
            ", valVerifinflexibilidade=" + getValVerifinflexibilidade() +
            ", valVerifinflexembutmerito=" + getValVerifinflexembutmerito() +
            ", valVerifinflexpura=" + getValVerifinflexpura() +
            ", valVerifrazaoeletrica=" + getValVerifrazaoeletrica() +
            ", valVerifgarantiaenergetica=" + getValVerifgarantiaenergetica() +
            ", valVerifgfom=" + getValVerifgfom() +
            ", valVerifreposicaoperdas=" + getValVerifreposicaoperdas() +
            ", valVerifexportacao=" + getValVerifexportacao() +
            ", valFdexp=" + getValFdexp() +
            ", valVerifreservapotencia=" + getValVerifreservapotencia() +
            ", valAtendsatisfatoriorpo=" + getValAtendsatisfatoriorpo() +
            ", valVerifgsub=" + getValVerifgsub() +
            ", valVerifunitcommitment=" + getValVerifunitcommitment() +
            ", valVerifconstrainedoff=" + getValVerifconstrainedoff() +
            ", tipRestricaoeletrica=" + getTipRestricaoeletrica() +
            "}";
    }
}
