package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.
 */
@Entity
@Table(name = "OnsTable57")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsdadoshistoricodespachoenergiajaneiro2021asetembro2024")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dat_pdp")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String datPdp;

    @Column(name = "cod_submercado")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codSubmercado;

    @Column(name = "sgl_tipousina")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String sglTipousina;

    @Column(name = "cod_usinapdp")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codUsinapdp;

    @Column(name = "nom_usinapdp")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomUsinapdp;

    @Column(name = "num_intdespa")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer numIntdespa;

    @Column(name = "val_despasup")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer valDespasup;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatPdp() {
        return this.datPdp;
    }

    public OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity datPdp(String datPdp) {
        this.setDatPdp(datPdp);
        return this;
    }

    public void setDatPdp(String datPdp) {
        this.datPdp = datPdp;
    }

    public String getCodSubmercado() {
        return this.codSubmercado;
    }

    public OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity codSubmercado(String codSubmercado) {
        this.setCodSubmercado(codSubmercado);
        return this;
    }

    public void setCodSubmercado(String codSubmercado) {
        this.codSubmercado = codSubmercado;
    }

    public String getSglTipousina() {
        return this.sglTipousina;
    }

    public OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity sglTipousina(String sglTipousina) {
        this.setSglTipousina(sglTipousina);
        return this;
    }

    public void setSglTipousina(String sglTipousina) {
        this.sglTipousina = sglTipousina;
    }

    public String getCodUsinapdp() {
        return this.codUsinapdp;
    }

    public OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity codUsinapdp(String codUsinapdp) {
        this.setCodUsinapdp(codUsinapdp);
        return this;
    }

    public void setCodUsinapdp(String codUsinapdp) {
        this.codUsinapdp = codUsinapdp;
    }

    public String getNomUsinapdp() {
        return this.nomUsinapdp;
    }

    public OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity nomUsinapdp(String nomUsinapdp) {
        this.setNomUsinapdp(nomUsinapdp);
        return this;
    }

    public void setNomUsinapdp(String nomUsinapdp) {
        this.nomUsinapdp = nomUsinapdp;
    }

    public Integer getNumIntdespa() {
        return this.numIntdespa;
    }

    public OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity numIntdespa(Integer numIntdespa) {
        this.setNumIntdespa(numIntdespa);
        return this;
    }

    public void setNumIntdespa(Integer numIntdespa) {
        this.numIntdespa = numIntdespa;
    }

    public Integer getValDespasup() {
        return this.valDespasup;
    }

    public OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity valDespasup(Integer valDespasup) {
        this.setValDespasup(valDespasup);
        return this;
    }

    public void setValDespasup(Integer valDespasup) {
        this.valDespasup = valDespasup;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity{" +
            "id=" + getId() +
            ", datPdp='" + getDatPdp() + "'" +
            ", codSubmercado='" + getCodSubmercado() + "'" +
            ", sglTipousina='" + getSglTipousina() + "'" +
            ", codUsinapdp='" + getCodUsinapdp() + "'" +
            ", nomUsinapdp='" + getNomUsinapdp() + "'" +
            ", numIntdespa=" + getNumIntdespa() +
            ", valDespasup=" + getValDespasup() +
            "}";
    }
}
