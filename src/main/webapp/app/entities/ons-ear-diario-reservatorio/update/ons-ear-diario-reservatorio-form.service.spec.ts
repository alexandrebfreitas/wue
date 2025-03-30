import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-ear-diario-reservatorio.test-samples';

import { OnsEarDiarioReservatorioFormService } from './ons-ear-diario-reservatorio-form.service';

describe('OnsEarDiarioReservatorio Form Service', () => {
  let service: OnsEarDiarioReservatorioFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsEarDiarioReservatorioFormService);
  });

  describe('Service methods', () => {
    describe('createOnsEarDiarioReservatorioFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsEarDiarioReservatorioFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistemaJusante: expect.any(Object),
            nomSubsistemaJusante: expect.any(Object),
            earData: expect.any(Object),
            earReservatorioSubsistemaProprioMwmes: expect.any(Object),
            earReservatorioSubsistemaJusanteMwmes: expect.any(Object),
            earmaxReservatorioSubsistemaProprioMwmes: expect.any(Object),
            earmaxReservatorioSubsistemaJusanteMwmes: expect.any(Object),
            earReservatorioPercentual: expect.any(Object),
            earTotalMwmes: expect.any(Object),
            earMaximaTotalMwmes: expect.any(Object),
            valContribearbacia: expect.any(Object),
            valContribearmaxbacia: expect.any(Object),
            valContribearsubsistema: expect.any(Object),
            valContribearmaxsubsistema: expect.any(Object),
            valContribearsubsistemajusante: expect.any(Object),
            valContribearmaxsubsistemajusante: expect.any(Object),
            valContribearsin: expect.any(Object),
            valContribearmaxsin: expect.any(Object),
          }),
        );
      });

      it('passing IOnsEarDiarioReservatorio should create a new form with FormGroup', () => {
        const formGroup = service.createOnsEarDiarioReservatorioFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistemaJusante: expect.any(Object),
            nomSubsistemaJusante: expect.any(Object),
            earData: expect.any(Object),
            earReservatorioSubsistemaProprioMwmes: expect.any(Object),
            earReservatorioSubsistemaJusanteMwmes: expect.any(Object),
            earmaxReservatorioSubsistemaProprioMwmes: expect.any(Object),
            earmaxReservatorioSubsistemaJusanteMwmes: expect.any(Object),
            earReservatorioPercentual: expect.any(Object),
            earTotalMwmes: expect.any(Object),
            earMaximaTotalMwmes: expect.any(Object),
            valContribearbacia: expect.any(Object),
            valContribearmaxbacia: expect.any(Object),
            valContribearsubsistema: expect.any(Object),
            valContribearmaxsubsistema: expect.any(Object),
            valContribearsubsistemajusante: expect.any(Object),
            valContribearmaxsubsistemajusante: expect.any(Object),
            valContribearsin: expect.any(Object),
            valContribearmaxsin: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsEarDiarioReservatorio', () => {
      it('should return NewOnsEarDiarioReservatorio for default OnsEarDiarioReservatorio initial value', () => {
        const formGroup = service.createOnsEarDiarioReservatorioFormGroup(sampleWithNewData);

        const onsEarDiarioReservatorio = service.getOnsEarDiarioReservatorio(formGroup) as any;

        expect(onsEarDiarioReservatorio).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsEarDiarioReservatorio for empty OnsEarDiarioReservatorio initial value', () => {
        const formGroup = service.createOnsEarDiarioReservatorioFormGroup();

        const onsEarDiarioReservatorio = service.getOnsEarDiarioReservatorio(formGroup) as any;

        expect(onsEarDiarioReservatorio).toMatchObject({});
      });

      it('should return IOnsEarDiarioReservatorio', () => {
        const formGroup = service.createOnsEarDiarioReservatorioFormGroup(sampleWithRequiredData);

        const onsEarDiarioReservatorio = service.getOnsEarDiarioReservatorio(formGroup) as any;

        expect(onsEarDiarioReservatorio).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsEarDiarioReservatorio should not enable id FormControl', () => {
        const formGroup = service.createOnsEarDiarioReservatorioFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsEarDiarioReservatorio should disable id FormControl', () => {
        const formGroup = service.createOnsEarDiarioReservatorioFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
