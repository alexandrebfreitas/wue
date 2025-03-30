package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsModalidadeOperacaoUsinasEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsModalidadeOperacaoUsinasEntity getOnsModalidadeOperacaoUsinasEntitySample1() {
        return new OnsModalidadeOperacaoUsinasEntity()
            .id(1L)
            .nomUsina("nomUsina1")
            .ceg("ceg1")
            .nomModalidadeoperacao("nomModalidadeoperacao1")
            .sglCentrooperacao("sglCentrooperacao1")
            .nomPontoconexao("nomPontoconexao1")
            .idEstado("idEstado1")
            .nomEstado("nomEstado1")
            .stsAneel("stsAneel1");
    }

    public static OnsModalidadeOperacaoUsinasEntity getOnsModalidadeOperacaoUsinasEntitySample2() {
        return new OnsModalidadeOperacaoUsinasEntity()
            .id(2L)
            .nomUsina("nomUsina2")
            .ceg("ceg2")
            .nomModalidadeoperacao("nomModalidadeoperacao2")
            .sglCentrooperacao("sglCentrooperacao2")
            .nomPontoconexao("nomPontoconexao2")
            .idEstado("idEstado2")
            .nomEstado("nomEstado2")
            .stsAneel("stsAneel2");
    }

    public static OnsModalidadeOperacaoUsinasEntity getOnsModalidadeOperacaoUsinasEntityRandomSampleGenerator() {
        return new OnsModalidadeOperacaoUsinasEntity()
            .id(longCount.incrementAndGet())
            .nomUsina(UUID.randomUUID().toString())
            .ceg(UUID.randomUUID().toString())
            .nomModalidadeoperacao(UUID.randomUUID().toString())
            .sglCentrooperacao(UUID.randomUUID().toString())
            .nomPontoconexao(UUID.randomUUID().toString())
            .idEstado(UUID.randomUUID().toString())
            .nomEstado(UUID.randomUUID().toString())
            .stsAneel(UUID.randomUUID().toString());
    }
}
