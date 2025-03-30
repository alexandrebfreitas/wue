import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsImportacaoEnergiaNaModalidadeComercialBlocoService } from '../service/ons-importacao-energia-na-modalidade-comercial-bloco.service';
import { IOnsImportacaoEnergiaNaModalidadeComercialBloco } from '../ons-importacao-energia-na-modalidade-comercial-bloco.model';
import { OnsImportacaoEnergiaNaModalidadeComercialBlocoFormService } from './ons-importacao-energia-na-modalidade-comercial-bloco-form.service';

import { OnsImportacaoEnergiaNaModalidadeComercialBlocoUpdateComponent } from './ons-importacao-energia-na-modalidade-comercial-bloco-update.component';

describe('OnsImportacaoEnergiaNaModalidadeComercialBloco Management Update Component', () => {
  let comp: OnsImportacaoEnergiaNaModalidadeComercialBlocoUpdateComponent;
  let fixture: ComponentFixture<OnsImportacaoEnergiaNaModalidadeComercialBlocoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsImportacaoEnergiaNaModalidadeComercialBlocoFormService: OnsImportacaoEnergiaNaModalidadeComercialBlocoFormService;
  let onsImportacaoEnergiaNaModalidadeComercialBlocoService: OnsImportacaoEnergiaNaModalidadeComercialBlocoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsImportacaoEnergiaNaModalidadeComercialBlocoUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(OnsImportacaoEnergiaNaModalidadeComercialBlocoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsImportacaoEnergiaNaModalidadeComercialBlocoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsImportacaoEnergiaNaModalidadeComercialBlocoFormService = TestBed.inject(OnsImportacaoEnergiaNaModalidadeComercialBlocoFormService);
    onsImportacaoEnergiaNaModalidadeComercialBlocoService = TestBed.inject(OnsImportacaoEnergiaNaModalidadeComercialBlocoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsImportacaoEnergiaNaModalidadeComercialBloco: IOnsImportacaoEnergiaNaModalidadeComercialBloco = { id: 20607 };

      activatedRoute.data = of({ onsImportacaoEnergiaNaModalidadeComercialBloco });
      comp.ngOnInit();

      expect(comp.onsImportacaoEnergiaNaModalidadeComercialBloco).toEqual(onsImportacaoEnergiaNaModalidadeComercialBloco);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsImportacaoEnergiaNaModalidadeComercialBloco>>();
      const onsImportacaoEnergiaNaModalidadeComercialBloco = { id: 20715 };
      jest
        .spyOn(onsImportacaoEnergiaNaModalidadeComercialBlocoFormService, 'getOnsImportacaoEnergiaNaModalidadeComercialBloco')
        .mockReturnValue(onsImportacaoEnergiaNaModalidadeComercialBloco);
      jest.spyOn(onsImportacaoEnergiaNaModalidadeComercialBlocoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsImportacaoEnergiaNaModalidadeComercialBloco });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsImportacaoEnergiaNaModalidadeComercialBloco }));
      saveSubject.complete();

      // THEN
      expect(
        onsImportacaoEnergiaNaModalidadeComercialBlocoFormService.getOnsImportacaoEnergiaNaModalidadeComercialBloco,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsImportacaoEnergiaNaModalidadeComercialBlocoService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsImportacaoEnergiaNaModalidadeComercialBloco),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsImportacaoEnergiaNaModalidadeComercialBloco>>();
      const onsImportacaoEnergiaNaModalidadeComercialBloco = { id: 20715 };
      jest
        .spyOn(onsImportacaoEnergiaNaModalidadeComercialBlocoFormService, 'getOnsImportacaoEnergiaNaModalidadeComercialBloco')
        .mockReturnValue({ id: null });
      jest.spyOn(onsImportacaoEnergiaNaModalidadeComercialBlocoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsImportacaoEnergiaNaModalidadeComercialBloco: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsImportacaoEnergiaNaModalidadeComercialBloco }));
      saveSubject.complete();

      // THEN
      expect(
        onsImportacaoEnergiaNaModalidadeComercialBlocoFormService.getOnsImportacaoEnergiaNaModalidadeComercialBloco,
      ).toHaveBeenCalled();
      expect(onsImportacaoEnergiaNaModalidadeComercialBlocoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsImportacaoEnergiaNaModalidadeComercialBloco>>();
      const onsImportacaoEnergiaNaModalidadeComercialBloco = { id: 20715 };
      jest.spyOn(onsImportacaoEnergiaNaModalidadeComercialBlocoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsImportacaoEnergiaNaModalidadeComercialBloco });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsImportacaoEnergiaNaModalidadeComercialBlocoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
