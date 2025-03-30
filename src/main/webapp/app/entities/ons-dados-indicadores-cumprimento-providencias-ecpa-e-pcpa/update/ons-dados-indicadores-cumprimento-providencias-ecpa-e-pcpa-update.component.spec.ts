import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService } from '../service/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.service';
import { IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa } from '../ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.model';
import { OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService } from './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-form.service';

import { OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaUpdateComponent } from './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-update.component';

describe('OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa Management Update Component', () => {
  let comp: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaUpdateComponent;
  let fixture: ComponentFixture<OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService;
  let onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaUpdateComponent],
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
      .overrideTemplate(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService = TestBed.inject(
      OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService,
    );
    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService = TestBed.inject(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = { id: 7593 };

      activatedRoute.data = of({ onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa });
      comp.ngOnInit();

      expect(comp.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa).toEqual(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>>();
      const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = { id: 30063 };
      jest
        .spyOn(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService, 'getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa')
        .mockReturnValue(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa);
      jest.spyOn(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService.getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>>();
      const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = { id: 30063 };
      jest
        .spyOn(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService, 'getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa')
        .mockReturnValue({ id: null });
      jest.spyOn(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService.getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
      ).toHaveBeenCalled();
      expect(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>>();
      const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = { id: 30063 };
      jest.spyOn(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
