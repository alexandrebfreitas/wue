import { TestBed } from '@angular/core/testing';

import {
  sampleWithNewData,
  sampleWithRequiredData,
} from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.test-samples';

import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService } from './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-form.service';

describe('OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 Form Service', () => {
  let service: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            datPdp: expect.any(Object),
            codSubmercado: expect.any(Object),
            sglTipousina: expect.any(Object),
            codUsinapdp: expect.any(Object),
            nomUsinapdp: expect.any(Object),
            numIntdespa: expect.any(Object),
            valDespasup: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            datPdp: expect.any(Object),
            codSubmercado: expect.any(Object),
            sglTipousina: expect.any(Object),
            codUsinapdp: expect.any(Object),
            nomUsinapdp: expect.any(Object),
            numIntdespa: expect.any(Object),
            valDespasup: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024', () => {
      it('should return NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 for default OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 initial value', () => {
        const formGroup = service.createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup(sampleWithNewData);

        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 =
          service.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(formGroup) as any;

        expect(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 for empty OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 initial value', () => {
        const formGroup = service.createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup();

        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 =
          service.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(formGroup) as any;

        expect(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024).toMatchObject({});
      });

      it('should return IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024', () => {
        const formGroup = service.createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup(sampleWithRequiredData);

        const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 =
          service.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(formGroup) as any;

        expect(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 should disable id FormControl', () => {
        const formGroup = service.createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
