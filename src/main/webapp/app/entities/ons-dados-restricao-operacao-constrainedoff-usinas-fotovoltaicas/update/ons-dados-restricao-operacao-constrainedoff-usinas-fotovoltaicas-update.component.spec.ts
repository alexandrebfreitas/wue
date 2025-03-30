import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.service';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService } from './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-form.service';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasUpdateComponent } from './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-update.component';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas Management Update Component', () => {
  let comp: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasUpdateComponent;
  let fixture: ComponentFixture<OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService;
  let onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasUpdateComponent],
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
      .overrideTemplate(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService = TestBed.inject(
      OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService,
    );
    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService = TestBed.inject(
      OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = {
        id: 130,
      };

      activatedRoute.data = of({ onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas });
      comp.ngOnInit();

      expect(comp.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas).toEqual(
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
      );
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>>();
      const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = { id: 27295 };
      jest
        .spyOn(
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService,
          'getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas',
        )
        .mockReturnValue(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas);
      jest.spyOn(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService.getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>>();
      const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = { id: 27295 };
      jest
        .spyOn(
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService,
          'getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas',
        )
        .mockReturnValue({ id: null });
      jest.spyOn(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService.getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
      ).toHaveBeenCalled();
      expect(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>>();
      const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = { id: 27295 };
      jest.spyOn(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
