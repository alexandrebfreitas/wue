package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntitySample1() {
        return new OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity()
            .id(1L)
            .idPeriodicidade("idPeriodicidade1")
            .numDesvioPermSobre(1)
            .numDesvioPermSub(1)
            .numDesvioDistSobre(1)
            .numDesvioDistSub(1)
            .numMinutos(1)
            .numVioladodis(1)
            .numVioladoperm(1);
    }

    public static OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntitySample2() {
        return new OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity()
            .id(2L)
            .idPeriodicidade("idPeriodicidade2")
            .numDesvioPermSobre(2)
            .numDesvioPermSub(2)
            .numDesvioDistSobre(2)
            .numDesvioDistSub(2)
            .numMinutos(2)
            .numVioladodis(2)
            .numVioladoperm(2);
    }

    public static OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntityRandomSampleGenerator() {
        return new OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity()
            .id(longCount.incrementAndGet())
            .idPeriodicidade(UUID.randomUUID().toString())
            .numDesvioPermSobre(intCount.incrementAndGet())
            .numDesvioPermSub(intCount.incrementAndGet())
            .numDesvioDistSobre(intCount.incrementAndGet())
            .numDesvioDistSub(intCount.incrementAndGet())
            .numMinutos(intCount.incrementAndGet())
            .numVioladodis(intCount.incrementAndGet())
            .numVioladoperm(intCount.incrementAndGet());
    }
}
