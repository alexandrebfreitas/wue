import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-ofertas-preco-para-importacao.test-samples';

import { OnsOfertasPrecoParaImportacaoFormService } from './ons-ofertas-preco-para-importacao-form.service';

describe('OnsOfertasPrecoParaImportacao Form Service', () => {
  let service: OnsOfertasPrecoParaImportacaoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsOfertasPrecoParaImportacaoFormService);
  });

  describe('Service methods', () => {
    describe('createOnsOfertasPrecoParaImportacaoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsOfertasPrecoParaImportacaoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomPais: expect.any(Object),
            nomAgente: expect.any(Object),
            nomBloco: expect.any(Object),
            datIniciovalidade: expect.any(Object),
            datFimvalidade: expect.any(Object),
            valPreco: expect.any(Object),
          }),
        );
      });

      it('passing IOnsOfertasPrecoParaImportacao should create a new form with FormGroup', () => {
        const formGroup = service.createOnsOfertasPrecoParaImportacaoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomPais: expect.any(Object),
            nomAgente: expect.any(Object),
            nomBloco: expect.any(Object),
            datIniciovalidade: expect.any(Object),
            datFimvalidade: expect.any(Object),
            valPreco: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsOfertasPrecoParaImportacao', () => {
      it('should return NewOnsOfertasPrecoParaImportacao for default OnsOfertasPrecoParaImportacao initial value', () => {
        const formGroup = service.createOnsOfertasPrecoParaImportacaoFormGroup(sampleWithNewData);

        const onsOfertasPrecoParaImportacao = service.getOnsOfertasPrecoParaImportacao(formGroup) as any;

        expect(onsOfertasPrecoParaImportacao).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsOfertasPrecoParaImportacao for empty OnsOfertasPrecoParaImportacao initial value', () => {
        const formGroup = service.createOnsOfertasPrecoParaImportacaoFormGroup();

        const onsOfertasPrecoParaImportacao = service.getOnsOfertasPrecoParaImportacao(formGroup) as any;

        expect(onsOfertasPrecoParaImportacao).toMatchObject({});
      });

      it('should return IOnsOfertasPrecoParaImportacao', () => {
        const formGroup = service.createOnsOfertasPrecoParaImportacaoFormGroup(sampleWithRequiredData);

        const onsOfertasPrecoParaImportacao = service.getOnsOfertasPrecoParaImportacao(formGroup) as any;

        expect(onsOfertasPrecoParaImportacao).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsOfertasPrecoParaImportacao should not enable id FormControl', () => {
        const formGroup = service.createOnsOfertasPrecoParaImportacaoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsOfertasPrecoParaImportacao should disable id FormControl', () => {
        const formGroup = service.createOnsOfertasPrecoParaImportacaoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
