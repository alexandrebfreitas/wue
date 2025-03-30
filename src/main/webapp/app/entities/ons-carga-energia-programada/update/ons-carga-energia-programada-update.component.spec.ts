import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsCargaEnergiaProgramadaService } from '../service/ons-carga-energia-programada.service';
import { IOnsCargaEnergiaProgramada } from '../ons-carga-energia-programada.model';
import { OnsCargaEnergiaProgramadaFormService } from './ons-carga-energia-programada-form.service';

import { OnsCargaEnergiaProgramadaUpdateComponent } from './ons-carga-energia-programada-update.component';

describe('OnsCargaEnergiaProgramada Management Update Component', () => {
  let comp: OnsCargaEnergiaProgramadaUpdateComponent;
  let fixture: ComponentFixture<OnsCargaEnergiaProgramadaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsCargaEnergiaProgramadaFormService: OnsCargaEnergiaProgramadaFormService;
  let onsCargaEnergiaProgramadaService: OnsCargaEnergiaProgramadaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCargaEnergiaProgramadaUpdateComponent],
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
      .overrideTemplate(OnsCargaEnergiaProgramadaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsCargaEnergiaProgramadaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsCargaEnergiaProgramadaFormService = TestBed.inject(OnsCargaEnergiaProgramadaFormService);
    onsCargaEnergiaProgramadaService = TestBed.inject(OnsCargaEnergiaProgramadaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsCargaEnergiaProgramada: IOnsCargaEnergiaProgramada = { id: 2126 };

      activatedRoute.data = of({ onsCargaEnergiaProgramada });
      comp.ngOnInit();

      expect(comp.onsCargaEnergiaProgramada).toEqual(onsCargaEnergiaProgramada);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCargaEnergiaProgramada>>();
      const onsCargaEnergiaProgramada = { id: 16894 };
      jest.spyOn(onsCargaEnergiaProgramadaFormService, 'getOnsCargaEnergiaProgramada').mockReturnValue(onsCargaEnergiaProgramada);
      jest.spyOn(onsCargaEnergiaProgramadaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCargaEnergiaProgramada });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCargaEnergiaProgramada }));
      saveSubject.complete();

      // THEN
      expect(onsCargaEnergiaProgramadaFormService.getOnsCargaEnergiaProgramada).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsCargaEnergiaProgramadaService.update).toHaveBeenCalledWith(expect.objectContaining(onsCargaEnergiaProgramada));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCargaEnergiaProgramada>>();
      const onsCargaEnergiaProgramada = { id: 16894 };
      jest.spyOn(onsCargaEnergiaProgramadaFormService, 'getOnsCargaEnergiaProgramada').mockReturnValue({ id: null });
      jest.spyOn(onsCargaEnergiaProgramadaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCargaEnergiaProgramada: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCargaEnergiaProgramada }));
      saveSubject.complete();

      // THEN
      expect(onsCargaEnergiaProgramadaFormService.getOnsCargaEnergiaProgramada).toHaveBeenCalled();
      expect(onsCargaEnergiaProgramadaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCargaEnergiaProgramada>>();
      const onsCargaEnergiaProgramada = { id: 16894 };
      jest.spyOn(onsCargaEnergiaProgramadaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCargaEnergiaProgramada });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsCargaEnergiaProgramadaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
