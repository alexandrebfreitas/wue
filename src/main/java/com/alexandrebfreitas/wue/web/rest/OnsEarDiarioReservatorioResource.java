package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsEarDiarioReservatorioEntity;
import com.alexandrebfreitas.wue.repository.OnsEarDiarioReservatorioRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEarDiarioReservatorioSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsEarDiarioReservatorioEntity}.
 */
@RestController
@RequestMapping("/api/ons-ear-diario-reservatorios")
@Transactional
public class OnsEarDiarioReservatorioResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsEarDiarioReservatorioResource.class);

    private static final String ENTITY_NAME = "onsEarDiarioReservatorio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsEarDiarioReservatorioRepository onsEarDiarioReservatorioRepository;

    private final OnsEarDiarioReservatorioSearchRepository onsEarDiarioReservatorioSearchRepository;

    public OnsEarDiarioReservatorioResource(
        OnsEarDiarioReservatorioRepository onsEarDiarioReservatorioRepository,
        OnsEarDiarioReservatorioSearchRepository onsEarDiarioReservatorioSearchRepository
    ) {
        this.onsEarDiarioReservatorioRepository = onsEarDiarioReservatorioRepository;
        this.onsEarDiarioReservatorioSearchRepository = onsEarDiarioReservatorioSearchRepository;
    }

    /**
     * {@code POST  /ons-ear-diario-reservatorios} : Create a new onsEarDiarioReservatorio.
     *
     * @param onsEarDiarioReservatorioEntity the onsEarDiarioReservatorioEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsEarDiarioReservatorioEntity, or with status {@code 400 (Bad Request)} if the onsEarDiarioReservatorio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsEarDiarioReservatorioEntity> createOnsEarDiarioReservatorio(
        @RequestBody OnsEarDiarioReservatorioEntity onsEarDiarioReservatorioEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsEarDiarioReservatorio : {}", onsEarDiarioReservatorioEntity);
        if (onsEarDiarioReservatorioEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsEarDiarioReservatorio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsEarDiarioReservatorioEntity = onsEarDiarioReservatorioRepository.save(onsEarDiarioReservatorioEntity);
        onsEarDiarioReservatorioSearchRepository.index(onsEarDiarioReservatorioEntity);
        return ResponseEntity.created(new URI("/api/ons-ear-diario-reservatorios/" + onsEarDiarioReservatorioEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsEarDiarioReservatorioEntity.getId().toString())
            )
            .body(onsEarDiarioReservatorioEntity);
    }

    /**
     * {@code PUT  /ons-ear-diario-reservatorios/:id} : Updates an existing onsEarDiarioReservatorio.
     *
     * @param id the id of the onsEarDiarioReservatorioEntity to save.
     * @param onsEarDiarioReservatorioEntity the onsEarDiarioReservatorioEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEarDiarioReservatorioEntity,
     * or with status {@code 400 (Bad Request)} if the onsEarDiarioReservatorioEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsEarDiarioReservatorioEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsEarDiarioReservatorioEntity> updateOnsEarDiarioReservatorio(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEarDiarioReservatorioEntity onsEarDiarioReservatorioEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsEarDiarioReservatorio : {}, {}", id, onsEarDiarioReservatorioEntity);
        if (onsEarDiarioReservatorioEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEarDiarioReservatorioEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEarDiarioReservatorioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsEarDiarioReservatorioEntity = onsEarDiarioReservatorioRepository.save(onsEarDiarioReservatorioEntity);
        onsEarDiarioReservatorioSearchRepository.index(onsEarDiarioReservatorioEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEarDiarioReservatorioEntity.getId().toString())
            )
            .body(onsEarDiarioReservatorioEntity);
    }

    /**
     * {@code PATCH  /ons-ear-diario-reservatorios/:id} : Partial updates given fields of an existing onsEarDiarioReservatorio, field will ignore if it is null
     *
     * @param id the id of the onsEarDiarioReservatorioEntity to save.
     * @param onsEarDiarioReservatorioEntity the onsEarDiarioReservatorioEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEarDiarioReservatorioEntity,
     * or with status {@code 400 (Bad Request)} if the onsEarDiarioReservatorioEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsEarDiarioReservatorioEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsEarDiarioReservatorioEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsEarDiarioReservatorioEntity> partialUpdateOnsEarDiarioReservatorio(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEarDiarioReservatorioEntity onsEarDiarioReservatorioEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsEarDiarioReservatorio partially : {}, {}", id, onsEarDiarioReservatorioEntity);
        if (onsEarDiarioReservatorioEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEarDiarioReservatorioEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEarDiarioReservatorioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsEarDiarioReservatorioEntity> result = onsEarDiarioReservatorioRepository
            .findById(onsEarDiarioReservatorioEntity.getId())
            .map(existingOnsEarDiarioReservatorio -> {
                if (onsEarDiarioReservatorioEntity.getIdSubsistemaJusante() != null) {
                    existingOnsEarDiarioReservatorio.setIdSubsistemaJusante(onsEarDiarioReservatorioEntity.getIdSubsistemaJusante());
                }
                if (onsEarDiarioReservatorioEntity.getNomSubsistemaJusante() != null) {
                    existingOnsEarDiarioReservatorio.setNomSubsistemaJusante(onsEarDiarioReservatorioEntity.getNomSubsistemaJusante());
                }
                if (onsEarDiarioReservatorioEntity.getEarData() != null) {
                    existingOnsEarDiarioReservatorio.setEarData(onsEarDiarioReservatorioEntity.getEarData());
                }
                if (onsEarDiarioReservatorioEntity.getEarReservatorioSubsistemaProprioMwmes() != null) {
                    existingOnsEarDiarioReservatorio.setEarReservatorioSubsistemaProprioMwmes(
                        onsEarDiarioReservatorioEntity.getEarReservatorioSubsistemaProprioMwmes()
                    );
                }
                if (onsEarDiarioReservatorioEntity.getEarReservatorioSubsistemaJusanteMwmes() != null) {
                    existingOnsEarDiarioReservatorio.setEarReservatorioSubsistemaJusanteMwmes(
                        onsEarDiarioReservatorioEntity.getEarReservatorioSubsistemaJusanteMwmes()
                    );
                }
                if (onsEarDiarioReservatorioEntity.getEarmaxReservatorioSubsistemaProprioMwmes() != null) {
                    existingOnsEarDiarioReservatorio.setEarmaxReservatorioSubsistemaProprioMwmes(
                        onsEarDiarioReservatorioEntity.getEarmaxReservatorioSubsistemaProprioMwmes()
                    );
                }
                if (onsEarDiarioReservatorioEntity.getEarmaxReservatorioSubsistemaJusanteMwmes() != null) {
                    existingOnsEarDiarioReservatorio.setEarmaxReservatorioSubsistemaJusanteMwmes(
                        onsEarDiarioReservatorioEntity.getEarmaxReservatorioSubsistemaJusanteMwmes()
                    );
                }
                if (onsEarDiarioReservatorioEntity.getEarReservatorioPercentual() != null) {
                    existingOnsEarDiarioReservatorio.setEarReservatorioPercentual(
                        onsEarDiarioReservatorioEntity.getEarReservatorioPercentual()
                    );
                }
                if (onsEarDiarioReservatorioEntity.getEarTotalMwmes() != null) {
                    existingOnsEarDiarioReservatorio.setEarTotalMwmes(onsEarDiarioReservatorioEntity.getEarTotalMwmes());
                }
                if (onsEarDiarioReservatorioEntity.getEarMaximaTotalMwmes() != null) {
                    existingOnsEarDiarioReservatorio.setEarMaximaTotalMwmes(onsEarDiarioReservatorioEntity.getEarMaximaTotalMwmes());
                }
                if (onsEarDiarioReservatorioEntity.getValContribearbacia() != null) {
                    existingOnsEarDiarioReservatorio.setValContribearbacia(onsEarDiarioReservatorioEntity.getValContribearbacia());
                }
                if (onsEarDiarioReservatorioEntity.getValContribearmaxbacia() != null) {
                    existingOnsEarDiarioReservatorio.setValContribearmaxbacia(onsEarDiarioReservatorioEntity.getValContribearmaxbacia());
                }
                if (onsEarDiarioReservatorioEntity.getValContribearsubsistema() != null) {
                    existingOnsEarDiarioReservatorio.setValContribearsubsistema(
                        onsEarDiarioReservatorioEntity.getValContribearsubsistema()
                    );
                }
                if (onsEarDiarioReservatorioEntity.getValContribearmaxsubsistema() != null) {
                    existingOnsEarDiarioReservatorio.setValContribearmaxsubsistema(
                        onsEarDiarioReservatorioEntity.getValContribearmaxsubsistema()
                    );
                }
                if (onsEarDiarioReservatorioEntity.getValContribearsubsistemajusante() != null) {
                    existingOnsEarDiarioReservatorio.setValContribearsubsistemajusante(
                        onsEarDiarioReservatorioEntity.getValContribearsubsistemajusante()
                    );
                }
                if (onsEarDiarioReservatorioEntity.getValContribearmaxsubsistemajusante() != null) {
                    existingOnsEarDiarioReservatorio.setValContribearmaxsubsistemajusante(
                        onsEarDiarioReservatorioEntity.getValContribearmaxsubsistemajusante()
                    );
                }
                if (onsEarDiarioReservatorioEntity.getValContribearsin() != null) {
                    existingOnsEarDiarioReservatorio.setValContribearsin(onsEarDiarioReservatorioEntity.getValContribearsin());
                }
                if (onsEarDiarioReservatorioEntity.getValContribearmaxsin() != null) {
                    existingOnsEarDiarioReservatorio.setValContribearmaxsin(onsEarDiarioReservatorioEntity.getValContribearmaxsin());
                }

                return existingOnsEarDiarioReservatorio;
            })
            .map(onsEarDiarioReservatorioRepository::save)
            .map(savedOnsEarDiarioReservatorio -> {
                onsEarDiarioReservatorioSearchRepository.index(savedOnsEarDiarioReservatorio);
                return savedOnsEarDiarioReservatorio;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEarDiarioReservatorioEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-ear-diario-reservatorios} : get all the onsEarDiarioReservatorios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsEarDiarioReservatorios in body.
     */
    @GetMapping("")
    public List<OnsEarDiarioReservatorioEntity> getAllOnsEarDiarioReservatorios() {
        LOG.debug("REST request to get all OnsEarDiarioReservatorios");
        return onsEarDiarioReservatorioRepository.findAll();
    }

    /**
     * {@code GET  /ons-ear-diario-reservatorios/:id} : get the "id" onsEarDiarioReservatorio.
     *
     * @param id the id of the onsEarDiarioReservatorioEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsEarDiarioReservatorioEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsEarDiarioReservatorioEntity> getOnsEarDiarioReservatorio(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsEarDiarioReservatorio : {}", id);
        Optional<OnsEarDiarioReservatorioEntity> onsEarDiarioReservatorioEntity = onsEarDiarioReservatorioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsEarDiarioReservatorioEntity);
    }

    /**
     * {@code DELETE  /ons-ear-diario-reservatorios/:id} : delete the "id" onsEarDiarioReservatorio.
     *
     * @param id the id of the onsEarDiarioReservatorioEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsEarDiarioReservatorio(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsEarDiarioReservatorio : {}", id);
        onsEarDiarioReservatorioRepository.deleteById(id);
        onsEarDiarioReservatorioSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-ear-diario-reservatorios/_search?query=:query} : search for the onsEarDiarioReservatorio corresponding
     * to the query.
     *
     * @param query the query of the onsEarDiarioReservatorio search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsEarDiarioReservatorioEntity> searchOnsEarDiarioReservatorios(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsEarDiarioReservatorios for query {}", query);
        try {
            return StreamSupport.stream(onsEarDiarioReservatorioSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
