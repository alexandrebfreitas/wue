import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-ear-diario-ree-reservatorio-equivalente-energia.test-samples';

import { OnsEarDiarioReeReservatorioEquivalenteEnergiaFormService } from './ons-ear-diario-ree-reservatorio-equivalente-energia-form.service';

describe('OnsEarDiarioReeReservatorioEquivalenteEnergia Form Service', () => {
  let service: OnsEarDiarioReeReservatorioEquivalenteEnergiaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsEarDiarioReeReservatorioEquivalenteEnergiaFormService);
  });

  describe('Service methods', () => {
    describe('createOnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomRee: expect.any(Object),
            earData: expect.any(Object),
            earMaxRee: expect.any(Object),
            earVerifReeMwmes: expect.any(Object),
            earVerifReePercentual: expect.any(Object),
          }),
        );
      });

      it('passing IOnsEarDiarioReeReservatorioEquivalenteEnergia should create a new form with FormGroup', () => {
        const formGroup = service.createOnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomRee: expect.any(Object),
            earData: expect.any(Object),
            earMaxRee: expect.any(Object),
            earVerifReeMwmes: expect.any(Object),
            earVerifReePercentual: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsEarDiarioReeReservatorioEquivalenteEnergia', () => {
      it('should return NewOnsEarDiarioReeReservatorioEquivalenteEnergia for default OnsEarDiarioReeReservatorioEquivalenteEnergia initial value', () => {
        const formGroup = service.createOnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup(sampleWithNewData);

        const onsEarDiarioReeReservatorioEquivalenteEnergia = service.getOnsEarDiarioReeReservatorioEquivalenteEnergia(formGroup) as any;

        expect(onsEarDiarioReeReservatorioEquivalenteEnergia).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsEarDiarioReeReservatorioEquivalenteEnergia for empty OnsEarDiarioReeReservatorioEquivalenteEnergia initial value', () => {
        const formGroup = service.createOnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup();

        const onsEarDiarioReeReservatorioEquivalenteEnergia = service.getOnsEarDiarioReeReservatorioEquivalenteEnergia(formGroup) as any;

        expect(onsEarDiarioReeReservatorioEquivalenteEnergia).toMatchObject({});
      });

      it('should return IOnsEarDiarioReeReservatorioEquivalenteEnergia', () => {
        const formGroup = service.createOnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup(sampleWithRequiredData);

        const onsEarDiarioReeReservatorioEquivalenteEnergia = service.getOnsEarDiarioReeReservatorioEquivalenteEnergia(formGroup) as any;

        expect(onsEarDiarioReeReservatorioEquivalenteEnergia).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsEarDiarioReeReservatorioEquivalenteEnergia should not enable id FormControl', () => {
        const formGroup = service.createOnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsEarDiarioReeReservatorioEquivalenteEnergia should disable id FormControl', () => {
        const formGroup = service.createOnsEarDiarioReeReservatorioEquivalenteEnergiaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
