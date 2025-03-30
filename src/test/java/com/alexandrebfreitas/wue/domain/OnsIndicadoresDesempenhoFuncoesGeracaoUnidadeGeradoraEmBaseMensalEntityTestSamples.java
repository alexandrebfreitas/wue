package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntitySample1() {
        return new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity()
            .id(1L)
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .idEstado("idEstado1")
            .nomEstado("nomEstado1")
            .nomModalidadeoperacao("nomModalidadeoperacao1")
            .nomAgenteproprietario("nomAgenteproprietario1")
            .idTipousina("idTipousina1")
            .idUsina("idUsina1")
            .nomUsina("nomUsina1")
            .ceg("ceg1")
            .codEquipamento("codEquipamento1")
            .numUnidadegeradora("numUnidadegeradora1")
            .nomUnidadegeradora("nomUnidadegeradora1");
    }

    public static OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntitySample2() {
        return new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity()
            .id(2L)
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .idEstado("idEstado2")
            .nomEstado("nomEstado2")
            .nomModalidadeoperacao("nomModalidadeoperacao2")
            .nomAgenteproprietario("nomAgenteproprietario2")
            .idTipousina("idTipousina2")
            .idUsina("idUsina2")
            .nomUsina("nomUsina2")
            .ceg("ceg2")
            .codEquipamento("codEquipamento2")
            .numUnidadegeradora("numUnidadegeradora2")
            .nomUnidadegeradora("nomUnidadegeradora2");
    }

    public static OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityRandomSampleGenerator() {
        return new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .idEstado(UUID.randomUUID().toString())
            .nomEstado(UUID.randomUUID().toString())
            .nomModalidadeoperacao(UUID.randomUUID().toString())
            .nomAgenteproprietario(UUID.randomUUID().toString())
            .idTipousina(UUID.randomUUID().toString())
            .idUsina(UUID.randomUUID().toString())
            .nomUsina(UUID.randomUUID().toString())
            .ceg(UUID.randomUUID().toString())
            .codEquipamento(UUID.randomUUID().toString())
            .numUnidadegeradora(UUID.randomUUID().toString())
            .nomUnidadegeradora(UUID.randomUUID().toString());
    }
}
