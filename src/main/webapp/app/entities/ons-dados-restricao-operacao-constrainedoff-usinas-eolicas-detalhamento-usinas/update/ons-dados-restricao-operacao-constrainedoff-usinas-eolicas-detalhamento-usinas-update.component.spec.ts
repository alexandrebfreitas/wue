import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.service';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService } from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-form.service';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasUpdateComponent } from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas-update.component';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas Management Update Component', () => {
  let comp: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasUpdateComponent;
  let fixture: ComponentFixture<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService;
  let onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasUpdateComponent],
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
      .overrideTemplate(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService = TestBed.inject(
      OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService,
    );
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService = TestBed.inject(
      OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
        { id: 2261 };

      activatedRoute.data = of({ onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas });
      comp.ngOnInit();

      expect(comp.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas).toEqual(
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
      );
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>>();
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = { id: 7419 };
      jest
        .spyOn(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService,
          'getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas',
        )
        .mockReturnValue(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas);
      jest.spyOn(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>>();
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = { id: 7419 };
      jest
        .spyOn(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService,
          'getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas',
        )
        .mockReturnValue({ id: null });
      jest.spyOn(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
      ).toHaveBeenCalled();
      expect(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>>();
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = { id: 7419 };
      jest.spyOn(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
