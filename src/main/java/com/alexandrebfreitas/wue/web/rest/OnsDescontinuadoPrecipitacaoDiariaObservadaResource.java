package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDescontinuadoPrecipitacaoDiariaObservadaEntity;
import com.alexandrebfreitas.wue.repository.OnsDescontinuadoPrecipitacaoDiariaObservadaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDescontinuadoPrecipitacaoDiariaObservadaEntity}.
 */
@RestController
@RequestMapping("/api/ons-descontinuado-precipitacao-diaria-observadas")
@Transactional
public class OnsDescontinuadoPrecipitacaoDiariaObservadaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDescontinuadoPrecipitacaoDiariaObservadaResource.class);

    private static final String ENTITY_NAME = "onsDescontinuadoPrecipitacaoDiariaObservada";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDescontinuadoPrecipitacaoDiariaObservadaRepository onsDescontinuadoPrecipitacaoDiariaObservadaRepository;

    private final OnsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository;

    public OnsDescontinuadoPrecipitacaoDiariaObservadaResource(
        OnsDescontinuadoPrecipitacaoDiariaObservadaRepository onsDescontinuadoPrecipitacaoDiariaObservadaRepository,
        OnsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository
    ) {
        this.onsDescontinuadoPrecipitacaoDiariaObservadaRepository = onsDescontinuadoPrecipitacaoDiariaObservadaRepository;
        this.onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository = onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository;
    }

    /**
     * {@code POST  /ons-descontinuado-precipitacao-diaria-observadas} : Create a new onsDescontinuadoPrecipitacaoDiariaObservada.
     *
     * @param onsDescontinuadoPrecipitacaoDiariaObservadaEntity the onsDescontinuadoPrecipitacaoDiariaObservadaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDescontinuadoPrecipitacaoDiariaObservadaEntity, or with status {@code 400 (Bad Request)} if the onsDescontinuadoPrecipitacaoDiariaObservada has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsDescontinuadoPrecipitacaoDiariaObservadaEntity> createOnsDescontinuadoPrecipitacaoDiariaObservada(
        @RequestBody OnsDescontinuadoPrecipitacaoDiariaObservadaEntity onsDescontinuadoPrecipitacaoDiariaObservadaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDescontinuadoPrecipitacaoDiariaObservada : {}",
            onsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );
        if (onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDescontinuadoPrecipitacaoDiariaObservada cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDescontinuadoPrecipitacaoDiariaObservadaEntity = onsDescontinuadoPrecipitacaoDiariaObservadaRepository.save(
            onsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );
        onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.index(onsDescontinuadoPrecipitacaoDiariaObservadaEntity);
        return ResponseEntity.created(
            new URI("/api/ons-descontinuado-precipitacao-diaria-observadas/" + onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId().toString()
                )
            )
            .body(onsDescontinuadoPrecipitacaoDiariaObservadaEntity);
    }

    /**
     * {@code PUT  /ons-descontinuado-precipitacao-diaria-observadas/:id} : Updates an existing onsDescontinuadoPrecipitacaoDiariaObservada.
     *
     * @param id the id of the onsDescontinuadoPrecipitacaoDiariaObservadaEntity to save.
     * @param onsDescontinuadoPrecipitacaoDiariaObservadaEntity the onsDescontinuadoPrecipitacaoDiariaObservadaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDescontinuadoPrecipitacaoDiariaObservadaEntity,
     * or with status {@code 400 (Bad Request)} if the onsDescontinuadoPrecipitacaoDiariaObservadaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDescontinuadoPrecipitacaoDiariaObservadaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsDescontinuadoPrecipitacaoDiariaObservadaEntity> updateOnsDescontinuadoPrecipitacaoDiariaObservada(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDescontinuadoPrecipitacaoDiariaObservadaEntity onsDescontinuadoPrecipitacaoDiariaObservadaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDescontinuadoPrecipitacaoDiariaObservada : {}, {}",
            id,
            onsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );
        if (onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDescontinuadoPrecipitacaoDiariaObservadaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDescontinuadoPrecipitacaoDiariaObservadaEntity = onsDescontinuadoPrecipitacaoDiariaObservadaRepository.save(
            onsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );
        onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.index(onsDescontinuadoPrecipitacaoDiariaObservadaEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId().toString()
                )
            )
            .body(onsDescontinuadoPrecipitacaoDiariaObservadaEntity);
    }

    /**
     * {@code PATCH  /ons-descontinuado-precipitacao-diaria-observadas/:id} : Partial updates given fields of an existing onsDescontinuadoPrecipitacaoDiariaObservada, field will ignore if it is null
     *
     * @param id the id of the onsDescontinuadoPrecipitacaoDiariaObservadaEntity to save.
     * @param onsDescontinuadoPrecipitacaoDiariaObservadaEntity the onsDescontinuadoPrecipitacaoDiariaObservadaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDescontinuadoPrecipitacaoDiariaObservadaEntity,
     * or with status {@code 400 (Bad Request)} if the onsDescontinuadoPrecipitacaoDiariaObservadaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDescontinuadoPrecipitacaoDiariaObservadaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDescontinuadoPrecipitacaoDiariaObservadaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsDescontinuadoPrecipitacaoDiariaObservadaEntity> partialUpdateOnsDescontinuadoPrecipitacaoDiariaObservada(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDescontinuadoPrecipitacaoDiariaObservadaEntity onsDescontinuadoPrecipitacaoDiariaObservadaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDescontinuadoPrecipitacaoDiariaObservada partially : {}, {}",
            id,
            onsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );
        if (onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDescontinuadoPrecipitacaoDiariaObservadaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDescontinuadoPrecipitacaoDiariaObservadaEntity> result = onsDescontinuadoPrecipitacaoDiariaObservadaRepository
            .findById(onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId())
            .map(existingOnsDescontinuadoPrecipitacaoDiariaObservada -> {
                if (onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getCodEstacao() != null) {
                    existingOnsDescontinuadoPrecipitacaoDiariaObservada.setCodEstacao(
                        onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getCodEstacao()
                    );
                }
                if (onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getValLatitude() != null) {
                    existingOnsDescontinuadoPrecipitacaoDiariaObservada.setValLatitude(
                        onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getValLatitude()
                    );
                }
                if (onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getValLongitude() != null) {
                    existingOnsDescontinuadoPrecipitacaoDiariaObservada.setValLongitude(
                        onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getValLongitude()
                    );
                }
                if (onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getValMedida() != null) {
                    existingOnsDescontinuadoPrecipitacaoDiariaObservada.setValMedida(
                        onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getValMedida()
                    );
                }
                if (onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getDatObservada() != null) {
                    existingOnsDescontinuadoPrecipitacaoDiariaObservada.setDatObservada(
                        onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getDatObservada()
                    );
                }

                return existingOnsDescontinuadoPrecipitacaoDiariaObservada;
            })
            .map(onsDescontinuadoPrecipitacaoDiariaObservadaRepository::save)
            .map(savedOnsDescontinuadoPrecipitacaoDiariaObservada -> {
                onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.index(savedOnsDescontinuadoPrecipitacaoDiariaObservada);
                return savedOnsDescontinuadoPrecipitacaoDiariaObservada;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-descontinuado-precipitacao-diaria-observadas} : get all the onsDescontinuadoPrecipitacaoDiariaObservadas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDescontinuadoPrecipitacaoDiariaObservadas in body.
     */
    @GetMapping("")
    public List<OnsDescontinuadoPrecipitacaoDiariaObservadaEntity> getAllOnsDescontinuadoPrecipitacaoDiariaObservadas() {
        LOG.debug("REST request to get all OnsDescontinuadoPrecipitacaoDiariaObservadas");
        return onsDescontinuadoPrecipitacaoDiariaObservadaRepository.findAll();
    }

    /**
     * {@code GET  /ons-descontinuado-precipitacao-diaria-observadas/:id} : get the "id" onsDescontinuadoPrecipitacaoDiariaObservada.
     *
     * @param id the id of the onsDescontinuadoPrecipitacaoDiariaObservadaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDescontinuadoPrecipitacaoDiariaObservadaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsDescontinuadoPrecipitacaoDiariaObservadaEntity> getOnsDescontinuadoPrecipitacaoDiariaObservada(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsDescontinuadoPrecipitacaoDiariaObservada : {}", id);
        Optional<OnsDescontinuadoPrecipitacaoDiariaObservadaEntity> onsDescontinuadoPrecipitacaoDiariaObservadaEntity =
            onsDescontinuadoPrecipitacaoDiariaObservadaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDescontinuadoPrecipitacaoDiariaObservadaEntity);
    }

    /**
     * {@code DELETE  /ons-descontinuado-precipitacao-diaria-observadas/:id} : delete the "id" onsDescontinuadoPrecipitacaoDiariaObservada.
     *
     * @param id the id of the onsDescontinuadoPrecipitacaoDiariaObservadaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDescontinuadoPrecipitacaoDiariaObservada(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDescontinuadoPrecipitacaoDiariaObservada : {}", id);
        onsDescontinuadoPrecipitacaoDiariaObservadaRepository.deleteById(id);
        onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-descontinuado-precipitacao-diaria-observadas/_search?query=:query} : search for the onsDescontinuadoPrecipitacaoDiariaObservada corresponding
     * to the query.
     *
     * @param query the query of the onsDescontinuadoPrecipitacaoDiariaObservada search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDescontinuadoPrecipitacaoDiariaObservadaEntity> searchOnsDescontinuadoPrecipitacaoDiariaObservadas(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsDescontinuadoPrecipitacaoDiariaObservadas for query {}", query);
        try {
            return StreamSupport.stream(
                onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
