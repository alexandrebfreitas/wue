package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsGeracaoComercialParaExportacaoInternacionalEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsGeracaoComercialParaExportacaoInternacionalEntity getOnsGeracaoComercialParaExportacaoInternacionalEntitySample1() {
        return new OnsGeracaoComercialParaExportacaoInternacionalEntity().id(1L).nomConversora("nomConversora1");
    }

    public static OnsGeracaoComercialParaExportacaoInternacionalEntity getOnsGeracaoComercialParaExportacaoInternacionalEntitySample2() {
        return new OnsGeracaoComercialParaExportacaoInternacionalEntity().id(2L).nomConversora("nomConversora2");
    }

    public static OnsGeracaoComercialParaExportacaoInternacionalEntity getOnsGeracaoComercialParaExportacaoInternacionalEntityRandomSampleGenerator() {
        return new OnsGeracaoComercialParaExportacaoInternacionalEntity()
            .id(longCount.incrementAndGet())
            .nomConversora(UUID.randomUUID().toString());
    }
}
