import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsOfertasPrecoParaImportacaoService } from '../service/ons-ofertas-preco-para-importacao.service';
import { IOnsOfertasPrecoParaImportacao } from '../ons-ofertas-preco-para-importacao.model';
import { OnsOfertasPrecoParaImportacaoFormService } from './ons-ofertas-preco-para-importacao-form.service';

import { OnsOfertasPrecoParaImportacaoUpdateComponent } from './ons-ofertas-preco-para-importacao-update.component';

describe('OnsOfertasPrecoParaImportacao Management Update Component', () => {
  let comp: OnsOfertasPrecoParaImportacaoUpdateComponent;
  let fixture: ComponentFixture<OnsOfertasPrecoParaImportacaoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsOfertasPrecoParaImportacaoFormService: OnsOfertasPrecoParaImportacaoFormService;
  let onsOfertasPrecoParaImportacaoService: OnsOfertasPrecoParaImportacaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsOfertasPrecoParaImportacaoUpdateComponent],
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
      .overrideTemplate(OnsOfertasPrecoParaImportacaoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsOfertasPrecoParaImportacaoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsOfertasPrecoParaImportacaoFormService = TestBed.inject(OnsOfertasPrecoParaImportacaoFormService);
    onsOfertasPrecoParaImportacaoService = TestBed.inject(OnsOfertasPrecoParaImportacaoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsOfertasPrecoParaImportacao: IOnsOfertasPrecoParaImportacao = { id: 24814 };

      activatedRoute.data = of({ onsOfertasPrecoParaImportacao });
      comp.ngOnInit();

      expect(comp.onsOfertasPrecoParaImportacao).toEqual(onsOfertasPrecoParaImportacao);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsOfertasPrecoParaImportacao>>();
      const onsOfertasPrecoParaImportacao = { id: 1132 };
      jest
        .spyOn(onsOfertasPrecoParaImportacaoFormService, 'getOnsOfertasPrecoParaImportacao')
        .mockReturnValue(onsOfertasPrecoParaImportacao);
      jest.spyOn(onsOfertasPrecoParaImportacaoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsOfertasPrecoParaImportacao });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsOfertasPrecoParaImportacao }));
      saveSubject.complete();

      // THEN
      expect(onsOfertasPrecoParaImportacaoFormService.getOnsOfertasPrecoParaImportacao).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsOfertasPrecoParaImportacaoService.update).toHaveBeenCalledWith(expect.objectContaining(onsOfertasPrecoParaImportacao));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsOfertasPrecoParaImportacao>>();
      const onsOfertasPrecoParaImportacao = { id: 1132 };
      jest.spyOn(onsOfertasPrecoParaImportacaoFormService, 'getOnsOfertasPrecoParaImportacao').mockReturnValue({ id: null });
      jest.spyOn(onsOfertasPrecoParaImportacaoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsOfertasPrecoParaImportacao: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsOfertasPrecoParaImportacao }));
      saveSubject.complete();

      // THEN
      expect(onsOfertasPrecoParaImportacaoFormService.getOnsOfertasPrecoParaImportacao).toHaveBeenCalled();
      expect(onsOfertasPrecoParaImportacaoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsOfertasPrecoParaImportacao>>();
      const onsOfertasPrecoParaImportacao = { id: 1132 };
      jest.spyOn(onsOfertasPrecoParaImportacaoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsOfertasPrecoParaImportacao });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsOfertasPrecoParaImportacaoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
