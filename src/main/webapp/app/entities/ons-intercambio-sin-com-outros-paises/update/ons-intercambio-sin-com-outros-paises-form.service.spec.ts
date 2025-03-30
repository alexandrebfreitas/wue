import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-intercambio-sin-com-outros-paises.test-samples';

import { OnsIntercambioSinComOutrosPaisesFormService } from './ons-intercambio-sin-com-outros-paises-form.service';

describe('OnsIntercambioSinComOutrosPaises Form Service', () => {
  let service: OnsIntercambioSinComOutrosPaisesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsIntercambioSinComOutrosPaisesFormService);
  });

  describe('Service methods', () => {
    describe('createOnsIntercambioSinComOutrosPaisesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsIntercambioSinComOutrosPaisesFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinInstante: expect.any(Object),
            nomPaisdestino: expect.any(Object),
            valIntercambiomwmed: expect.any(Object),
          }),
        );
      });

      it('passing IOnsIntercambioSinComOutrosPaises should create a new form with FormGroup', () => {
        const formGroup = service.createOnsIntercambioSinComOutrosPaisesFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dinInstante: expect.any(Object),
            nomPaisdestino: expect.any(Object),
            valIntercambiomwmed: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsIntercambioSinComOutrosPaises', () => {
      it('should return NewOnsIntercambioSinComOutrosPaises for default OnsIntercambioSinComOutrosPaises initial value', () => {
        const formGroup = service.createOnsIntercambioSinComOutrosPaisesFormGroup(sampleWithNewData);

        const onsIntercambioSinComOutrosPaises = service.getOnsIntercambioSinComOutrosPaises(formGroup) as any;

        expect(onsIntercambioSinComOutrosPaises).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsIntercambioSinComOutrosPaises for empty OnsIntercambioSinComOutrosPaises initial value', () => {
        const formGroup = service.createOnsIntercambioSinComOutrosPaisesFormGroup();

        const onsIntercambioSinComOutrosPaises = service.getOnsIntercambioSinComOutrosPaises(formGroup) as any;

        expect(onsIntercambioSinComOutrosPaises).toMatchObject({});
      });

      it('should return IOnsIntercambioSinComOutrosPaises', () => {
        const formGroup = service.createOnsIntercambioSinComOutrosPaisesFormGroup(sampleWithRequiredData);

        const onsIntercambioSinComOutrosPaises = service.getOnsIntercambioSinComOutrosPaises(formGroup) as any;

        expect(onsIntercambioSinComOutrosPaises).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsIntercambioSinComOutrosPaises should not enable id FormControl', () => {
        const formGroup = service.createOnsIntercambioSinComOutrosPaisesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsIntercambioSinComOutrosPaises should disable id FormControl', () => {
        const formGroup = service.createOnsIntercambioSinComOutrosPaisesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
