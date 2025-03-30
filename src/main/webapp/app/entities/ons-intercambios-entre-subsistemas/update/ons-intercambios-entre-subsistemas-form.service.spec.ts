import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-intercambios-entre-subsistemas.test-samples';

import { OnsIntercambiosEntreSubsistemasFormService } from './ons-intercambios-entre-subsistemas-form.service';

describe('OnsIntercambiosEntreSubsistemas Form Service', () => {
  let service: OnsIntercambiosEntreSubsistemasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsIntercambiosEntreSubsistemasFormService);
  });

  describe('Service methods', () => {
    describe('createOnsIntercambiosEntreSubsistemasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsIntercambiosEntreSubsistemasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinInstante: expect.any(Object),
            idSubsistemaOrigem: expect.any(Object),
            nomSubsistemaOrigem: expect.any(Object),
            idSubsistemaDestino: expect.any(Object),
            nomSubsistemaDestino: expect.any(Object),
            valIntercambiomwmed: expect.any(Object),
          }),
        );
      });

      it('passing IOnsIntercambiosEntreSubsistemas should create a new form with FormGroup', () => {
        const formGroup = service.createOnsIntercambiosEntreSubsistemasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinInstante: expect.any(Object),
            idSubsistemaOrigem: expect.any(Object),
            nomSubsistemaOrigem: expect.any(Object),
            idSubsistemaDestino: expect.any(Object),
            nomSubsistemaDestino: expect.any(Object),
            valIntercambiomwmed: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsIntercambiosEntreSubsistemas', () => {
      it('should return NewOnsIntercambiosEntreSubsistemas for default OnsIntercambiosEntreSubsistemas initial value', () => {
        const formGroup = service.createOnsIntercambiosEntreSubsistemasFormGroup(sampleWithNewData);

        const onsIntercambiosEntreSubsistemas = service.getOnsIntercambiosEntreSubsistemas(formGroup) as any;

        expect(onsIntercambiosEntreSubsistemas).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsIntercambiosEntreSubsistemas for empty OnsIntercambiosEntreSubsistemas initial value', () => {
        const formGroup = service.createOnsIntercambiosEntreSubsistemasFormGroup();

        const onsIntercambiosEntreSubsistemas = service.getOnsIntercambiosEntreSubsistemas(formGroup) as any;

        expect(onsIntercambiosEntreSubsistemas).toMatchObject({});
      });

      it('should return IOnsIntercambiosEntreSubsistemas', () => {
        const formGroup = service.createOnsIntercambiosEntreSubsistemasFormGroup(sampleWithRequiredData);

        const onsIntercambiosEntreSubsistemas = service.getOnsIntercambiosEntreSubsistemas(formGroup) as any;

        expect(onsIntercambiosEntreSubsistemas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsIntercambiosEntreSubsistemas should not enable id FormControl', () => {
        const formGroup = service.createOnsIntercambiosEntreSubsistemasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsIntercambiosEntreSubsistemas should disable id FormControl', () => {
        const formGroup = service.createOnsIntercambiosEntreSubsistemasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
