package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsLinhasTransmissaoRedeOperacaoEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsLinhasTransmissaoRedeOperacaoEntity getOnsLinhasTransmissaoRedeOperacaoEntitySample1() {
        return new OnsLinhasTransmissaoRedeOperacaoEntity()
            .id(1L)
            .idSubsistemaTerminalde("idSubsistemaTerminalde1")
            .nomSubsistemaTerminalde("nomSubsistemaTerminalde1")
            .idSubsistemaTerminalpara("idSubsistemaTerminalpara1")
            .nomSubsistemaTerminalpara("nomSubsistemaTerminalpara1")
            .idEstadoTerminalde("idEstadoTerminalde1")
            .nomEstadoDe("nomEstadoDe1")
            .idEstadoTerminalpara("idEstadoTerminalpara1")
            .nomEstadoPara("nomEstadoPara1")
            .nomSubestacaoDe("nomSubestacaoDe1")
            .nomSubestacaoPara("nomSubestacaoPara1")
            .nomTipoderede("nomTipoderede1")
            .nomTipolinha("nomTipolinha1")
            .nomAgenteproprietario("nomAgenteproprietario1")
            .nomLinhadetransmissao("nomLinhadetransmissao1")
            .codEquipamento("codEquipamento1");
    }

    public static OnsLinhasTransmissaoRedeOperacaoEntity getOnsLinhasTransmissaoRedeOperacaoEntitySample2() {
        return new OnsLinhasTransmissaoRedeOperacaoEntity()
            .id(2L)
            .idSubsistemaTerminalde("idSubsistemaTerminalde2")
            .nomSubsistemaTerminalde("nomSubsistemaTerminalde2")
            .idSubsistemaTerminalpara("idSubsistemaTerminalpara2")
            .nomSubsistemaTerminalpara("nomSubsistemaTerminalpara2")
            .idEstadoTerminalde("idEstadoTerminalde2")
            .nomEstadoDe("nomEstadoDe2")
            .idEstadoTerminalpara("idEstadoTerminalpara2")
            .nomEstadoPara("nomEstadoPara2")
            .nomSubestacaoDe("nomSubestacaoDe2")
            .nomSubestacaoPara("nomSubestacaoPara2")
            .nomTipoderede("nomTipoderede2")
            .nomTipolinha("nomTipolinha2")
            .nomAgenteproprietario("nomAgenteproprietario2")
            .nomLinhadetransmissao("nomLinhadetransmissao2")
            .codEquipamento("codEquipamento2");
    }

    public static OnsLinhasTransmissaoRedeOperacaoEntity getOnsLinhasTransmissaoRedeOperacaoEntityRandomSampleGenerator() {
        return new OnsLinhasTransmissaoRedeOperacaoEntity()
            .id(longCount.incrementAndGet())
            .idSubsistemaTerminalde(UUID.randomUUID().toString())
            .nomSubsistemaTerminalde(UUID.randomUUID().toString())
            .idSubsistemaTerminalpara(UUID.randomUUID().toString())
            .nomSubsistemaTerminalpara(UUID.randomUUID().toString())
            .idEstadoTerminalde(UUID.randomUUID().toString())
            .nomEstadoDe(UUID.randomUUID().toString())
            .idEstadoTerminalpara(UUID.randomUUID().toString())
            .nomEstadoPara(UUID.randomUUID().toString())
            .nomSubestacaoDe(UUID.randomUUID().toString())
            .nomSubestacaoPara(UUID.randomUUID().toString())
            .nomTipoderede(UUID.randomUUID().toString())
            .nomTipolinha(UUID.randomUUID().toString())
            .nomAgenteproprietario(UUID.randomUUID().toString())
            .nomLinhadetransmissao(UUID.randomUUID().toString())
            .codEquipamento(UUID.randomUUID().toString());
    }
}
