import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-ena-diario-reservatorio.test-samples';

import { OnsEnaDiarioReservatorioFormService } from './ons-ena-diario-reservatorio-form.service';

describe('OnsEnaDiarioReservatorio Form Service', () => {
  let service: OnsEnaDiarioReservatorioFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsEnaDiarioReservatorioFormService);
  });

  describe('Service methods', () => {
    describe('createOnsEnaDiarioReservatorioFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsEnaDiarioReservatorioFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            enaBrutaResMwmed: expect.any(Object),
            enaBrutaResPercentualmlt: expect.any(Object),
            enaArmazenavelResMwmed: expect.any(Object),
            enaArmazenavelResPercentualmlt: expect.any(Object),
            enaQuedaBruta: expect.any(Object),
            mltEna: expect.any(Object),
          }),
        );
      });

      it('passing IOnsEnaDiarioReservatorio should create a new form with FormGroup', () => {
        const formGroup = service.createOnsEnaDiarioReservatorioFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            enaBrutaResMwmed: expect.any(Object),
            enaBrutaResPercentualmlt: expect.any(Object),
            enaArmazenavelResMwmed: expect.any(Object),
            enaArmazenavelResPercentualmlt: expect.any(Object),
            enaQuedaBruta: expect.any(Object),
            mltEna: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsEnaDiarioReservatorio', () => {
      it('should return NewOnsEnaDiarioReservatorio for default OnsEnaDiarioReservatorio initial value', () => {
        const formGroup = service.createOnsEnaDiarioReservatorioFormGroup(sampleWithNewData);

        const onsEnaDiarioReservatorio = service.getOnsEnaDiarioReservatorio(formGroup) as any;

        expect(onsEnaDiarioReservatorio).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsEnaDiarioReservatorio for empty OnsEnaDiarioReservatorio initial value', () => {
        const formGroup = service.createOnsEnaDiarioReservatorioFormGroup();

        const onsEnaDiarioReservatorio = service.getOnsEnaDiarioReservatorio(formGroup) as any;

        expect(onsEnaDiarioReservatorio).toMatchObject({});
      });

      it('should return IOnsEnaDiarioReservatorio', () => {
        const formGroup = service.createOnsEnaDiarioReservatorioFormGroup(sampleWithRequiredData);

        const onsEnaDiarioReservatorio = service.getOnsEnaDiarioReservatorio(formGroup) as any;

        expect(onsEnaDiarioReservatorio).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsEnaDiarioReservatorio should not enable id FormControl', () => {
        const formGroup = service.createOnsEnaDiarioReservatorioFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsEnaDiarioReservatorio should disable id FormControl', () => {
        const formGroup = service.createOnsEnaDiarioReservatorioFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
