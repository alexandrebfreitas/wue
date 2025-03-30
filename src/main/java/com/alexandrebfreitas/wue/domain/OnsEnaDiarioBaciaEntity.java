package com.alexandrebfreitas.wue.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OnsEnaDiarioBaciaEntity.
 */
@Entity
@Table(name = "OnsTable35")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "onsenadiariobacia")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OnsEnaDiarioBaciaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ena_armazenavel_bacia_percentualmlt")
    private Double enaArmazenavelBaciaPercentualmlt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OnsEnaDiarioBaciaEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getEnaArmazenavelBaciaPercentualmlt() {
        return this.enaArmazenavelBaciaPercentualmlt;
    }

    public OnsEnaDiarioBaciaEntity enaArmazenavelBaciaPercentualmlt(Double enaArmazenavelBaciaPercentualmlt) {
        this.setEnaArmazenavelBaciaPercentualmlt(enaArmazenavelBaciaPercentualmlt);
        return this;
    }

    public void setEnaArmazenavelBaciaPercentualmlt(Double enaArmazenavelBaciaPercentualmlt) {
        this.enaArmazenavelBaciaPercentualmlt = enaArmazenavelBaciaPercentualmlt;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OnsEnaDiarioBaciaEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((OnsEnaDiarioBaciaEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OnsEnaDiarioBaciaEntity{" +
            "id=" + getId() +
            ", enaArmazenavelBaciaPercentualmlt=" + getEnaArmazenavelBaciaPercentualmlt() +
            "}";
    }
}
