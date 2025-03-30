import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosHidrologicosVolumeEsperaRecomendadoService } from '../service/ons-dados-hidrologicos-volume-espera-recomendado.service';
import { IOnsDadosHidrologicosVolumeEsperaRecomendado } from '../ons-dados-hidrologicos-volume-espera-recomendado.model';
import { OnsDadosHidrologicosVolumeEsperaRecomendadoFormService } from './ons-dados-hidrologicos-volume-espera-recomendado-form.service';

import { OnsDadosHidrologicosVolumeEsperaRecomendadoUpdateComponent } from './ons-dados-hidrologicos-volume-espera-recomendado-update.component';

describe('OnsDadosHidrologicosVolumeEsperaRecomendado Management Update Component', () => {
  let comp: OnsDadosHidrologicosVolumeEsperaRecomendadoUpdateComponent;
  let fixture: ComponentFixture<OnsDadosHidrologicosVolumeEsperaRecomendadoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosHidrologicosVolumeEsperaRecomendadoFormService: OnsDadosHidrologicosVolumeEsperaRecomendadoFormService;
  let onsDadosHidrologicosVolumeEsperaRecomendadoService: OnsDadosHidrologicosVolumeEsperaRecomendadoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosHidrologicosVolumeEsperaRecomendadoUpdateComponent],
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
      .overrideTemplate(OnsDadosHidrologicosVolumeEsperaRecomendadoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosHidrologicosVolumeEsperaRecomendadoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosHidrologicosVolumeEsperaRecomendadoFormService = TestBed.inject(OnsDadosHidrologicosVolumeEsperaRecomendadoFormService);
    onsDadosHidrologicosVolumeEsperaRecomendadoService = TestBed.inject(OnsDadosHidrologicosVolumeEsperaRecomendadoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosHidrologicosVolumeEsperaRecomendado: IOnsDadosHidrologicosVolumeEsperaRecomendado = { id: 30056 };

      activatedRoute.data = of({ onsDadosHidrologicosVolumeEsperaRecomendado });
      comp.ngOnInit();

      expect(comp.onsDadosHidrologicosVolumeEsperaRecomendado).toEqual(onsDadosHidrologicosVolumeEsperaRecomendado);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosHidrologicosVolumeEsperaRecomendado>>();
      const onsDadosHidrologicosVolumeEsperaRecomendado = { id: 16 };
      jest
        .spyOn(onsDadosHidrologicosVolumeEsperaRecomendadoFormService, 'getOnsDadosHidrologicosVolumeEsperaRecomendado')
        .mockReturnValue(onsDadosHidrologicosVolumeEsperaRecomendado);
      jest.spyOn(onsDadosHidrologicosVolumeEsperaRecomendadoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosHidrologicosVolumeEsperaRecomendado });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosHidrologicosVolumeEsperaRecomendado }));
      saveSubject.complete();

      // THEN
      expect(onsDadosHidrologicosVolumeEsperaRecomendadoFormService.getOnsDadosHidrologicosVolumeEsperaRecomendado).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosHidrologicosVolumeEsperaRecomendadoService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosHidrologicosVolumeEsperaRecomendado),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosHidrologicosVolumeEsperaRecomendado>>();
      const onsDadosHidrologicosVolumeEsperaRecomendado = { id: 16 };
      jest
        .spyOn(onsDadosHidrologicosVolumeEsperaRecomendadoFormService, 'getOnsDadosHidrologicosVolumeEsperaRecomendado')
        .mockReturnValue({ id: null });
      jest.spyOn(onsDadosHidrologicosVolumeEsperaRecomendadoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosHidrologicosVolumeEsperaRecomendado: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosHidrologicosVolumeEsperaRecomendado }));
      saveSubject.complete();

      // THEN
      expect(onsDadosHidrologicosVolumeEsperaRecomendadoFormService.getOnsDadosHidrologicosVolumeEsperaRecomendado).toHaveBeenCalled();
      expect(onsDadosHidrologicosVolumeEsperaRecomendadoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosHidrologicosVolumeEsperaRecomendado>>();
      const onsDadosHidrologicosVolumeEsperaRecomendado = { id: 16 };
      jest.spyOn(onsDadosHidrologicosVolumeEsperaRecomendadoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosHidrologicosVolumeEsperaRecomendado });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosHidrologicosVolumeEsperaRecomendadoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
