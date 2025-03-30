import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-hidraulicos-reservatorio-base-diaria.test-samples';

import { OnsDadosHidraulicosReservatorioBaseDiariaFormService } from './ons-dados-hidraulicos-reservatorio-base-diaria-form.service';

describe('OnsDadosHidraulicosReservatorioBaseDiaria Form Service', () => {
  let service: OnsDadosHidraulicosReservatorioBaseDiariaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosHidraulicosReservatorioBaseDiariaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosHidraulicosReservatorioBaseDiariaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseDiariaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            valNivelmontante: expect.any(Object),
            valNiveljusante: expect.any(Object),
            valVolumeutilcon: expect.any(Object),
            valVazaoafluente: expect.any(Object),
            valVazaoturbinada: expect.any(Object),
            valVazaovertida: expect.any(Object),
            valVazaooutrasestruturas: expect.any(Object),
            valVazaodefluente: expect.any(Object),
            valVazaotransferida: expect.any(Object),
            valVazaonatural: expect.any(Object),
            valVazaoartificial: expect.any(Object),
            valVazaoincremental: expect.any(Object),
            valVazaoevaporacaoliquida: expect.any(Object),
            valVazaousoconsuntivo: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosHidraulicosReservatorioBaseDiaria should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseDiariaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            valNivelmontante: expect.any(Object),
            valNiveljusante: expect.any(Object),
            valVolumeutilcon: expect.any(Object),
            valVazaoafluente: expect.any(Object),
            valVazaoturbinada: expect.any(Object),
            valVazaovertida: expect.any(Object),
            valVazaooutrasestruturas: expect.any(Object),
            valVazaodefluente: expect.any(Object),
            valVazaotransferida: expect.any(Object),
            valVazaonatural: expect.any(Object),
            valVazaoartificial: expect.any(Object),
            valVazaoincremental: expect.any(Object),
            valVazaoevaporacaoliquida: expect.any(Object),
            valVazaousoconsuntivo: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosHidraulicosReservatorioBaseDiaria', () => {
      it('should return NewOnsDadosHidraulicosReservatorioBaseDiaria for default OnsDadosHidraulicosReservatorioBaseDiaria initial value', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseDiariaFormGroup(sampleWithNewData);

        const onsDadosHidraulicosReservatorioBaseDiaria = service.getOnsDadosHidraulicosReservatorioBaseDiaria(formGroup) as any;

        expect(onsDadosHidraulicosReservatorioBaseDiaria).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosHidraulicosReservatorioBaseDiaria for empty OnsDadosHidraulicosReservatorioBaseDiaria initial value', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseDiariaFormGroup();

        const onsDadosHidraulicosReservatorioBaseDiaria = service.getOnsDadosHidraulicosReservatorioBaseDiaria(formGroup) as any;

        expect(onsDadosHidraulicosReservatorioBaseDiaria).toMatchObject({});
      });

      it('should return IOnsDadosHidraulicosReservatorioBaseDiaria', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseDiariaFormGroup(sampleWithRequiredData);

        const onsDadosHidraulicosReservatorioBaseDiaria = service.getOnsDadosHidraulicosReservatorioBaseDiaria(formGroup) as any;

        expect(onsDadosHidraulicosReservatorioBaseDiaria).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosHidraulicosReservatorioBaseDiaria should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseDiariaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosHidraulicosReservatorioBaseDiaria should disable id FormControl', () => {
        const formGroup = service.createOnsDadosHidraulicosReservatorioBaseDiariaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
