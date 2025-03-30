import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-capacidade-transformacao-rede-basica.test-samples';

import { OnsCapacidadeTransformacaoRedeBasicaFormService } from './ons-capacidade-transformacao-rede-basica-form.service';

describe('OnsCapacidadeTransformacaoRedeBasica Form Service', () => {
  let service: OnsCapacidadeTransformacaoRedeBasicaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsCapacidadeTransformacaoRedeBasicaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsCapacidadeTransformacaoRedeBasicaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsCapacidadeTransformacaoRedeBasicaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            nomTipotransformador: expect.any(Object),
            nomAgenteproprietario: expect.any(Object),
            nomSubestacao: expect.any(Object),
            nomTransformador: expect.any(Object),
            codEquipamento: expect.any(Object),
            datEntradaoperacao: expect.any(Object),
            datDesativacao: expect.any(Object),
            valTensaoprimarioKv: expect.any(Object),
            valTensaosecundarioKv: expect.any(Object),
            valTensaoterciarioKv: expect.any(Object),
            valPotencianominalMva: expect.any(Object),
            nomTipoderede: expect.any(Object),
            numBarraPrimario: expect.any(Object),
            numBarraSecundario: expect.any(Object),
          }),
        );
      });

      it('passing IOnsCapacidadeTransformacaoRedeBasica should create a new form with FormGroup', () => {
        const formGroup = service.createOnsCapacidadeTransformacaoRedeBasicaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            nomTipotransformador: expect.any(Object),
            nomAgenteproprietario: expect.any(Object),
            nomSubestacao: expect.any(Object),
            nomTransformador: expect.any(Object),
            codEquipamento: expect.any(Object),
            datEntradaoperacao: expect.any(Object),
            datDesativacao: expect.any(Object),
            valTensaoprimarioKv: expect.any(Object),
            valTensaosecundarioKv: expect.any(Object),
            valTensaoterciarioKv: expect.any(Object),
            valPotencianominalMva: expect.any(Object),
            nomTipoderede: expect.any(Object),
            numBarraPrimario: expect.any(Object),
            numBarraSecundario: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsCapacidadeTransformacaoRedeBasica', () => {
      it('should return NewOnsCapacidadeTransformacaoRedeBasica for default OnsCapacidadeTransformacaoRedeBasica initial value', () => {
        const formGroup = service.createOnsCapacidadeTransformacaoRedeBasicaFormGroup(sampleWithNewData);

        const onsCapacidadeTransformacaoRedeBasica = service.getOnsCapacidadeTransformacaoRedeBasica(formGroup) as any;

        expect(onsCapacidadeTransformacaoRedeBasica).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsCapacidadeTransformacaoRedeBasica for empty OnsCapacidadeTransformacaoRedeBasica initial value', () => {
        const formGroup = service.createOnsCapacidadeTransformacaoRedeBasicaFormGroup();

        const onsCapacidadeTransformacaoRedeBasica = service.getOnsCapacidadeTransformacaoRedeBasica(formGroup) as any;

        expect(onsCapacidadeTransformacaoRedeBasica).toMatchObject({});
      });

      it('should return IOnsCapacidadeTransformacaoRedeBasica', () => {
        const formGroup = service.createOnsCapacidadeTransformacaoRedeBasicaFormGroup(sampleWithRequiredData);

        const onsCapacidadeTransformacaoRedeBasica = service.getOnsCapacidadeTransformacaoRedeBasica(formGroup) as any;

        expect(onsCapacidadeTransformacaoRedeBasica).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsCapacidadeTransformacaoRedeBasica should not enable id FormControl', () => {
        const formGroup = service.createOnsCapacidadeTransformacaoRedeBasicaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsCapacidadeTransformacaoRedeBasica should disable id FormControl', () => {
        const formGroup = service.createOnsCapacidadeTransformacaoRedeBasicaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
