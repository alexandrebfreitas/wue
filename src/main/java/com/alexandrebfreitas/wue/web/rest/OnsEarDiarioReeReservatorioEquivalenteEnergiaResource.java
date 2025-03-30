package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity;
import com.alexandrebfreitas.wue.repository.OnsEarDiarioReeReservatorioEquivalenteEnergiaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity}.
 */
@RestController
@RequestMapping("/api/ons-ear-diario-ree-reservatorio-equivalente-energias")
@Transactional
public class OnsEarDiarioReeReservatorioEquivalenteEnergiaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsEarDiarioReeReservatorioEquivalenteEnergiaResource.class);

    private static final String ENTITY_NAME = "onsEarDiarioReeReservatorioEquivalenteEnergia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsEarDiarioReeReservatorioEquivalenteEnergiaRepository onsEarDiarioReeReservatorioEquivalenteEnergiaRepository;

    private final OnsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository;

    public OnsEarDiarioReeReservatorioEquivalenteEnergiaResource(
        OnsEarDiarioReeReservatorioEquivalenteEnergiaRepository onsEarDiarioReeReservatorioEquivalenteEnergiaRepository,
        OnsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository
    ) {
        this.onsEarDiarioReeReservatorioEquivalenteEnergiaRepository = onsEarDiarioReeReservatorioEquivalenteEnergiaRepository;
        this.onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository = onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository;
    }

    /**
     * {@code POST  /ons-ear-diario-ree-reservatorio-equivalente-energias} : Create a new onsEarDiarioReeReservatorioEquivalenteEnergia.
     *
     * @param onsEarDiarioReeReservatorioEquivalenteEnergiaEntity the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsEarDiarioReeReservatorioEquivalenteEnergiaEntity, or with status {@code 400 (Bad Request)} if the onsEarDiarioReeReservatorioEquivalenteEnergia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity> createOnsEarDiarioReeReservatorioEquivalenteEnergia(
        @RequestBody OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsEarDiarioReeReservatorioEquivalenteEnergia : {}",
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        if (onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsEarDiarioReeReservatorioEquivalenteEnergia cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity = onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.save(
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.index(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity);
        return ResponseEntity.created(
            new URI(
                "/api/ons-ear-diario-ree-reservatorio-equivalente-energias/" + onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId().toString()
                )
            )
            .body(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity);
    }

    /**
     * {@code PUT  /ons-ear-diario-ree-reservatorio-equivalente-energias/:id} : Updates an existing onsEarDiarioReeReservatorioEquivalenteEnergia.
     *
     * @param id the id of the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity to save.
     * @param onsEarDiarioReeReservatorioEquivalenteEnergiaEntity the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEarDiarioReeReservatorioEquivalenteEnergiaEntity,
     * or with status {@code 400 (Bad Request)} if the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity> updateOnsEarDiarioReeReservatorioEquivalenteEnergia(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsEarDiarioReeReservatorioEquivalenteEnergia : {}, {}",
            id,
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        if (onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity = onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.save(
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.index(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId().toString()
                )
            )
            .body(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity);
    }

    /**
     * {@code PATCH  /ons-ear-diario-ree-reservatorio-equivalente-energias/:id} : Partial updates given fields of an existing onsEarDiarioReeReservatorioEquivalenteEnergia, field will ignore if it is null
     *
     * @param id the id of the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity to save.
     * @param onsEarDiarioReeReservatorioEquivalenteEnergiaEntity the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEarDiarioReeReservatorioEquivalenteEnergiaEntity,
     * or with status {@code 400 (Bad Request)} if the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity> partialUpdateOnsEarDiarioReeReservatorioEquivalenteEnergia(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsEarDiarioReeReservatorioEquivalenteEnergia partially : {}, {}",
            id,
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        if (onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity> result = onsEarDiarioReeReservatorioEquivalenteEnergiaRepository
            .findById(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
            .map(existingOnsEarDiarioReeReservatorioEquivalenteEnergia -> {
                if (onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getNomRee() != null) {
                    existingOnsEarDiarioReeReservatorioEquivalenteEnergia.setNomRee(
                        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getNomRee()
                    );
                }
                if (onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getEarData() != null) {
                    existingOnsEarDiarioReeReservatorioEquivalenteEnergia.setEarData(
                        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getEarData()
                    );
                }
                if (onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getEarMaxRee() != null) {
                    existingOnsEarDiarioReeReservatorioEquivalenteEnergia.setEarMaxRee(
                        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getEarMaxRee()
                    );
                }
                if (onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getEarVerifReeMwmes() != null) {
                    existingOnsEarDiarioReeReservatorioEquivalenteEnergia.setEarVerifReeMwmes(
                        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getEarVerifReeMwmes()
                    );
                }
                if (onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getEarVerifReePercentual() != null) {
                    existingOnsEarDiarioReeReservatorioEquivalenteEnergia.setEarVerifReePercentual(
                        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getEarVerifReePercentual()
                    );
                }

                return existingOnsEarDiarioReeReservatorioEquivalenteEnergia;
            })
            .map(onsEarDiarioReeReservatorioEquivalenteEnergiaRepository::save)
            .map(savedOnsEarDiarioReeReservatorioEquivalenteEnergia -> {
                onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.index(savedOnsEarDiarioReeReservatorioEquivalenteEnergia);
                return savedOnsEarDiarioReeReservatorioEquivalenteEnergia;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-ear-diario-ree-reservatorio-equivalente-energias} : get all the onsEarDiarioReeReservatorioEquivalenteEnergias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsEarDiarioReeReservatorioEquivalenteEnergias in body.
     */
    @GetMapping("")
    public List<OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity> getAllOnsEarDiarioReeReservatorioEquivalenteEnergias() {
        LOG.debug("REST request to get all OnsEarDiarioReeReservatorioEquivalenteEnergias");
        return onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.findAll();
    }

    /**
     * {@code GET  /ons-ear-diario-ree-reservatorio-equivalente-energias/:id} : get the "id" onsEarDiarioReeReservatorioEquivalenteEnergia.
     *
     * @param id the id of the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity> getOnsEarDiarioReeReservatorioEquivalenteEnergia(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsEarDiarioReeReservatorioEquivalenteEnergia : {}", id);
        Optional<OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity> onsEarDiarioReeReservatorioEquivalenteEnergiaEntity =
            onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity);
    }

    /**
     * {@code DELETE  /ons-ear-diario-ree-reservatorio-equivalente-energias/:id} : delete the "id" onsEarDiarioReeReservatorioEquivalenteEnergia.
     *
     * @param id the id of the onsEarDiarioReeReservatorioEquivalenteEnergiaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsEarDiarioReeReservatorioEquivalenteEnergia(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsEarDiarioReeReservatorioEquivalenteEnergia : {}", id);
        onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.deleteById(id);
        onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-ear-diario-ree-reservatorio-equivalente-energias/_search?query=:query} : search for the onsEarDiarioReeReservatorioEquivalenteEnergia corresponding
     * to the query.
     *
     * @param query the query of the onsEarDiarioReeReservatorioEquivalenteEnergia search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity> searchOnsEarDiarioReeReservatorioEquivalenteEnergias(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsEarDiarioReeReservatorioEquivalenteEnergias for query {}", query);
        try {
            return StreamSupport.stream(
                onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
