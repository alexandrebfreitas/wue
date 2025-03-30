import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-linhas-transmissao-rede-operacao.test-samples';

import { OnsLinhasTransmissaoRedeOperacaoFormService } from './ons-linhas-transmissao-rede-operacao-form.service';

describe('OnsLinhasTransmissaoRedeOperacao Form Service', () => {
  let service: OnsLinhasTransmissaoRedeOperacaoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsLinhasTransmissaoRedeOperacaoFormService);
  });

  describe('Service methods', () => {
    describe('createOnsLinhasTransmissaoRedeOperacaoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsLinhasTransmissaoRedeOperacaoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistemaTerminalde: expect.any(Object),
            nomSubsistemaTerminalde: expect.any(Object),
            idSubsistemaTerminalpara: expect.any(Object),
            nomSubsistemaTerminalpara: expect.any(Object),
            idEstadoTerminalde: expect.any(Object),
            nomEstadoDe: expect.any(Object),
            idEstadoTerminalpara: expect.any(Object),
            nomEstadoPara: expect.any(Object),
            nomSubestacaoDe: expect.any(Object),
            nomSubestacaoPara: expect.any(Object),
            valNiveltensaoKv: expect.any(Object),
            nomTipoderede: expect.any(Object),
            nomTipolinha: expect.any(Object),
            nomAgenteproprietario: expect.any(Object),
            nomLinhadetransmissao: expect.any(Object),
            codEquipamento: expect.any(Object),
            datEntradaoperacao: expect.any(Object),
            datDesativacao: expect.any(Object),
            datPrevista: expect.any(Object),
            valComprimento: expect.any(Object),
            valResistencia: expect.any(Object),
            valReatancia: expect.any(Object),
            valShunt: expect.any(Object),
            valCapacoperlongasemlimit: expect.any(Object),
            valCapacoperlongacomlimit: expect.any(Object),
            valCapacopercurtasemlimit: expect.any(Object),
            valCapacopercurtacomlimit: expect.any(Object),
            valCapacidadeoperveraodialonga: expect.any(Object),
            valCapacoperinvernodialonga: expect.any(Object),
            valCapacoperinvernonoitelonga: expect.any(Object),
            valCapacoperveradiacurta: expect.any(Object),
            valCapacoperveraonoitecurta: expect.any(Object),
            valCapacoperinvernodiacurta: expect.any(Object),
            valCapacoperinvernonoitecurta: expect.any(Object),
          }),
        );
      });

      it('passing IOnsLinhasTransmissaoRedeOperacao should create a new form with FormGroup', () => {
        const formGroup = service.createOnsLinhasTransmissaoRedeOperacaoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistemaTerminalde: expect.any(Object),
            nomSubsistemaTerminalde: expect.any(Object),
            idSubsistemaTerminalpara: expect.any(Object),
            nomSubsistemaTerminalpara: expect.any(Object),
            idEstadoTerminalde: expect.any(Object),
            nomEstadoDe: expect.any(Object),
            idEstadoTerminalpara: expect.any(Object),
            nomEstadoPara: expect.any(Object),
            nomSubestacaoDe: expect.any(Object),
            nomSubestacaoPara: expect.any(Object),
            valNiveltensaoKv: expect.any(Object),
            nomTipoderede: expect.any(Object),
            nomTipolinha: expect.any(Object),
            nomAgenteproprietario: expect.any(Object),
            nomLinhadetransmissao: expect.any(Object),
            codEquipamento: expect.any(Object),
            datEntradaoperacao: expect.any(Object),
            datDesativacao: expect.any(Object),
            datPrevista: expect.any(Object),
            valComprimento: expect.any(Object),
            valResistencia: expect.any(Object),
            valReatancia: expect.any(Object),
            valShunt: expect.any(Object),
            valCapacoperlongasemlimit: expect.any(Object),
            valCapacoperlongacomlimit: expect.any(Object),
            valCapacopercurtasemlimit: expect.any(Object),
            valCapacopercurtacomlimit: expect.any(Object),
            valCapacidadeoperveraodialonga: expect.any(Object),
            valCapacoperinvernodialonga: expect.any(Object),
            valCapacoperinvernonoitelonga: expect.any(Object),
            valCapacoperveradiacurta: expect.any(Object),
            valCapacoperveraonoitecurta: expect.any(Object),
            valCapacoperinvernodiacurta: expect.any(Object),
            valCapacoperinvernonoitecurta: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsLinhasTransmissaoRedeOperacao', () => {
      it('should return NewOnsLinhasTransmissaoRedeOperacao for default OnsLinhasTransmissaoRedeOperacao initial value', () => {
        const formGroup = service.createOnsLinhasTransmissaoRedeOperacaoFormGroup(sampleWithNewData);

        const onsLinhasTransmissaoRedeOperacao = service.getOnsLinhasTransmissaoRedeOperacao(formGroup) as any;

        expect(onsLinhasTransmissaoRedeOperacao).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsLinhasTransmissaoRedeOperacao for empty OnsLinhasTransmissaoRedeOperacao initial value', () => {
        const formGroup = service.createOnsLinhasTransmissaoRedeOperacaoFormGroup();

        const onsLinhasTransmissaoRedeOperacao = service.getOnsLinhasTransmissaoRedeOperacao(formGroup) as any;

        expect(onsLinhasTransmissaoRedeOperacao).toMatchObject({});
      });

      it('should return IOnsLinhasTransmissaoRedeOperacao', () => {
        const formGroup = service.createOnsLinhasTransmissaoRedeOperacaoFormGroup(sampleWithRequiredData);

        const onsLinhasTransmissaoRedeOperacao = service.getOnsLinhasTransmissaoRedeOperacao(formGroup) as any;

        expect(onsLinhasTransmissaoRedeOperacao).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsLinhasTransmissaoRedeOperacao should not enable id FormControl', () => {
        const formGroup = service.createOnsLinhasTransmissaoRedeOperacaoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsLinhasTransmissaoRedeOperacao should disable id FormControl', () => {
        const formGroup = service.createOnsLinhasTransmissaoRedeOperacaoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
