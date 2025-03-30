import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.service';
import { IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.model';
import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-form.service';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalUpdateComponent } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-update.component';

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal Management Update Component', () => {
  let comp: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalUpdateComponent;
  let fixture: ComponentFixture<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService;
  let onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalUpdateComponent],
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
      .overrideTemplate(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService = TestBed.inject(
      OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService,
    );
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService = TestBed.inject(
      OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
        { id: 10386 };

      activatedRoute.data = of({ onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal });
      comp.ngOnInit();

      expect(comp.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal).toEqual(
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
      );
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal>>();
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal = { id: 18913 };
      jest
        .spyOn(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService,
          'getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal',
        )
        .mockReturnValue(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal);
      jest.spyOn(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal }));
      saveSubject.complete();

      // THEN
      expect(
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal>>();
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal = { id: 18913 };
      jest
        .spyOn(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService,
          'getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal',
        )
        .mockReturnValue({ id: null });
      jest.spyOn(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal }));
      saveSubject.complete();

      // THEN
      expect(
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalFormService.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
      ).toHaveBeenCalled();
      expect(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal>>();
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal = { id: 18913 };
      jest.spyOn(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
