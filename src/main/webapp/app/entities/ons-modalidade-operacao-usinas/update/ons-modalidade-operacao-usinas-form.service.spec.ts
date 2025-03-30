import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-modalidade-operacao-usinas.test-samples';

import { OnsModalidadeOperacaoUsinasFormService } from './ons-modalidade-operacao-usinas-form.service';

describe('OnsModalidadeOperacaoUsinas Form Service', () => {
  let service: OnsModalidadeOperacaoUsinasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsModalidadeOperacaoUsinasFormService);
  });

  describe('Service methods', () => {
    describe('createOnsModalidadeOperacaoUsinasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsModalidadeOperacaoUsinasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomUsina: expect.any(Object),
            ceg: expect.any(Object),
            nomModalidadeoperacao: expect.any(Object),
            valPotenciaautorizada: expect.any(Object),
            sglCentrooperacao: expect.any(Object),
            nomPontoconexao: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            stsAneel: expect.any(Object),
          }),
        );
      });

      it('passing IOnsModalidadeOperacaoUsinas should create a new form with FormGroup', () => {
        const formGroup = service.createOnsModalidadeOperacaoUsinasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomUsina: expect.any(Object),
            ceg: expect.any(Object),
            nomModalidadeoperacao: expect.any(Object),
            valPotenciaautorizada: expect.any(Object),
            sglCentrooperacao: expect.any(Object),
            nomPontoconexao: expect.any(Object),
            idEstado: expect.any(Object),
            nomEstado: expect.any(Object),
            stsAneel: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsModalidadeOperacaoUsinas', () => {
      it('should return NewOnsModalidadeOperacaoUsinas for default OnsModalidadeOperacaoUsinas initial value', () => {
        const formGroup = service.createOnsModalidadeOperacaoUsinasFormGroup(sampleWithNewData);

        const onsModalidadeOperacaoUsinas = service.getOnsModalidadeOperacaoUsinas(formGroup) as any;

        expect(onsModalidadeOperacaoUsinas).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsModalidadeOperacaoUsinas for empty OnsModalidadeOperacaoUsinas initial value', () => {
        const formGroup = service.createOnsModalidadeOperacaoUsinasFormGroup();

        const onsModalidadeOperacaoUsinas = service.getOnsModalidadeOperacaoUsinas(formGroup) as any;

        expect(onsModalidadeOperacaoUsinas).toMatchObject({});
      });

      it('should return IOnsModalidadeOperacaoUsinas', () => {
        const formGroup = service.createOnsModalidadeOperacaoUsinasFormGroup(sampleWithRequiredData);

        const onsModalidadeOperacaoUsinas = service.getOnsModalidadeOperacaoUsinas(formGroup) as any;

        expect(onsModalidadeOperacaoUsinas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsModalidadeOperacaoUsinas should not enable id FormControl', () => {
        const formGroup = service.createOnsModalidadeOperacaoUsinasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsModalidadeOperacaoUsinas should disable id FormControl', () => {
        const formGroup = service.createOnsModalidadeOperacaoUsinasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
