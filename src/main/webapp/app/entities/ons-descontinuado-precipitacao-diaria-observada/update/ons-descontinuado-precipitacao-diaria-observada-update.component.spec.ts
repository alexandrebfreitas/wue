import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDescontinuadoPrecipitacaoDiariaObservadaService } from '../service/ons-descontinuado-precipitacao-diaria-observada.service';
import { IOnsDescontinuadoPrecipitacaoDiariaObservada } from '../ons-descontinuado-precipitacao-diaria-observada.model';
import { OnsDescontinuadoPrecipitacaoDiariaObservadaFormService } from './ons-descontinuado-precipitacao-diaria-observada-form.service';

import { OnsDescontinuadoPrecipitacaoDiariaObservadaUpdateComponent } from './ons-descontinuado-precipitacao-diaria-observada-update.component';

describe('OnsDescontinuadoPrecipitacaoDiariaObservada Management Update Component', () => {
  let comp: OnsDescontinuadoPrecipitacaoDiariaObservadaUpdateComponent;
  let fixture: ComponentFixture<OnsDescontinuadoPrecipitacaoDiariaObservadaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDescontinuadoPrecipitacaoDiariaObservadaFormService: OnsDescontinuadoPrecipitacaoDiariaObservadaFormService;
  let onsDescontinuadoPrecipitacaoDiariaObservadaService: OnsDescontinuadoPrecipitacaoDiariaObservadaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDescontinuadoPrecipitacaoDiariaObservadaUpdateComponent],
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
      .overrideTemplate(OnsDescontinuadoPrecipitacaoDiariaObservadaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDescontinuadoPrecipitacaoDiariaObservadaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDescontinuadoPrecipitacaoDiariaObservadaFormService = TestBed.inject(OnsDescontinuadoPrecipitacaoDiariaObservadaFormService);
    onsDescontinuadoPrecipitacaoDiariaObservadaService = TestBed.inject(OnsDescontinuadoPrecipitacaoDiariaObservadaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDescontinuadoPrecipitacaoDiariaObservada: IOnsDescontinuadoPrecipitacaoDiariaObservada = { id: 8014 };

      activatedRoute.data = of({ onsDescontinuadoPrecipitacaoDiariaObservada });
      comp.ngOnInit();

      expect(comp.onsDescontinuadoPrecipitacaoDiariaObservada).toEqual(onsDescontinuadoPrecipitacaoDiariaObservada);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDescontinuadoPrecipitacaoDiariaObservada>>();
      const onsDescontinuadoPrecipitacaoDiariaObservada = { id: 5142 };
      jest
        .spyOn(onsDescontinuadoPrecipitacaoDiariaObservadaFormService, 'getOnsDescontinuadoPrecipitacaoDiariaObservada')
        .mockReturnValue(onsDescontinuadoPrecipitacaoDiariaObservada);
      jest.spyOn(onsDescontinuadoPrecipitacaoDiariaObservadaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDescontinuadoPrecipitacaoDiariaObservada });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDescontinuadoPrecipitacaoDiariaObservada }));
      saveSubject.complete();

      // THEN
      expect(onsDescontinuadoPrecipitacaoDiariaObservadaFormService.getOnsDescontinuadoPrecipitacaoDiariaObservada).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDescontinuadoPrecipitacaoDiariaObservadaService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDescontinuadoPrecipitacaoDiariaObservada),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDescontinuadoPrecipitacaoDiariaObservada>>();
      const onsDescontinuadoPrecipitacaoDiariaObservada = { id: 5142 };
      jest
        .spyOn(onsDescontinuadoPrecipitacaoDiariaObservadaFormService, 'getOnsDescontinuadoPrecipitacaoDiariaObservada')
        .mockReturnValue({ id: null });
      jest.spyOn(onsDescontinuadoPrecipitacaoDiariaObservadaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDescontinuadoPrecipitacaoDiariaObservada: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDescontinuadoPrecipitacaoDiariaObservada }));
      saveSubject.complete();

      // THEN
      expect(onsDescontinuadoPrecipitacaoDiariaObservadaFormService.getOnsDescontinuadoPrecipitacaoDiariaObservada).toHaveBeenCalled();
      expect(onsDescontinuadoPrecipitacaoDiariaObservadaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDescontinuadoPrecipitacaoDiariaObservada>>();
      const onsDescontinuadoPrecipitacaoDiariaObservada = { id: 5142 };
      jest.spyOn(onsDescontinuadoPrecipitacaoDiariaObservadaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDescontinuadoPrecipitacaoDiariaObservada });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDescontinuadoPrecipitacaoDiariaObservadaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
