package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreConjuntosEUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRelacionamentoEntreConjuntosEUsinasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreConjuntosEUsinasEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-relacionamento-entre-conjuntos-e-usinas")
@Transactional
public class OnsDadosRelacionamentoEntreConjuntosEUsinasResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosRelacionamentoEntreConjuntosEUsinasResource.class);

    private static final String ENTITY_NAME = "onsDadosRelacionamentoEntreConjuntosEUsinas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosRelacionamentoEntreConjuntosEUsinasRepository onsDadosRelacionamentoEntreConjuntosEUsinasRepository;

    private final OnsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository;

    public OnsDadosRelacionamentoEntreConjuntosEUsinasResource(
        OnsDadosRelacionamentoEntreConjuntosEUsinasRepository onsDadosRelacionamentoEntreConjuntosEUsinasRepository,
        OnsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository
    ) {
        this.onsDadosRelacionamentoEntreConjuntosEUsinasRepository = onsDadosRelacionamentoEntreConjuntosEUsinasRepository;
        this.onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository = onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-relacionamento-entre-conjuntos-e-usinas} : Create a new onsDadosRelacionamentoEntreConjuntosEUsinas.
     *
     * @param onsDadosRelacionamentoEntreConjuntosEUsinasEntity the onsDadosRelacionamentoEntreConjuntosEUsinasEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosRelacionamentoEntreConjuntosEUsinasEntity, or with status {@code 400 (Bad Request)} if the onsDadosRelacionamentoEntreConjuntosEUsinas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> createOnsDadosRelacionamentoEntreConjuntosEUsinas(
        @RequestBody OnsDadosRelacionamentoEntreConjuntosEUsinasEntity onsDadosRelacionamentoEntreConjuntosEUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosRelacionamentoEntreConjuntosEUsinas : {}",
            onsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );
        if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosRelacionamentoEntreConjuntosEUsinas cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosRelacionamentoEntreConjuntosEUsinasEntity = onsDadosRelacionamentoEntreConjuntosEUsinasRepository.save(
            onsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );
        onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.index(onsDadosRelacionamentoEntreConjuntosEUsinasEntity);
        return ResponseEntity.created(
            new URI("/api/ons-dados-relacionamento-entre-conjuntos-e-usinas/" + onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId().toString()
                )
            )
            .body(onsDadosRelacionamentoEntreConjuntosEUsinasEntity);
    }

    /**
     * {@code PUT  /ons-dados-relacionamento-entre-conjuntos-e-usinas/:id} : Updates an existing onsDadosRelacionamentoEntreConjuntosEUsinas.
     *
     * @param id the id of the onsDadosRelacionamentoEntreConjuntosEUsinasEntity to save.
     * @param onsDadosRelacionamentoEntreConjuntosEUsinasEntity the onsDadosRelacionamentoEntreConjuntosEUsinasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosRelacionamentoEntreConjuntosEUsinasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosRelacionamentoEntreConjuntosEUsinasEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosRelacionamentoEntreConjuntosEUsinasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> updateOnsDadosRelacionamentoEntreConjuntosEUsinas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosRelacionamentoEntreConjuntosEUsinasEntity onsDadosRelacionamentoEntreConjuntosEUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosRelacionamentoEntreConjuntosEUsinas : {}, {}",
            id,
            onsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );
        if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosRelacionamentoEntreConjuntosEUsinasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosRelacionamentoEntreConjuntosEUsinasEntity = onsDadosRelacionamentoEntreConjuntosEUsinasRepository.save(
            onsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );
        onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.index(onsDadosRelacionamentoEntreConjuntosEUsinasEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId().toString()
                )
            )
            .body(onsDadosRelacionamentoEntreConjuntosEUsinasEntity);
    }

    /**
     * {@code PATCH  /ons-dados-relacionamento-entre-conjuntos-e-usinas/:id} : Partial updates given fields of an existing onsDadosRelacionamentoEntreConjuntosEUsinas, field will ignore if it is null
     *
     * @param id the id of the onsDadosRelacionamentoEntreConjuntosEUsinasEntity to save.
     * @param onsDadosRelacionamentoEntreConjuntosEUsinasEntity the onsDadosRelacionamentoEntreConjuntosEUsinasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosRelacionamentoEntreConjuntosEUsinasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosRelacionamentoEntreConjuntosEUsinasEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosRelacionamentoEntreConjuntosEUsinasEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosRelacionamentoEntreConjuntosEUsinasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> partialUpdateOnsDadosRelacionamentoEntreConjuntosEUsinas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosRelacionamentoEntreConjuntosEUsinasEntity onsDadosRelacionamentoEntreConjuntosEUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosRelacionamentoEntreConjuntosEUsinas partially : {}, {}",
            id,
            onsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );
        if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosRelacionamentoEntreConjuntosEUsinasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> result = onsDadosRelacionamentoEntreConjuntosEUsinasRepository
            .findById(onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId())
            .map(existingOnsDadosRelacionamentoEntreConjuntosEUsinas -> {
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getIdSubsistema() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setIdSubsistema(
                        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getIdSubsistema()
                    );
                }
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getNomSubsistema() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setNomSubsistema(
                        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getNomSubsistema()
                    );
                }
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getEstadId() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setEstadId(
                        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getEstadId()
                    );
                }
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getNomEstado() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setNomEstado(
                        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getNomEstado()
                    );
                }
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getIdTipousina() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setIdTipousina(
                        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getIdTipousina()
                    );
                }
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getIdConjuntousina() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setIdConjuntousina(
                        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getIdConjuntousina()
                    );
                }
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getIdOnsConjunto() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setIdOnsConjunto(
                        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getIdOnsConjunto()
                    );
                }
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getIdOnsUsina() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setIdOnsUsina(
                        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getIdOnsUsina()
                    );
                }
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getNomConjunto() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setNomConjunto(
                        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getNomConjunto()
                    );
                }
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getNomUsina() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setNomUsina(
                        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getNomUsina()
                    );
                }
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getCeg() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setCeg(onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getCeg());
                }
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getDatIniciorelacionamento() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setDatIniciorelacionamento(
                        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getDatIniciorelacionamento()
                    );
                }
                if (onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getDatFimrelacionamento() != null) {
                    existingOnsDadosRelacionamentoEntreConjuntosEUsinas.setDatFimrelacionamento(
                        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getDatFimrelacionamento()
                    );
                }

                return existingOnsDadosRelacionamentoEntreConjuntosEUsinas;
            })
            .map(onsDadosRelacionamentoEntreConjuntosEUsinasRepository::save)
            .map(savedOnsDadosRelacionamentoEntreConjuntosEUsinas -> {
                onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.index(savedOnsDadosRelacionamentoEntreConjuntosEUsinas);
                return savedOnsDadosRelacionamentoEntreConjuntosEUsinas;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-relacionamento-entre-conjuntos-e-usinas} : get all the onsDadosRelacionamentoEntreConjuntosEUsinas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosRelacionamentoEntreConjuntosEUsinas in body.
     */
    @GetMapping("")
    public List<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> getAllOnsDadosRelacionamentoEntreConjuntosEUsinas() {
        LOG.debug("REST request to get all OnsDadosRelacionamentoEntreConjuntosEUsinas");
        return onsDadosRelacionamentoEntreConjuntosEUsinasRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-relacionamento-entre-conjuntos-e-usinas/:id} : get the "id" onsDadosRelacionamentoEntreConjuntosEUsinas.
     *
     * @param id the id of the onsDadosRelacionamentoEntreConjuntosEUsinasEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosRelacionamentoEntreConjuntosEUsinasEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> getOnsDadosRelacionamentoEntreConjuntosEUsinas(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsDadosRelacionamentoEntreConjuntosEUsinas : {}", id);
        Optional<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> onsDadosRelacionamentoEntreConjuntosEUsinasEntity =
            onsDadosRelacionamentoEntreConjuntosEUsinasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosRelacionamentoEntreConjuntosEUsinasEntity);
    }

    /**
     * {@code DELETE  /ons-dados-relacionamento-entre-conjuntos-e-usinas/:id} : delete the "id" onsDadosRelacionamentoEntreConjuntosEUsinas.
     *
     * @param id the id of the onsDadosRelacionamentoEntreConjuntosEUsinasEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosRelacionamentoEntreConjuntosEUsinas(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosRelacionamentoEntreConjuntosEUsinas : {}", id);
        onsDadosRelacionamentoEntreConjuntosEUsinasRepository.deleteById(id);
        onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-relacionamento-entre-conjuntos-e-usinas/_search?query=:query} : search for the onsDadosRelacionamentoEntreConjuntosEUsinas corresponding
     * to the query.
     *
     * @param query the query of the onsDadosRelacionamentoEntreConjuntosEUsinas search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> searchOnsDadosRelacionamentoEntreConjuntosEUsinas(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsDadosRelacionamentoEntreConjuntosEUsinas for query {}", query);
        try {
            return StreamSupport.stream(
                onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
