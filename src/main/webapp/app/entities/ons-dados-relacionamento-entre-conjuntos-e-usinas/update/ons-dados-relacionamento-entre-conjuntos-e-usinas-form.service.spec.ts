import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-dados-relacionamento-entre-conjuntos-e-usinas.test-samples';

import { OnsDadosRelacionamentoEntreConjuntosEUsinasFormService } from './ons-dados-relacionamento-entre-conjuntos-e-usinas-form.service';

describe('OnsDadosRelacionamentoEntreConjuntosEUsinas Form Service', () => {
  let service: OnsDadosRelacionamentoEntreConjuntosEUsinasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsDadosRelacionamentoEntreConjuntosEUsinasFormService);
  });

  describe('Service methods', () => {
    describe('createOnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            estadId: expect.any(Object),
            nomEstado: expect.any(Object),
            idTipousina: expect.any(Object),
            idConjuntousina: expect.any(Object),
            idOnsConjunto: expect.any(Object),
            idOnsUsina: expect.any(Object),
            nomConjunto: expect.any(Object),
            nomUsina: expect.any(Object),
            ceg: expect.any(Object),
            datIniciorelacionamento: expect.any(Object),
            datFimrelacionamento: expect.any(Object),
          }),
        );
      });

      it('passing IOnsDadosRelacionamentoEntreConjuntosEUsinas should create a new form with FormGroup', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idSubsistema: expect.any(Object),
            nomSubsistema: expect.any(Object),
            estadId: expect.any(Object),
            nomEstado: expect.any(Object),
            idTipousina: expect.any(Object),
            idConjuntousina: expect.any(Object),
            idOnsConjunto: expect.any(Object),
            idOnsUsina: expect.any(Object),
            nomConjunto: expect.any(Object),
            nomUsina: expect.any(Object),
            ceg: expect.any(Object),
            datIniciorelacionamento: expect.any(Object),
            datFimrelacionamento: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsDadosRelacionamentoEntreConjuntosEUsinas', () => {
      it('should return NewOnsDadosRelacionamentoEntreConjuntosEUsinas for default OnsDadosRelacionamentoEntreConjuntosEUsinas initial value', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup(sampleWithNewData);

        const onsDadosRelacionamentoEntreConjuntosEUsinas = service.getOnsDadosRelacionamentoEntreConjuntosEUsinas(formGroup) as any;

        expect(onsDadosRelacionamentoEntreConjuntosEUsinas).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsDadosRelacionamentoEntreConjuntosEUsinas for empty OnsDadosRelacionamentoEntreConjuntosEUsinas initial value', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup();

        const onsDadosRelacionamentoEntreConjuntosEUsinas = service.getOnsDadosRelacionamentoEntreConjuntosEUsinas(formGroup) as any;

        expect(onsDadosRelacionamentoEntreConjuntosEUsinas).toMatchObject({});
      });

      it('should return IOnsDadosRelacionamentoEntreConjuntosEUsinas', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup(sampleWithRequiredData);

        const onsDadosRelacionamentoEntreConjuntosEUsinas = service.getOnsDadosRelacionamentoEntreConjuntosEUsinas(formGroup) as any;

        expect(onsDadosRelacionamentoEntreConjuntosEUsinas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsDadosRelacionamentoEntreConjuntosEUsinas should not enable id FormControl', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsDadosRelacionamentoEntreConjuntosEUsinas should disable id FormControl', () => {
        const formGroup = service.createOnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
