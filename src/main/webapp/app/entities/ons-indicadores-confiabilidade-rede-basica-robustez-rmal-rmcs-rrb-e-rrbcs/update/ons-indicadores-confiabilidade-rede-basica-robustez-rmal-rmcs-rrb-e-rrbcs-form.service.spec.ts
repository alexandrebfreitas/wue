import { TestBed } from '@angular/core/testing';

import {
  sampleWithNewData,
  sampleWithRequiredData,
} from '../ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.test-samples';

import { OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService } from './ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-form.service';

describe('OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs Form Service', () => {
  let service: OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormService);
  });

  describe('Service methods', () => {
    describe('createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codIndicador: expect.any(Object),
            dscAgregacao: expect.any(Object),
            codCaracteristica: expect.any(Object),
            dscCaracteristica: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            dinReferencia: expect.any(Object),
            valIndicador: expect.any(Object),
            numPerturbacoes: expect.any(Object),
            numPerturbacoescortecarga: expect.any(Object),
            numPerturbacoescortecarga0a50mw: expect.any(Object),
            numPerturbacoescortecarga50a100mw: expect.any(Object),
            numPerturbacoescortecargaMaior100mw: expect.any(Object),
          }),
        );
      });

      it('passing IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs should create a new form with FormGroup', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codIndicador: expect.any(Object),
            dscAgregacao: expect.any(Object),
            codCaracteristica: expect.any(Object),
            dscCaracteristica: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            dinReferencia: expect.any(Object),
            valIndicador: expect.any(Object),
            numPerturbacoes: expect.any(Object),
            numPerturbacoescortecarga: expect.any(Object),
            numPerturbacoescortecarga0a50mw: expect.any(Object),
            numPerturbacoescortecarga50a100mw: expect.any(Object),
            numPerturbacoescortecargaMaior100mw: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs', () => {
      it('should return NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs for default OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs initial value', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup(sampleWithNewData);

        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
          service.getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(formGroup) as any;

        expect(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs for empty OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs initial value', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup();

        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
          service.getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(formGroup) as any;

        expect(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs).toMatchObject({});
      });

      it('should return IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup(sampleWithRequiredData);

        const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
          service.getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(formGroup) as any;

        expect(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs should not enable id FormControl', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs should disable id FormControl', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
