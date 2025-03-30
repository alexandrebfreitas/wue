import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsCargaEnergiaVerificadaService } from '../service/ons-carga-energia-verificada.service';
import { IOnsCargaEnergiaVerificada } from '../ons-carga-energia-verificada.model';
import { OnsCargaEnergiaVerificadaFormService } from './ons-carga-energia-verificada-form.service';

import { OnsCargaEnergiaVerificadaUpdateComponent } from './ons-carga-energia-verificada-update.component';

describe('OnsCargaEnergiaVerificada Management Update Component', () => {
  let comp: OnsCargaEnergiaVerificadaUpdateComponent;
  let fixture: ComponentFixture<OnsCargaEnergiaVerificadaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsCargaEnergiaVerificadaFormService: OnsCargaEnergiaVerificadaFormService;
  let onsCargaEnergiaVerificadaService: OnsCargaEnergiaVerificadaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCargaEnergiaVerificadaUpdateComponent],
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
      .overrideTemplate(OnsCargaEnergiaVerificadaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsCargaEnergiaVerificadaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsCargaEnergiaVerificadaFormService = TestBed.inject(OnsCargaEnergiaVerificadaFormService);
    onsCargaEnergiaVerificadaService = TestBed.inject(OnsCargaEnergiaVerificadaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsCargaEnergiaVerificada: IOnsCargaEnergiaVerificada = { id: 31492 };

      activatedRoute.data = of({ onsCargaEnergiaVerificada });
      comp.ngOnInit();

      expect(comp.onsCargaEnergiaVerificada).toEqual(onsCargaEnergiaVerificada);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCargaEnergiaVerificada>>();
      const onsCargaEnergiaVerificada = { id: 29329 };
      jest.spyOn(onsCargaEnergiaVerificadaFormService, 'getOnsCargaEnergiaVerificada').mockReturnValue(onsCargaEnergiaVerificada);
      jest.spyOn(onsCargaEnergiaVerificadaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCargaEnergiaVerificada });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCargaEnergiaVerificada }));
      saveSubject.complete();

      // THEN
      expect(onsCargaEnergiaVerificadaFormService.getOnsCargaEnergiaVerificada).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsCargaEnergiaVerificadaService.update).toHaveBeenCalledWith(expect.objectContaining(onsCargaEnergiaVerificada));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCargaEnergiaVerificada>>();
      const onsCargaEnergiaVerificada = { id: 29329 };
      jest.spyOn(onsCargaEnergiaVerificadaFormService, 'getOnsCargaEnergiaVerificada').mockReturnValue({ id: null });
      jest.spyOn(onsCargaEnergiaVerificadaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCargaEnergiaVerificada: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCargaEnergiaVerificada }));
      saveSubject.complete();

      // THEN
      expect(onsCargaEnergiaVerificadaFormService.getOnsCargaEnergiaVerificada).toHaveBeenCalled();
      expect(onsCargaEnergiaVerificadaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCargaEnergiaVerificada>>();
      const onsCargaEnergiaVerificada = { id: 29329 };
      jest.spyOn(onsCargaEnergiaVerificadaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCargaEnergiaVerificada });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsCargaEnergiaVerificadaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
