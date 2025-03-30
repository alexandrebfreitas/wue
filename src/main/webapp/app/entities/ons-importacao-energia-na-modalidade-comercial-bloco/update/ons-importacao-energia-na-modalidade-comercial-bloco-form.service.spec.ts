import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../ons-importacao-energia-na-modalidade-comercial-bloco.test-samples';

import { OnsImportacaoEnergiaNaModalidadeComercialBlocoFormService } from './ons-importacao-energia-na-modalidade-comercial-bloco-form.service';

describe('OnsImportacaoEnergiaNaModalidadeComercialBloco Form Service', () => {
  let service: OnsImportacaoEnergiaNaModalidadeComercialBlocoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnsImportacaoEnergiaNaModalidadeComercialBlocoFormService);
  });

  describe('Service methods', () => {
    describe('createOnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomPais: expect.any(Object),
            nomAgente: expect.any(Object),
            nomBloco: expect.any(Object),
            codBloco: expect.any(Object),
            dinInstante: expect.any(Object),
            valImportacaoprogramada: expect.any(Object),
            valImportacaodespachada: expect.any(Object),
            valImportacaoverificada: expect.any(Object),
            valPreco: expect.any(Object),
          }),
        );
      });

      it('passing IOnsImportacaoEnergiaNaModalidadeComercialBloco should create a new form with FormGroup', () => {
        const formGroup = service.createOnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nomPais: expect.any(Object),
            nomAgente: expect.any(Object),
            nomBloco: expect.any(Object),
            codBloco: expect.any(Object),
            dinInstante: expect.any(Object),
            valImportacaoprogramada: expect.any(Object),
            valImportacaodespachada: expect.any(Object),
            valImportacaoverificada: expect.any(Object),
            valPreco: expect.any(Object),
          }),
        );
      });
    });

    describe('getOnsImportacaoEnergiaNaModalidadeComercialBloco', () => {
      it('should return NewOnsImportacaoEnergiaNaModalidadeComercialBloco for default OnsImportacaoEnergiaNaModalidadeComercialBloco initial value', () => {
        const formGroup = service.createOnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup(sampleWithNewData);

        const onsImportacaoEnergiaNaModalidadeComercialBloco = service.getOnsImportacaoEnergiaNaModalidadeComercialBloco(formGroup) as any;

        expect(onsImportacaoEnergiaNaModalidadeComercialBloco).toMatchObject(sampleWithNewData);
      });

      it('should return NewOnsImportacaoEnergiaNaModalidadeComercialBloco for empty OnsImportacaoEnergiaNaModalidadeComercialBloco initial value', () => {
        const formGroup = service.createOnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup();

        const onsImportacaoEnergiaNaModalidadeComercialBloco = service.getOnsImportacaoEnergiaNaModalidadeComercialBloco(formGroup) as any;

        expect(onsImportacaoEnergiaNaModalidadeComercialBloco).toMatchObject({});
      });

      it('should return IOnsImportacaoEnergiaNaModalidadeComercialBloco', () => {
        const formGroup = service.createOnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup(sampleWithRequiredData);

        const onsImportacaoEnergiaNaModalidadeComercialBloco = service.getOnsImportacaoEnergiaNaModalidadeComercialBloco(formGroup) as any;

        expect(onsImportacaoEnergiaNaModalidadeComercialBloco).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOnsImportacaoEnergiaNaModalidadeComercialBloco should not enable id FormControl', () => {
        const formGroup = service.createOnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOnsImportacaoEnergiaNaModalidadeComercialBloco should disable id FormControl', () => {
        const formGroup = service.createOnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
