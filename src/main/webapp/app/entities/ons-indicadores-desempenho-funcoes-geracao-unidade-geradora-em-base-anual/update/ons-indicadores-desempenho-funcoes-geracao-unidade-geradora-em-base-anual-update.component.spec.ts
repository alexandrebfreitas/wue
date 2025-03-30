import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.service';
import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.model';
import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-form.service';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualUpdateComponent } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-update.component';

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual Management Update Component', () => {
  let comp: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualUpdateComponent;
  let fixture: ComponentFixture<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService;
  let onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualUpdateComponent],
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
      .overrideTemplate(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService = TestBed.inject(
      OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService,
    );
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService = TestBed.inject(
      OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
        { id: 10370 };

      activatedRoute.data = of({ onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual });
      comp.ngOnInit();

      expect(comp.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual).toEqual(
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
      );
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual>>();
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual = { id: 28296 };
      jest
        .spyOn(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService,
          'getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual',
        )
        .mockReturnValue(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual);
      jest.spyOn(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual }));
      saveSubject.complete();

      // THEN
      expect(
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual>>();
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual = { id: 28296 };
      jest
        .spyOn(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService,
          'getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual',
        )
        .mockReturnValue({ id: null });
      jest.spyOn(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual }));
      saveSubject.complete();

      // THEN
      expect(
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualFormService.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
      ).toHaveBeenCalled();
      expect(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual>>();
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual = { id: 28296 };
      jest.spyOn(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
