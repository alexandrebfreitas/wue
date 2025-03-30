package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntitySample1() {
        return new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity()
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
            .nomUnidadegeradora("nomUnidadegeradora1")
            .dinAno(1);
    }

    public static OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntitySample2() {
        return new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity()
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
            .nomUnidadegeradora("nomUnidadegeradora2")
            .dinAno(2);
    }

    public static OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityRandomSampleGenerator() {
        return new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity()
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
            .nomUnidadegeradora(UUID.randomUUID().toString())
            .dinAno(intCount.incrementAndGet());
    }
}
