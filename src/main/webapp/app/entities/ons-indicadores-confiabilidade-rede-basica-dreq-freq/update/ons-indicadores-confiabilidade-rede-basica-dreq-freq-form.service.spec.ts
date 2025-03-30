import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-indicadores-confiabilidade-rede-basica-dreq-freq.test-samples';

import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService } from './ons-indicadores-confiabilidade-rede-basica-dreq-freq-form.service';

describe('OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq Form Service', () => {
  let service: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormService);
  });

  describe('Service methods', () => {
    describe('createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dscAgregacao: expect.any(Object),
            codCaracteristica: expect.any(Object),
            dscCaracteristica: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            dinReferencia: expect.any(Object),
            valDreq: expect.any(Object),
            valFreq: expect.any(Object),
          }),
        );
      });

      it('passing IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq should create a new form with FormGroup', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dscAgregacao: expect.any(Object),
            codCaracteristica: expect.any(Object),
            dscCaracteristica: expect.any(Object),
            idPeriodicidade: expect.any(Object),
            dinReferencia: expect.any(Object),
            valDreq: expect.any(Object),
            valFreq: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq', () => {
      it('should return NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq for default OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq initial value', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup(sampleWithNewData);

        const onsIndicadoresConfiabilidadeRedeBasicaDreqFreq = service.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq(formGroup) as any;

        expect(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq for empty OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq initial value', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup();

        const onsIndicadoresConfiabilidadeRedeBasicaDreqFreq = service.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq(formGroup) as any;

        expect(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq).toMatchObject({});
      });

      it('should return IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup(sampleWithRequiredData);

        const onsIndicadoresConfiabilidadeRedeBasicaDreqFreq = service.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq(formGroup) as any;

        expect(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq should not enable id FormControl', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq should disable id FormControl', () => {
        const formGroup = service.createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
