import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.service';
import { IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas } from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.model';
import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService } from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-form.service';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasUpdateComponent } from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-update.component';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas Management Update Component', () => {
  let comp: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasUpdateComponent;
  let fixture: ComponentFixture<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService;
  let onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasUpdateComponent],
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
      .overrideTemplate(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService = TestBed.inject(
      OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService,
    );
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService = TestBed.inject(
      OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = { id: 22752 };

      activatedRoute.data = of({ onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas });
      comp.ngOnInit();

      expect(comp.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas).toEqual(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>>();
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = { id: 11763 };
      jest
        .spyOn(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService, 'getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas')
        .mockReturnValue(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas);
      jest.spyOn(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>>();
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = { id: 11763 };
      jest
        .spyOn(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService, 'getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas')
        .mockReturnValue({ id: null });
      jest.spyOn(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
      ).toHaveBeenCalled();
      expect(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>>();
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = { id: 11763 };
      jest.spyOn(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
