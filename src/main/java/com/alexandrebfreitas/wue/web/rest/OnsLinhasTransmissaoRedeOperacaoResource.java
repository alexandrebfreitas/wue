package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsLinhasTransmissaoRedeOperacaoEntity;
import com.alexandrebfreitas.wue.repository.OnsLinhasTransmissaoRedeOperacaoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsLinhasTransmissaoRedeOperacaoSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsLinhasTransmissaoRedeOperacaoEntity}.
 */
@RestController
@RequestMapping("/api/ons-linhas-transmissao-rede-operacaos")
@Transactional
public class OnsLinhasTransmissaoRedeOperacaoResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsLinhasTransmissaoRedeOperacaoResource.class);

    private static final String ENTITY_NAME = "onsLinhasTransmissaoRedeOperacao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsLinhasTransmissaoRedeOperacaoRepository onsLinhasTransmissaoRedeOperacaoRepository;

    private final OnsLinhasTransmissaoRedeOperacaoSearchRepository onsLinhasTransmissaoRedeOperacaoSearchRepository;

    public OnsLinhasTransmissaoRedeOperacaoResource(
        OnsLinhasTransmissaoRedeOperacaoRepository onsLinhasTransmissaoRedeOperacaoRepository,
        OnsLinhasTransmissaoRedeOperacaoSearchRepository onsLinhasTransmissaoRedeOperacaoSearchRepository
    ) {
        this.onsLinhasTransmissaoRedeOperacaoRepository = onsLinhasTransmissaoRedeOperacaoRepository;
        this.onsLinhasTransmissaoRedeOperacaoSearchRepository = onsLinhasTransmissaoRedeOperacaoSearchRepository;
    }

    /**
     * {@code POST  /ons-linhas-transmissao-rede-operacaos} : Create a new onsLinhasTransmissaoRedeOperacao.
     *
     * @param onsLinhasTransmissaoRedeOperacaoEntity the onsLinhasTransmissaoRedeOperacaoEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsLinhasTransmissaoRedeOperacaoEntity, or with status {@code 400 (Bad Request)} if the onsLinhasTransmissaoRedeOperacao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsLinhasTransmissaoRedeOperacaoEntity> createOnsLinhasTransmissaoRedeOperacao(
        @RequestBody OnsLinhasTransmissaoRedeOperacaoEntity onsLinhasTransmissaoRedeOperacaoEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsLinhasTransmissaoRedeOperacao : {}", onsLinhasTransmissaoRedeOperacaoEntity);
        if (onsLinhasTransmissaoRedeOperacaoEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsLinhasTransmissaoRedeOperacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsLinhasTransmissaoRedeOperacaoEntity = onsLinhasTransmissaoRedeOperacaoRepository.save(onsLinhasTransmissaoRedeOperacaoEntity);
        onsLinhasTransmissaoRedeOperacaoSearchRepository.index(onsLinhasTransmissaoRedeOperacaoEntity);
        return ResponseEntity.created(
            new URI("/api/ons-linhas-transmissao-rede-operacaos/" + onsLinhasTransmissaoRedeOperacaoEntity.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsLinhasTransmissaoRedeOperacaoEntity.getId().toString()
                )
            )
            .body(onsLinhasTransmissaoRedeOperacaoEntity);
    }

    /**
     * {@code PUT  /ons-linhas-transmissao-rede-operacaos/:id} : Updates an existing onsLinhasTransmissaoRedeOperacao.
     *
     * @param id the id of the onsLinhasTransmissaoRedeOperacaoEntity to save.
     * @param onsLinhasTransmissaoRedeOperacaoEntity the onsLinhasTransmissaoRedeOperacaoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsLinhasTransmissaoRedeOperacaoEntity,
     * or with status {@code 400 (Bad Request)} if the onsLinhasTransmissaoRedeOperacaoEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsLinhasTransmissaoRedeOperacaoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsLinhasTransmissaoRedeOperacaoEntity> updateOnsLinhasTransmissaoRedeOperacao(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsLinhasTransmissaoRedeOperacaoEntity onsLinhasTransmissaoRedeOperacaoEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsLinhasTransmissaoRedeOperacao : {}, {}", id, onsLinhasTransmissaoRedeOperacaoEntity);
        if (onsLinhasTransmissaoRedeOperacaoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsLinhasTransmissaoRedeOperacaoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsLinhasTransmissaoRedeOperacaoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsLinhasTransmissaoRedeOperacaoEntity = onsLinhasTransmissaoRedeOperacaoRepository.save(onsLinhasTransmissaoRedeOperacaoEntity);
        onsLinhasTransmissaoRedeOperacaoSearchRepository.index(onsLinhasTransmissaoRedeOperacaoEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsLinhasTransmissaoRedeOperacaoEntity.getId().toString()
                )
            )
            .body(onsLinhasTransmissaoRedeOperacaoEntity);
    }

    /**
     * {@code PATCH  /ons-linhas-transmissao-rede-operacaos/:id} : Partial updates given fields of an existing onsLinhasTransmissaoRedeOperacao, field will ignore if it is null
     *
     * @param id the id of the onsLinhasTransmissaoRedeOperacaoEntity to save.
     * @param onsLinhasTransmissaoRedeOperacaoEntity the onsLinhasTransmissaoRedeOperacaoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsLinhasTransmissaoRedeOperacaoEntity,
     * or with status {@code 400 (Bad Request)} if the onsLinhasTransmissaoRedeOperacaoEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsLinhasTransmissaoRedeOperacaoEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsLinhasTransmissaoRedeOperacaoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsLinhasTransmissaoRedeOperacaoEntity> partialUpdateOnsLinhasTransmissaoRedeOperacao(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsLinhasTransmissaoRedeOperacaoEntity onsLinhasTransmissaoRedeOperacaoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsLinhasTransmissaoRedeOperacao partially : {}, {}",
            id,
            onsLinhasTransmissaoRedeOperacaoEntity
        );
        if (onsLinhasTransmissaoRedeOperacaoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsLinhasTransmissaoRedeOperacaoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsLinhasTransmissaoRedeOperacaoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsLinhasTransmissaoRedeOperacaoEntity> result = onsLinhasTransmissaoRedeOperacaoRepository
            .findById(onsLinhasTransmissaoRedeOperacaoEntity.getId())
            .map(existingOnsLinhasTransmissaoRedeOperacao -> {
                if (onsLinhasTransmissaoRedeOperacaoEntity.getIdSubsistemaTerminalde() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setIdSubsistemaTerminalde(
                        onsLinhasTransmissaoRedeOperacaoEntity.getIdSubsistemaTerminalde()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getNomSubsistemaTerminalde() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setNomSubsistemaTerminalde(
                        onsLinhasTransmissaoRedeOperacaoEntity.getNomSubsistemaTerminalde()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getIdSubsistemaTerminalpara() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setIdSubsistemaTerminalpara(
                        onsLinhasTransmissaoRedeOperacaoEntity.getIdSubsistemaTerminalpara()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getNomSubsistemaTerminalpara() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setNomSubsistemaTerminalpara(
                        onsLinhasTransmissaoRedeOperacaoEntity.getNomSubsistemaTerminalpara()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getIdEstadoTerminalde() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setIdEstadoTerminalde(
                        onsLinhasTransmissaoRedeOperacaoEntity.getIdEstadoTerminalde()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getNomEstadoDe() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setNomEstadoDe(onsLinhasTransmissaoRedeOperacaoEntity.getNomEstadoDe());
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getIdEstadoTerminalpara() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setIdEstadoTerminalpara(
                        onsLinhasTransmissaoRedeOperacaoEntity.getIdEstadoTerminalpara()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getNomEstadoPara() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setNomEstadoPara(onsLinhasTransmissaoRedeOperacaoEntity.getNomEstadoPara());
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getNomSubestacaoDe() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setNomSubestacaoDe(
                        onsLinhasTransmissaoRedeOperacaoEntity.getNomSubestacaoDe()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getNomSubestacaoPara() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setNomSubestacaoPara(
                        onsLinhasTransmissaoRedeOperacaoEntity.getNomSubestacaoPara()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValNiveltensaoKv() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValNiveltensaoKv(
                        onsLinhasTransmissaoRedeOperacaoEntity.getValNiveltensaoKv()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getNomTipoderede() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setNomTipoderede(onsLinhasTransmissaoRedeOperacaoEntity.getNomTipoderede());
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getNomTipolinha() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setNomTipolinha(onsLinhasTransmissaoRedeOperacaoEntity.getNomTipolinha());
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getNomAgenteproprietario() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setNomAgenteproprietario(
                        onsLinhasTransmissaoRedeOperacaoEntity.getNomAgenteproprietario()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getNomLinhadetransmissao() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setNomLinhadetransmissao(
                        onsLinhasTransmissaoRedeOperacaoEntity.getNomLinhadetransmissao()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getCodEquipamento() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setCodEquipamento(onsLinhasTransmissaoRedeOperacaoEntity.getCodEquipamento());
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getDatEntradaoperacao() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setDatEntradaoperacao(
                        onsLinhasTransmissaoRedeOperacaoEntity.getDatEntradaoperacao()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getDatDesativacao() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setDatDesativacao(onsLinhasTransmissaoRedeOperacaoEntity.getDatDesativacao());
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getDatPrevista() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setDatPrevista(onsLinhasTransmissaoRedeOperacaoEntity.getDatPrevista());
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValComprimento() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValComprimento(onsLinhasTransmissaoRedeOperacaoEntity.getValComprimento());
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValResistencia() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValResistencia(onsLinhasTransmissaoRedeOperacaoEntity.getValResistencia());
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValReatancia() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValReatancia(onsLinhasTransmissaoRedeOperacaoEntity.getValReatancia());
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValShunt() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValShunt(onsLinhasTransmissaoRedeOperacaoEntity.getValShunt());
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperlongasemlimit() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValCapacoperlongasemlimit(
                        onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperlongasemlimit()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperlongacomlimit() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValCapacoperlongacomlimit(
                        onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperlongacomlimit()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValCapacopercurtasemlimit() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValCapacopercurtasemlimit(
                        onsLinhasTransmissaoRedeOperacaoEntity.getValCapacopercurtasemlimit()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValCapacopercurtacomlimit() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValCapacopercurtacomlimit(
                        onsLinhasTransmissaoRedeOperacaoEntity.getValCapacopercurtacomlimit()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValCapacidadeoperveraodialonga() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValCapacidadeoperveraodialonga(
                        onsLinhasTransmissaoRedeOperacaoEntity.getValCapacidadeoperveraodialonga()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperinvernodialonga() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValCapacoperinvernodialonga(
                        onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperinvernodialonga()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperinvernonoitelonga() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValCapacoperinvernonoitelonga(
                        onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperinvernonoitelonga()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperveradiacurta() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValCapacoperveradiacurta(
                        onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperveradiacurta()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperveraonoitecurta() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValCapacoperveraonoitecurta(
                        onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperveraonoitecurta()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperinvernodiacurta() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValCapacoperinvernodiacurta(
                        onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperinvernodiacurta()
                    );
                }
                if (onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperinvernonoitecurta() != null) {
                    existingOnsLinhasTransmissaoRedeOperacao.setValCapacoperinvernonoitecurta(
                        onsLinhasTransmissaoRedeOperacaoEntity.getValCapacoperinvernonoitecurta()
                    );
                }

                return existingOnsLinhasTransmissaoRedeOperacao;
            })
            .map(onsLinhasTransmissaoRedeOperacaoRepository::save)
            .map(savedOnsLinhasTransmissaoRedeOperacao -> {
                onsLinhasTransmissaoRedeOperacaoSearchRepository.index(savedOnsLinhasTransmissaoRedeOperacao);
                return savedOnsLinhasTransmissaoRedeOperacao;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsLinhasTransmissaoRedeOperacaoEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-linhas-transmissao-rede-operacaos} : get all the onsLinhasTransmissaoRedeOperacaos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsLinhasTransmissaoRedeOperacaos in body.
     */
    @GetMapping("")
    public List<OnsLinhasTransmissaoRedeOperacaoEntity> getAllOnsLinhasTransmissaoRedeOperacaos() {
        LOG.debug("REST request to get all OnsLinhasTransmissaoRedeOperacaos");
        return onsLinhasTransmissaoRedeOperacaoRepository.findAll();
    }

    /**
     * {@code GET  /ons-linhas-transmissao-rede-operacaos/:id} : get the "id" onsLinhasTransmissaoRedeOperacao.
     *
     * @param id the id of the onsLinhasTransmissaoRedeOperacaoEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsLinhasTransmissaoRedeOperacaoEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsLinhasTransmissaoRedeOperacaoEntity> getOnsLinhasTransmissaoRedeOperacao(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsLinhasTransmissaoRedeOperacao : {}", id);
        Optional<OnsLinhasTransmissaoRedeOperacaoEntity> onsLinhasTransmissaoRedeOperacaoEntity =
            onsLinhasTransmissaoRedeOperacaoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsLinhasTransmissaoRedeOperacaoEntity);
    }

    /**
     * {@code DELETE  /ons-linhas-transmissao-rede-operacaos/:id} : delete the "id" onsLinhasTransmissaoRedeOperacao.
     *
     * @param id the id of the onsLinhasTransmissaoRedeOperacaoEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsLinhasTransmissaoRedeOperacao(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsLinhasTransmissaoRedeOperacao : {}", id);
        onsLinhasTransmissaoRedeOperacaoRepository.deleteById(id);
        onsLinhasTransmissaoRedeOperacaoSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-linhas-transmissao-rede-operacaos/_search?query=:query} : search for the onsLinhasTransmissaoRedeOperacao corresponding
     * to the query.
     *
     * @param query the query of the onsLinhasTransmissaoRedeOperacao search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsLinhasTransmissaoRedeOperacaoEntity> searchOnsLinhasTransmissaoRedeOperacaos(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsLinhasTransmissaoRedeOperacaos for query {}", query);
        try {
            return StreamSupport.stream(onsLinhasTransmissaoRedeOperacaoSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
