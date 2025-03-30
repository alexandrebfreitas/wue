import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-ena-diario-ree-reservatorio-equivalente-energia.test-samples';

import { OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormService } from './ons-ena-diario-ree-reservatorio-equivalente-energia-form.service';

describe('OnsEnaDiarioReeReservatorioEquivalenteEnergia Form Service', () => {
  let service: OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsEnaDiarioReeReservatorioEquivalenteEnergiaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            enaArmazenavelReePercentualmlt: expect.any(Object),
          }),
        );
      });

      it('passing IOnsEnaDiarioReeReservatorioEquivalenteEnergia should create a new form with FormGroup', () => {
        const formGroup = service.createOnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            enaArmazenavelReePercentualmlt: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsEnaDiarioReeReservatorioEquivalenteEnergia', () => {
      it('should return NewOnsEnaDiarioReeReservatorioEquivalenteEnergia for default OnsEnaDiarioReeReservatorioEquivalenteEnergia initial value', () => {
        const formGroup = service.createOnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup(sampleWithNewData);

        const onsEnaDiarioReeReservatorioEquivalenteEnergia = service.getOnsEnaDiarioReeReservatorioEquivalenteEnergia(formGroup) as any;

        expect(onsEnaDiarioReeReservatorioEquivalenteEnergia).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsEnaDiarioReeReservatorioEquivalenteEnergia for empty OnsEnaDiarioReeReservatorioEquivalenteEnergia initial value', () => {
        const formGroup = service.createOnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup();

        const onsEnaDiarioReeReservatorioEquivalenteEnergia = service.getOnsEnaDiarioReeReservatorioEquivalenteEnergia(formGroup) as any;

        expect(onsEnaDiarioReeReservatorioEquivalenteEnergia).toMatchObject({});
      });

      it('should return IOnsEnaDiarioReeReservatorioEquivalenteEnergia', () => {
        const formGroup = service.createOnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup(sampleWithRequiredData);

        const onsEnaDiarioReeReservatorioEquivalenteEnergia = service.getOnsEnaDiarioReeReservatorioEquivalenteEnergia(formGroup) as any;

        expect(onsEnaDiarioReeReservatorioEquivalenteEnergia).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsEnaDiarioReeReservatorioEquivalenteEnergia should not enable id FormControl', () => {
        const formGroup = service.createOnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsEnaDiarioReeReservatorioEquivalenteEnergia should disable id FormControl', () => {
        const formGroup = service.createOnsEnaDiarioReeReservatorioEquivalenteEnergiaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
