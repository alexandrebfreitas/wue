import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-hidraulicos-reservatorio-base-horaria.test-samples';

import { OnsDadosHidraulicosReservatorioBaseHorariaFormService } from './ons-dados-hidraulicos-reservatorio-base-horaria-form.service';

describe('OnsDadosHidraulicosReservatorioBaseHoraria Form Service', () => {
  let service: OnsDadosHidraulicosReservatorioBaseHorariaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosHidraulicosReservatorioBaseHorariaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosHidraulicosReservatorioBaseHorariaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseHorariaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            valVolumeutil: expect.any(Object),
            valVazaoafluente: expect.any(Object),
            valVazaodefluente: expect.any(Object),
            valVazaoturbinada: expect.any(Object),
            valVazaovertida: expect.any(Object),
            valVazaooutrasestruturas: expect.any(Object),
            valVazaotransferida: expect.any(Object),
            valVazaovertidanaoturbinavel: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosHidraulicosReservatorioBaseHoraria should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseHorariaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            valVolumeutil: expect.any(Object),
            valVazaoafluente: expect.any(Object),
            valVazaodefluente: expect.any(Object),
            valVazaoturbinada: expect.any(Object),
            valVazaovertida: expect.any(Object),
            valVazaooutrasestruturas: expect.any(Object),
            valVazaotransferida: expect.any(Object),
            valVazaovertidanaoturbinavel: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosHidraulicosReservatorioBaseHoraria', () => {
      it('should return NewOnsDadosHidraulicosReservatorioBaseHoraria for default OnsDadosHidraulicosReservatorioBaseHoraria initial value', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseHorariaFormGroup(sampleWithNewData);

        const onsDadosHidraulicosReservatorioBaseHoraria = service.getOnsDadosHidraulicosReservatorioBaseHoraria(formGroup) as any;

        expect(onsDadosHidraulicosReservatorioBaseHoraria).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosHidraulicosReservatorioBaseHoraria for empty OnsDadosHidraulicosReservatorioBaseHoraria initial value', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseHorariaFormGroup();

        const onsDadosHidraulicosReservatorioBaseHoraria = service.getOnsDadosHidraulicosReservatorioBaseHoraria(formGroup) as any;

        expect(onsDadosHidraulicosReservatorioBaseHoraria).toMatchObject({});
      });

      it('should return IOnsDadosHidraulicosReservatorioBaseHoraria', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseHorariaFormGroup(sampleWithRequiredData);

        const onsDadosHidraulicosReservatorioBaseHoraria = service.getOnsDadosHidraulicosReservatorioBaseHoraria(formGroup) as any;

        expect(onsDadosHidraulicosReservatorioBaseHoraria).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosHidraulicosReservatorioBaseHoraria should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseHorariaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosHidraulicosReservatorioBaseHoraria should disable id FormControl', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseHorariaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
