import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsCargaEnergiaDiariaService } from '../service/ons-carga-energia-diaria.service';
import { IOnsCargaEnergiaDiaria } from '../ons-carga-energia-diaria.model';
import { OnsCargaEnergiaDiariaFormService } from './ons-carga-energia-diaria-form.service';

import { OnsCargaEnergiaDiariaUpdateComponent } from './ons-carga-energia-diaria-update.component';

describe('OnsCargaEnergiaDiaria Management Update Component', () => {
  let comp: OnsCargaEnergiaDiariaUpdateComponent;
  let fixture: ComponentFixture<OnsCargaEnergiaDiariaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsCargaEnergiaDiariaFormService: OnsCargaEnergiaDiariaFormService;
  let onsCargaEnergiaDiariaService: OnsCargaEnergiaDiariaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCargaEnergiaDiariaUpdateComponent],
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
      .overrideTemplate(OnsCargaEnergiaDiariaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsCargaEnergiaDiariaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsCargaEnergiaDiariaFormService = TestBed.inject(OnsCargaEnergiaDiariaFormService);
    onsCargaEnergiaDiariaService = TestBed.inject(OnsCargaEnergiaDiariaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsCargaEnergiaDiaria: IOnsCargaEnergiaDiaria = { id: 24610 };

      activatedRoute.data = of({ onsCargaEnergiaDiaria });
      comp.ngOnInit();

      expect(comp.onsCargaEnergiaDiaria).toEqual(onsCargaEnergiaDiaria);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCargaEnergiaDiaria>>();
      const onsCargaEnergiaDiaria = { id: 10395 };
      jest.spyOn(onsCargaEnergiaDiariaFormService, 'getOnsCargaEnergiaDiaria').mockReturnValue(onsCargaEnergiaDiaria);
      jest.spyOn(onsCargaEnergiaDiariaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCargaEnergiaDiaria });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCargaEnergiaDiaria }));
      saveSubject.complete();

      // THEN
      expect(onsCargaEnergiaDiariaFormService.getOnsCargaEnergiaDiaria).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsCargaEnergiaDiariaService.update).toHaveBeenCalledWith(expect.objectContaining(onsCargaEnergiaDiaria));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCargaEnergiaDiaria>>();
      const onsCargaEnergiaDiaria = { id: 10395 };
      jest.spyOn(onsCargaEnergiaDiariaFormService, 'getOnsCargaEnergiaDiaria').mockReturnValue({ id: null });
      jest.spyOn(onsCargaEnergiaDiariaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCargaEnergiaDiaria: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCargaEnergiaDiaria }));
      saveSubject.complete();

      // THEN
      expect(onsCargaEnergiaDiariaFormService.getOnsCargaEnergiaDiaria).toHaveBeenCalled();
      expect(onsCargaEnergiaDiariaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCargaEnergiaDiaria>>();
      const onsCargaEnergiaDiaria = { id: 10395 };
      jest.spyOn(onsCargaEnergiaDiariaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCargaEnergiaDiaria });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsCargaEnergiaDiariaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
