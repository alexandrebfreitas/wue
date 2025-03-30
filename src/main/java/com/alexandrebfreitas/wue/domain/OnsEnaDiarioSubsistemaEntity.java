package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsEnaDiarioSubsistemaEntity.
 */
@Entity
@Table(name = "OnsTable37")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsenadiariosubsistema")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsEnaDiarioSubsistemaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ena_armazenavel_regiao_percentualmlt")
    private Double enaArmazenavelRegiaoPercentualmlt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsEnaDiarioSubsistemaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getEnaArmazenavelRegiaoPercentualmlt() {
        return this.enaArmazenavelRegiaoPercentualmlt;
    }

    public OnsEnaDiarioSubsistemaEntity enaArmazenavelRegiaoPercentualmlt(Double enaArmazenavelRegiaoPercentualmlt) {
        this.setEnaArmazenavelRegiaoPercentualmlt(enaArmazenavelRegiaoPercentualmlt);
        return this;
    }

    public void setEnaArmazenavelRegiaoPercentualmlt(Double enaArmazenavelRegiaoPercentualmlt) {
        this.enaArmazenavelRegiaoPercentualmlt = enaArmazenavelRegiaoPercentualmlt;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsEnaDiarioSubsistemaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsEnaDiarioSubsistemaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsEnaDiarioSubsistemaEntity{" +
            "id=" + getId() +
            ", enaArmazenavelRegiaoPercentualmlt=" + getEnaArmazenavelRegiaoPercentualmlt() +
            "}";
    }
}
