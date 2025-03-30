import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-descontinuado-precipitacao-diaria-observada.test-samples';

import { OnsDescontinuadoPrecipitacaoDiariaObservadaFormService } from './ons-descontinuado-precipitacao-diaria-observada-form.service';

describe('OnsDescontinuadoPrecipitacaoDiariaObservada Form Service', () => {
  let service: OnsDescontinuadoPrecipitacaoDiariaObservadaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDescontinuadoPrecipitacaoDiariaObservadaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codEstacao: expect.any(Object),
            valLatitude: expect.any(Object),
            valLongitude: expect.any(Object),
            valMedida: expect.any(Object),
            datObservada: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDescontinuadoPrecipitacaoDiariaObservada should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codEstacao: expect.any(Object),
            valLatitude: expect.any(Object),
            valLongitude: expect.any(Object),
            valMedida: expect.any(Object),
            datObservada: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDescontinuadoPrecipitacaoDiariaObservada', () => {
      it('should return NewOnsDescontinuadoPrecipitacaoDiariaObservada for default OnsDescontinuadoPrecipitacaoDiariaObservada initial value', () => {
        const formGroup = service.createOnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup(sampleWithNewData);

        const onsDescontinuadoPrecipitacaoDiariaObservada = service.getOnsDescontinuadoPrecipitacaoDiariaObservada(formGroup) as any;

        expect(onsDescontinuadoPrecipitacaoDiariaObservada).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDescontinuadoPrecipitacaoDiariaObservada for empty OnsDescontinuadoPrecipitacaoDiariaObservada initial value', () => {
        const formGroup = service.createOnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup();

        const onsDescontinuadoPrecipitacaoDiariaObservada = service.getOnsDescontinuadoPrecipitacaoDiariaObservada(formGroup) as any;

        expect(onsDescontinuadoPrecipitacaoDiariaObservada).toMatchObject({});
      });

      it('should return IOnsDescontinuadoPrecipitacaoDiariaObservada', () => {
        const formGroup = service.createOnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup(sampleWithRequiredData);

        const onsDescontinuadoPrecipitacaoDiariaObservada = service.getOnsDescontinuadoPrecipitacaoDiariaObservada(formGroup) as any;

        expect(onsDescontinuadoPrecipitacaoDiariaObservada).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDescontinuadoPrecipitacaoDiariaObservada should not enable id FormControl', () => {
        const formGroup = service.createOnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDescontinuadoPrecipitacaoDiariaObservada should disable id FormControl', () => {
        const formGroup = service.createOnsDescontinuadoPrecipitacaoDiariaObservadaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
