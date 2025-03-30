package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity.
 */
@Entity
@Table(name = "OnsTable7")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onseardiarioreereservatorioequivalenteenergia")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom_ree")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String nomRee;

    @Column(name = "ear_data")
    private LocalDate earData;

    @Column(name = "ear_max_ree")
    private Double earMaxRee;

    @Column(name = "ear_verif_ree_mwmes")
    private Double earVerifReeMwmes;

    @Column(name = "ear_verif_ree_percentual")
    private Double earVerifReePercentual;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomRee() {
        return this.nomRee;
    }

    public OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity nomRee(String nomRee) {
        this.setNomRee(nomRee);
        return this;
    }

    public void setNomRee(String nomRee) {
        this.nomRee = nomRee;
    }

    public LocalDate getEarData() {
        return this.earData;
    }

    public OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity earData(LocalDate earData) {
        this.setEarData(earData);
        return this;
    }

    public void setEarData(LocalDate earData) {
        this.earData = earData;
    }

    public Double getEarMaxRee() {
        return this.earMaxRee;
    }

    public OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity earMaxRee(Double earMaxRee) {
        this.setEarMaxRee(earMaxRee);
        return this;
    }

    public void setEarMaxRee(Double earMaxRee) {
        this.earMaxRee = earMaxRee;
    }

    public Double getEarVerifReeMwmes() {
        return this.earVerifReeMwmes;
    }

    public OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity earVerifReeMwmes(Double earVerifReeMwmes) {
        this.setEarVerifReeMwmes(earVerifReeMwmes);
        return this;
    }

    public void setEarVerifReeMwmes(Double earVerifReeMwmes) {
        this.earVerifReeMwmes = earVerifReeMwmes;
    }

    public Double getEarVerifReePercentual() {
        return this.earVerifReePercentual;
    }

    public OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity earVerifReePercentual(Double earVerifReePercentual) {
        this.setEarVerifReePercentual(earVerifReePercentual);
        return this;
    }

    public void setEarVerifReePercentual(Double earVerifReePercentual) {
        this.earVerifReePercentual = earVerifReePercentual;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity{" +
            "id=" + getId() +
            ", nomRee='" + getNomRee() + "'" +
            ", earData='" + getEarData() + "'" +
            ", earMaxRee=" + getEarMaxRee() +
            ", earVerifReeMwmes=" + getEarVerifReeMwmes() +
            ", earVerifReePercentual=" + getEarVerifReePercentual() +
            "}";
    }
}
