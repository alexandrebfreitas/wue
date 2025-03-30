import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-intercambio-energia-modalidade.test-samples';

import { OnsDadosIntercambioEnergiaModalidadeFormService } from './ons-dados-intercambio-energia-modalidade-form.service';

describe('OnsDadosIntercambioEnergiaModalidade Form Service', () => {
  let service: OnsDadosIntercambioEnergiaModalidadeFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosIntercambioEnergiaModalidadeFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosIntercambioEnergiaModalidadeFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosIntercambioEnergiaModalidadeFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomConversora: expect.any(Object),
            dinInstante: expect.any(Object),
            valModalidadecontratual: expect.any(Object),
            valModalidadeemergencial: expect.any(Object),
            valModalidadeoportunidade: expect.any(Object),
            valModalidadeteste: expect.any(Object),
            valModalidadeexcepcional: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosIntercambioEnergiaModalidade should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosIntercambioEnergiaModalidadeFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomConversora: expect.any(Object),
            dinInstante: expect.any(Object),
            valModalidadecontratual: expect.any(Object),
            valModalidadeemergencial: expect.any(Object),
            valModalidadeoportunidade: expect.any(Object),
            valModalidadeteste: expect.any(Object),
            valModalidadeexcepcional: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosIntercambioEnergiaModalidade', () => {
      it('should return NewOnsDadosIntercambioEnergiaModalidade for default OnsDadosIntercambioEnergiaModalidade initial value', () => {
        const formGroup = service.createOnsDadosIntercambioEnergiaModalidadeFormGroup(sampleWithNewData);

        const onsDadosIntercambioEnergiaModalidade = service.getOnsDadosIntercambioEnergiaModalidade(formGroup) as any;

        expect(onsDadosIntercambioEnergiaModalidade).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosIntercambioEnergiaModalidade for empty OnsDadosIntercambioEnergiaModalidade initial value', () => {
        const formGroup = service.createOnsDadosIntercambioEnergiaModalidadeFormGroup();

        const onsDadosIntercambioEnergiaModalidade = service.getOnsDadosIntercambioEnergiaModalidade(formGroup) as any;

        expect(onsDadosIntercambioEnergiaModalidade).toMatchObject({});
      });

      it('should return IOnsDadosIntercambioEnergiaModalidade', () => {
        const formGroup = service.createOnsDadosIntercambioEnergiaModalidadeFormGroup(sampleWithRequiredData);

        const onsDadosIntercambioEnergiaModalidade = service.getOnsDadosIntercambioEnergiaModalidade(formGroup) as any;

        expect(onsDadosIntercambioEnergiaModalidade).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosIntercambioEnergiaModalidade should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosIntercambioEnergiaModalidadeFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosIntercambioEnergiaModalidade should disable id FormControl', () => {
        const formGroup = service.createOnsDadosIntercambioEnergiaModalidadeFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
