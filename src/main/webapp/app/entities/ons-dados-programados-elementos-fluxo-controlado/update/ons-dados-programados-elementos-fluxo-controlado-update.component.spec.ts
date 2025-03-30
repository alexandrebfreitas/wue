import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosProgramadosElementosFluxoControladoService } from '../service/ons-dados-programados-elementos-fluxo-controlado.service';
import { IOnsDadosProgramadosElementosFluxoControlado } from '../ons-dados-programados-elementos-fluxo-controlado.model';
import { OnsDadosProgramadosElementosFluxoControladoFormService } from './ons-dados-programados-elementos-fluxo-controlado-form.service';

import { OnsDadosProgramadosElementosFluxoControladoUpdateComponent } from './ons-dados-programados-elementos-fluxo-controlado-update.component';

describe('OnsDadosProgramadosElementosFluxoControlado Management Update Component', () => {
  let comp: OnsDadosProgramadosElementosFluxoControladoUpdateComponent;
  let fixture: ComponentFixture<OnsDadosProgramadosElementosFluxoControladoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosProgramadosElementosFluxoControladoFormService: OnsDadosProgramadosElementosFluxoControladoFormService;
  let onsDadosProgramadosElementosFluxoControladoService: OnsDadosProgramadosElementosFluxoControladoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosProgramadosElementosFluxoControladoUpdateComponent],
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
      .overrideTemplate(OnsDadosProgramadosElementosFluxoControladoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosProgramadosElementosFluxoControladoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosProgramadosElementosFluxoControladoFormService = TestBed.inject(OnsDadosProgramadosElementosFluxoControladoFormService);
    onsDadosProgramadosElementosFluxoControladoService = TestBed.inject(OnsDadosProgramadosElementosFluxoControladoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosProgramadosElementosFluxoControlado: IOnsDadosProgramadosElementosFluxoControlado = { id: 10318 };

      activatedRoute.data = of({ onsDadosProgramadosElementosFluxoControlado });
      comp.ngOnInit();

      expect(comp.onsDadosProgramadosElementosFluxoControlado).toEqual(onsDadosProgramadosElementosFluxoControlado);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosProgramadosElementosFluxoControlado>>();
      const onsDadosProgramadosElementosFluxoControlado = { id: 17757 };
      jest
        .spyOn(onsDadosProgramadosElementosFluxoControladoFormService, 'getOnsDadosProgramadosElementosFluxoControlado')
        .mockReturnValue(onsDadosProgramadosElementosFluxoControlado);
      jest.spyOn(onsDadosProgramadosElementosFluxoControladoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosProgramadosElementosFluxoControlado });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosProgramadosElementosFluxoControlado }));
      saveSubject.complete();

      // THEN
      expect(onsDadosProgramadosElementosFluxoControladoFormService.getOnsDadosProgramadosElementosFluxoControlado).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosProgramadosElementosFluxoControladoService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosProgramadosElementosFluxoControlado),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosProgramadosElementosFluxoControlado>>();
      const onsDadosProgramadosElementosFluxoControlado = { id: 17757 };
      jest
        .spyOn(onsDadosProgramadosElementosFluxoControladoFormService, 'getOnsDadosProgramadosElementosFluxoControlado')
        .mockReturnValue({ id: null });
      jest.spyOn(onsDadosProgramadosElementosFluxoControladoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosProgramadosElementosFluxoControlado: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosProgramadosElementosFluxoControlado }));
      saveSubject.complete();

      // THEN
      expect(onsDadosProgramadosElementosFluxoControladoFormService.getOnsDadosProgramadosElementosFluxoControlado).toHaveBeenCalled();
      expect(onsDadosProgramadosElementosFluxoControladoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosProgramadosElementosFluxoControlado>>();
      const onsDadosProgramadosElementosFluxoControlado = { id: 17757 };
      jest.spyOn(onsDadosProgramadosElementosFluxoControladoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosProgramadosElementosFluxoControlado });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosProgramadosElementosFluxoControladoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
