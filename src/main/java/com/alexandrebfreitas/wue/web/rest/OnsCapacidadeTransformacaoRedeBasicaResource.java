package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsCapacidadeTransformacaoRedeBasicaEntity;
import com.alexandrebfreitas.wue.repository.OnsCapacidadeTransformacaoRedeBasicaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCapacidadeTransformacaoRedeBasicaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsCapacidadeTransformacaoRedeBasicaEntity}.
 */
@RestController
@RequestMapping("/api/ons-capacidade-transformacao-rede-basicas")
@Transactional
public class OnsCapacidadeTransformacaoRedeBasicaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsCapacidadeTransformacaoRedeBasicaResource.class);

    private static final String ENTITY_NAME = "onsCapacidadeTransformacaoRedeBasica";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsCapacidadeTransformacaoRedeBasicaRepository onsCapacidadeTransformacaoRedeBasicaRepository;

    private final OnsCapacidadeTransformacaoRedeBasicaSearchRepository onsCapacidadeTransformacaoRedeBasicaSearchRepository;

    public OnsCapacidadeTransformacaoRedeBasicaResource(
        OnsCapacidadeTransformacaoRedeBasicaRepository onsCapacidadeTransformacaoRedeBasicaRepository,
        OnsCapacidadeTransformacaoRedeBasicaSearchRepository onsCapacidadeTransformacaoRedeBasicaSearchRepository
    ) {
        this.onsCapacidadeTransformacaoRedeBasicaRepository = onsCapacidadeTransformacaoRedeBasicaRepository;
        this.onsCapacidadeTransformacaoRedeBasicaSearchRepository = onsCapacidadeTransformacaoRedeBasicaSearchRepository;
    }

    /**
     * {@code POST  /ons-capacidade-transformacao-rede-basicas} : Create a new onsCapacidadeTransformacaoRedeBasica.
     *
     * @param onsCapacidadeTransformacaoRedeBasicaEntity the onsCapacidadeTransformacaoRedeBasicaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsCapacidadeTransformacaoRedeBasicaEntity, or with status {@code 400 (Bad Request)} if the onsCapacidadeTransformacaoRedeBasica has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsCapacidadeTransformacaoRedeBasicaEntity> createOnsCapacidadeTransformacaoRedeBasica(
        @RequestBody OnsCapacidadeTransformacaoRedeBasicaEntity onsCapacidadeTransformacaoRedeBasicaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsCapacidadeTransformacaoRedeBasica : {}", onsCapacidadeTransformacaoRedeBasicaEntity);
        if (onsCapacidadeTransformacaoRedeBasicaEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsCapacidadeTransformacaoRedeBasica cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsCapacidadeTransformacaoRedeBasicaEntity = onsCapacidadeTransformacaoRedeBasicaRepository.save(
            onsCapacidadeTransformacaoRedeBasicaEntity
        );
        onsCapacidadeTransformacaoRedeBasicaSearchRepository.index(onsCapacidadeTransformacaoRedeBasicaEntity);
        return ResponseEntity.created(
            new URI("/api/ons-capacidade-transformacao-rede-basicas/" + onsCapacidadeTransformacaoRedeBasicaEntity.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsCapacidadeTransformacaoRedeBasicaEntity.getId().toString()
                )
            )
            .body(onsCapacidadeTransformacaoRedeBasicaEntity);
    }

    /**
     * {@code PUT  /ons-capacidade-transformacao-rede-basicas/:id} : Updates an existing onsCapacidadeTransformacaoRedeBasica.
     *
     * @param id the id of the onsCapacidadeTransformacaoRedeBasicaEntity to save.
     * @param onsCapacidadeTransformacaoRedeBasicaEntity the onsCapacidadeTransformacaoRedeBasicaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCapacidadeTransformacaoRedeBasicaEntity,
     * or with status {@code 400 (Bad Request)} if the onsCapacidadeTransformacaoRedeBasicaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsCapacidadeTransformacaoRedeBasicaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsCapacidadeTransformacaoRedeBasicaEntity> updateOnsCapacidadeTransformacaoRedeBasica(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCapacidadeTransformacaoRedeBasicaEntity onsCapacidadeTransformacaoRedeBasicaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsCapacidadeTransformacaoRedeBasica : {}, {}", id, onsCapacidadeTransformacaoRedeBasicaEntity);
        if (onsCapacidadeTransformacaoRedeBasicaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCapacidadeTransformacaoRedeBasicaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCapacidadeTransformacaoRedeBasicaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsCapacidadeTransformacaoRedeBasicaEntity = onsCapacidadeTransformacaoRedeBasicaRepository.save(
            onsCapacidadeTransformacaoRedeBasicaEntity
        );
        onsCapacidadeTransformacaoRedeBasicaSearchRepository.index(onsCapacidadeTransformacaoRedeBasicaEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsCapacidadeTransformacaoRedeBasicaEntity.getId().toString()
                )
            )
            .body(onsCapacidadeTransformacaoRedeBasicaEntity);
    }

    /**
     * {@code PATCH  /ons-capacidade-transformacao-rede-basicas/:id} : Partial updates given fields of an existing onsCapacidadeTransformacaoRedeBasica, field will ignore if it is null
     *
     * @param id the id of the onsCapacidadeTransformacaoRedeBasicaEntity to save.
     * @param onsCapacidadeTransformacaoRedeBasicaEntity the onsCapacidadeTransformacaoRedeBasicaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCapacidadeTransformacaoRedeBasicaEntity,
     * or with status {@code 400 (Bad Request)} if the onsCapacidadeTransformacaoRedeBasicaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsCapacidadeTransformacaoRedeBasicaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsCapacidadeTransformacaoRedeBasicaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsCapacidadeTransformacaoRedeBasicaEntity> partialUpdateOnsCapacidadeTransformacaoRedeBasica(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCapacidadeTransformacaoRedeBasicaEntity onsCapacidadeTransformacaoRedeBasicaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsCapacidadeTransformacaoRedeBasica partially : {}, {}",
            id,
            onsCapacidadeTransformacaoRedeBasicaEntity
        );
        if (onsCapacidadeTransformacaoRedeBasicaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCapacidadeTransformacaoRedeBasicaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCapacidadeTransformacaoRedeBasicaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsCapacidadeTransformacaoRedeBasicaEntity> result = onsCapacidadeTransformacaoRedeBasicaRepository
            .findById(onsCapacidadeTransformacaoRedeBasicaEntity.getId())
            .map(existingOnsCapacidadeTransformacaoRedeBasica -> {
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getIdSubsistema() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setIdSubsistema(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getIdSubsistema()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getNomSubsistema() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setNomSubsistema(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getNomSubsistema()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getIdEstado() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setIdEstado(onsCapacidadeTransformacaoRedeBasicaEntity.getIdEstado());
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getNomEstado() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setNomEstado(onsCapacidadeTransformacaoRedeBasicaEntity.getNomEstado());
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getNomTipotransformador() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setNomTipotransformador(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getNomTipotransformador()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getNomAgenteproprietario() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setNomAgenteproprietario(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getNomAgenteproprietario()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getNomSubestacao() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setNomSubestacao(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getNomSubestacao()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getNomTransformador() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setNomTransformador(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getNomTransformador()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getCodEquipamento() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setCodEquipamento(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getCodEquipamento()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getDatEntradaoperacao() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setDatEntradaoperacao(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getDatEntradaoperacao()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getDatDesativacao() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setDatDesativacao(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getDatDesativacao()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getValTensaoprimarioKv() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setValTensaoprimarioKv(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getValTensaoprimarioKv()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getValTensaosecundarioKv() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setValTensaosecundarioKv(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getValTensaosecundarioKv()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getValTensaoterciarioKv() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setValTensaoterciarioKv(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getValTensaoterciarioKv()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getValPotencianominalMva() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setValPotencianominalMva(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getValPotencianominalMva()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getNomTipoderede() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setNomTipoderede(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getNomTipoderede()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getNumBarraPrimario() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setNumBarraPrimario(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getNumBarraPrimario()
                    );
                }
                if (onsCapacidadeTransformacaoRedeBasicaEntity.getNumBarraSecundario() != null) {
                    existingOnsCapacidadeTransformacaoRedeBasica.setNumBarraSecundario(
                        onsCapacidadeTransformacaoRedeBasicaEntity.getNumBarraSecundario()
                    );
                }

                return existingOnsCapacidadeTransformacaoRedeBasica;
            })
            .map(onsCapacidadeTransformacaoRedeBasicaRepository::save)
            .map(savedOnsCapacidadeTransformacaoRedeBasica -> {
                onsCapacidadeTransformacaoRedeBasicaSearchRepository.index(savedOnsCapacidadeTransformacaoRedeBasica);
                return savedOnsCapacidadeTransformacaoRedeBasica;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsCapacidadeTransformacaoRedeBasicaEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-capacidade-transformacao-rede-basicas} : get all the onsCapacidadeTransformacaoRedeBasicas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsCapacidadeTransformacaoRedeBasicas in body.
     */
    @GetMapping("")
    public List<OnsCapacidadeTransformacaoRedeBasicaEntity> getAllOnsCapacidadeTransformacaoRedeBasicas() {
        LOG.debug("REST request to get all OnsCapacidadeTransformacaoRedeBasicas");
        return onsCapacidadeTransformacaoRedeBasicaRepository.findAll();
    }

    /**
     * {@code GET  /ons-capacidade-transformacao-rede-basicas/:id} : get the "id" onsCapacidadeTransformacaoRedeBasica.
     *
     * @param id the id of the onsCapacidadeTransformacaoRedeBasicaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsCapacidadeTransformacaoRedeBasicaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsCapacidadeTransformacaoRedeBasicaEntity> getOnsCapacidadeTransformacaoRedeBasica(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsCapacidadeTransformacaoRedeBasica : {}", id);
        Optional<OnsCapacidadeTransformacaoRedeBasicaEntity> onsCapacidadeTransformacaoRedeBasicaEntity =
            onsCapacidadeTransformacaoRedeBasicaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsCapacidadeTransformacaoRedeBasicaEntity);
    }

    /**
     * {@code DELETE  /ons-capacidade-transformacao-rede-basicas/:id} : delete the "id" onsCapacidadeTransformacaoRedeBasica.
     *
     * @param id the id of the onsCapacidadeTransformacaoRedeBasicaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsCapacidadeTransformacaoRedeBasica(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsCapacidadeTransformacaoRedeBasica : {}", id);
        onsCapacidadeTransformacaoRedeBasicaRepository.deleteById(id);
        onsCapacidadeTransformacaoRedeBasicaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-capacidade-transformacao-rede-basicas/_search?query=:query} : search for the onsCapacidadeTransformacaoRedeBasica corresponding
     * to the query.
     *
     * @param query the query of the onsCapacidadeTransformacaoRedeBasica search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsCapacidadeTransformacaoRedeBasicaEntity> searchOnsCapacidadeTransformacaoRedeBasicas(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsCapacidadeTransformacaoRedeBasicas for query {}", query);
        try {
            return StreamSupport.stream(onsCapacidadeTransformacaoRedeBasicaSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
