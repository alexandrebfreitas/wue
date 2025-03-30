import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-curva-carga-horaria.test-samples';

import { OnsCurvaCargaHorariaFormService } from './ons-curva-carga-horaria-form.service';

describe('OnsCurvaCargaHoraria Form Service', () => {
  let service: OnsCurvaCargaHorariaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsCurvaCargaHorariaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsCurvaCargaHorariaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsCurvaCargaHorariaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            dinInstante: expect.any(Object),
            valCargaenergiahomwmed: expect.any(Object),
          }),
        );
      });

      it('passing IOnsCurvaCargaHoraria should create a new form with FormGroup', () => {
        const formGroup = service.createOnsCurvaCargaHorariaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            dinInstante: expect.any(Object),
            valCargaenergiahomwmed: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsCurvaCargaHoraria', () => {
      it('should return NewOnsCurvaCargaHoraria for default OnsCurvaCargaHoraria initial value', () => {
        const formGroup = service.createOnsCurvaCargaHorariaFormGroup(sampleWithNewData);

        const onsCurvaCargaHoraria = service.getOnsCurvaCargaHoraria(formGroup) as any;

        expect(onsCurvaCargaHoraria).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsCurvaCargaHoraria for empty OnsCurvaCargaHoraria initial value', () => {
        const formGroup = service.createOnsCurvaCargaHorariaFormGroup();

        const onsCurvaCargaHoraria = service.getOnsCurvaCargaHoraria(formGroup) as any;

        expect(onsCurvaCargaHoraria).toMatchObject({});
      });

      it('should return IOnsCurvaCargaHoraria', () => {
        const formGroup = service.createOnsCurvaCargaHorariaFormGroup(sampleWithRequiredData);

        const onsCurvaCargaHoraria = service.getOnsCurvaCargaHoraria(formGroup) as any;

        expect(onsCurvaCargaHoraria).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsCurvaCargaHoraria should not enable id FormControl', () => {
        const formGroup = service.createOnsCurvaCargaHorariaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsCurvaCargaHoraria should disable id FormControl', () => {
        const formGroup = service.createOnsCurvaCargaHorariaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
