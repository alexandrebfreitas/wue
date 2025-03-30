package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsFatorCapacidadeGeracaoEolicaESolarEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsFatorCapacidadeGeracaoEolicaESolarEntity getOnsFatorCapacidadeGeracaoEolicaESolarEntitySample1() {
        return new OnsFatorCapacidadeGeracaoEolicaESolarEntity()
            .id(1L)
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .idEstado("idEstado1")
            .nomEstado("nomEstado1")
            .codPontoconexao("codPontoconexao1")
            .nomPontoconexao("nomPontoconexao1")
            .nomLocalizacao("nomLocalizacao1")
            .nomModalidadeoperacao("nomModalidadeoperacao1")
            .nomTipousina("nomTipousina1")
            .nomUsinaConjunto("nomUsinaConjunto1")
            .idOns("idOns1")
            .ceg("ceg1");
    }

    public static OnsFatorCapacidadeGeracaoEolicaESolarEntity getOnsFatorCapacidadeGeracaoEolicaESolarEntitySample2() {
        return new OnsFatorCapacidadeGeracaoEolicaESolarEntity()
            .id(2L)
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .idEstado("idEstado2")
            .nomEstado("nomEstado2")
            .codPontoconexao("codPontoconexao2")
            .nomPontoconexao("nomPontoconexao2")
            .nomLocalizacao("nomLocalizacao2")
            .nomModalidadeoperacao("nomModalidadeoperacao2")
            .nomTipousina("nomTipousina2")
            .nomUsinaConjunto("nomUsinaConjunto2")
            .idOns("idOns2")
            .ceg("ceg2");
    }

    public static OnsFatorCapacidadeGeracaoEolicaESolarEntity getOnsFatorCapacidadeGeracaoEolicaESolarEntityRandomSampleGenerator() {
        return new OnsFatorCapacidadeGeracaoEolicaESolarEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .idEstado(UUID.randomUUID().toString())
            .nomEstado(UUID.randomUUID().toString())
            .codPontoconexao(UUID.randomUUID().toString())
            .nomPontoconexao(UUID.randomUUID().toString())
            .nomLocalizacao(UUID.randomUUID().toString())
            .nomModalidadeoperacao(UUID.randomUUID().toString())
            .nomTipousina(UUID.randomUUID().toString())
            .nomUsinaConjunto(UUID.randomUUID().toString())
            .idOns(UUID.randomUUID().toString())
            .ceg(UUID.randomUUID().toString());
    }
}
