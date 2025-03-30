package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity;
import com.alexandrebfreitas.wue.repository.OnsEnaDiarioReeReservatorioEquivalenteEnergiaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository;
import com.alexandrebfreitas.wue.web.rest.errors.BadRequestAlertException;
import com.alexandrebfreitas.wue.web.rest.errors.ElasticsearchExceptionMapper;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity}.
 */
@RestController
@RequestMapping("/api/ons-ena-diario-ree-reservatorio-equivalente-energias")
@Transactional
public class OnsEnaDiarioReeReservatorioEquivalenteEnergiaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsEnaDiarioReeReservatorioEquivalenteEnergiaResource.class);

    private static final String ENTITY_NAME = "onsEnaDiarioReeReservatorioEquivalenteEnergia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsEnaDiarioReeReservatorioEquivalenteEnergiaRepository onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository;

    private final OnsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository;

    public OnsEnaDiarioReeReservatorioEquivalenteEnergiaResource(
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaRepository onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository,
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository
    ) {
        this.onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository = onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository;
        this.onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository = onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository;
    }

    /**
     * {@code POST  /ons-ena-diario-ree-reservatorio-equivalente-energias} : Create a new onsEnaDiarioReeReservatorioEquivalenteEnergia.
     *
     * @param onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity, or with status {@code 400 (Bad Request)} if the onsEnaDiarioReeReservatorioEquivalenteEnergia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> createOnsEnaDiarioReeReservatorioEquivalenteEnergia(
        @RequestBody OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsEnaDiarioReeReservatorioEquivalenteEnergia : {}",
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        if (onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsEnaDiarioReeReservatorioEquivalenteEnergia cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.save(
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.index(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity);
        return ResponseEntity.created(
            new URI(
                "/api/ons-ena-diario-ree-reservatorio-equivalente-energias/" + onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId().toString()
                )
            )
            .body(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity);
    }

    /**
     * {@code PUT  /ons-ena-diario-ree-reservatorio-equivalente-energias/:id} : Updates an existing onsEnaDiarioReeReservatorioEquivalenteEnergia.
     *
     * @param id the id of the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity to save.
     * @param onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity,
     * or with status {@code 400 (Bad Request)} if the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> updateOnsEnaDiarioReeReservatorioEquivalenteEnergia(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsEnaDiarioReeReservatorioEquivalenteEnergia : {}, {}",
            id,
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        if (onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.save(
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.index(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId().toString()
                )
            )
            .body(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity);
    }

    /**
     * {@code PATCH  /ons-ena-diario-ree-reservatorio-equivalente-energias/:id} : Partial updates given fields of an existing onsEnaDiarioReeReservatorioEquivalenteEnergia, field will ignore if it is null
     *
     * @param id the id of the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity to save.
     * @param onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity,
     * or with status {@code 400 (Bad Request)} if the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> partialUpdateOnsEnaDiarioReeReservatorioEquivalenteEnergia(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsEnaDiarioReeReservatorioEquivalenteEnergia partially : {}, {}",
            id,
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        if (onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> result = onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository
            .findById(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
            .map(existingOnsEnaDiarioReeReservatorioEquivalenteEnergia -> {
                if (onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getEnaArmazenavelReePercentualmlt() != null) {
                    existingOnsEnaDiarioReeReservatorioEquivalenteEnergia.setEnaArmazenavelReePercentualmlt(
                        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getEnaArmazenavelReePercentualmlt()
                    );
                }

                return existingOnsEnaDiarioReeReservatorioEquivalenteEnergia;
            })
            .map(onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository::save)
            .map(savedOnsEnaDiarioReeReservatorioEquivalenteEnergia -> {
                onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.index(savedOnsEnaDiarioReeReservatorioEquivalenteEnergia);
                return savedOnsEnaDiarioReeReservatorioEquivalenteEnergia;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-ena-diario-ree-reservatorio-equivalente-energias} : get all the onsEnaDiarioReeReservatorioEquivalenteEnergias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsEnaDiarioReeReservatorioEquivalenteEnergias in body.
     */
    @GetMapping("")
    public List<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> getAllOnsEnaDiarioReeReservatorioEquivalenteEnergias() {
        LOG.debug("REST request to get all OnsEnaDiarioReeReservatorioEquivalenteEnergias");
        return onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.findAll();
    }

    /**
     * {@code GET  /ons-ena-diario-ree-reservatorio-equivalente-energias/:id} : get the "id" onsEnaDiarioReeReservatorioEquivalenteEnergia.
     *
     * @param id the id of the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> getOnsEnaDiarioReeReservatorioEquivalenteEnergia(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsEnaDiarioReeReservatorioEquivalenteEnergia : {}", id);
        Optional<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity =
            onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity);
    }

    /**
     * {@code DELETE  /ons-ena-diario-ree-reservatorio-equivalente-energias/:id} : delete the "id" onsEnaDiarioReeReservatorioEquivalenteEnergia.
     *
     * @param id the id of the onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsEnaDiarioReeReservatorioEquivalenteEnergia(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsEnaDiarioReeReservatorioEquivalenteEnergia : {}", id);
        onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.deleteById(id);
        onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-ena-diario-ree-reservatorio-equivalente-energias/_search?query=:query} : search for the onsEnaDiarioReeReservatorioEquivalenteEnergia corresponding
     * to the query.
     *
     * @param query the query of the onsEnaDiarioReeReservatorioEquivalenteEnergia search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> searchOnsEnaDiarioReeReservatorioEquivalenteEnergias(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsEnaDiarioReeReservatorioEquivalenteEnergias for query {}", query);
        try {
            return StreamSupport.stream(
                onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
