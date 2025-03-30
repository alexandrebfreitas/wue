import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService } from '../service/ons-dados-valores-previsao-versus-programado-eolicas-e-solares.service';
import { IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares } from '../ons-dados-valores-previsao-versus-programado-eolicas-e-solares.model';
import { OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService } from './ons-dados-valores-previsao-versus-programado-eolicas-e-solares-form.service';

import { OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresUpdateComponent } from './ons-dados-valores-previsao-versus-programado-eolicas-e-solares-update.component';

describe('OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares Management Update Component', () => {
  let comp: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresUpdateComponent;
  let fixture: ComponentFixture<OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService;
  let onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresUpdateComponent],
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
      .overrideTemplate(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService = TestBed.inject(
      OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService,
    );
    onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService = TestBed.inject(
      OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares = { id: 11822 };

      activatedRoute.data = of({ onsDadosValoresPrevisaoVersusProgramadoEolicasESolares });
      comp.ngOnInit();

      expect(comp.onsDadosValoresPrevisaoVersusProgramadoEolicasESolares).toEqual(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>>();
      const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares = { id: 3628 };
      jest
        .spyOn(
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService,
          'getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares',
        )
        .mockReturnValue(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares);
      jest.spyOn(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosValoresPrevisaoVersusProgramadoEolicasESolares });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosValoresPrevisaoVersusProgramadoEolicasESolares }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService.getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>>();
      const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares = { id: 3628 };
      jest
        .spyOn(
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService,
          'getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares',
        )
        .mockReturnValue({ id: null });
      jest.spyOn(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosValoresPrevisaoVersusProgramadoEolicasESolares }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService.getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
      ).toHaveBeenCalled();
      expect(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>>();
      const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares = { id: 3628 };
      jest.spyOn(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosValoresPrevisaoVersusProgramadoEolicasESolares });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
