package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.
 */
@Entity
@Table(name = "OnsTable38")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsenadiarioreereservatorioequivalenteenergia")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ena_armazenavel_ree_percentualmlt")
    private Double enaArmazenavelReePercentualmlt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getEnaArmazenavelReePercentualmlt() {
        return this.enaArmazenavelReePercentualmlt;
    }

    public OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity enaArmazenavelReePercentualmlt(Double enaArmazenavelReePercentualmlt) {
        this.setEnaArmazenavelReePercentualmlt(enaArmazenavelReePercentualmlt);
        return this;
    }

    public void setEnaArmazenavelReePercentualmlt(Double enaArmazenavelReePercentualmlt) {
        this.enaArmazenavelReePercentualmlt = enaArmazenavelReePercentualmlt;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity{" +
            "id=" + getId() +
            ", enaArmazenavelReePercentualmlt=" + getEnaArmazenavelReePercentualmlt() +
            "}";
    }
}
