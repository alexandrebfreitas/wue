import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsCargaEnergiaMensalService } from '../service/ons-carga-energia-mensal.service';
import { IOnsCargaEnergiaMensal } from '../ons-carga-energia-mensal.model';
import { OnsCargaEnergiaMensalFormService } from './ons-carga-energia-mensal-form.service';

import { OnsCargaEnergiaMensalUpdateComponent } from './ons-carga-energia-mensal-update.component';

describe('OnsCargaEnergiaMensal Management Update Component', () => {
  let comp: OnsCargaEnergiaMensalUpdateComponent;
  let fixture: ComponentFixture<OnsCargaEnergiaMensalUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsCargaEnergiaMensalFormService: OnsCargaEnergiaMensalFormService;
  let onsCargaEnergiaMensalService: OnsCargaEnergiaMensalService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCargaEnergiaMensalUpdateComponent],
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
      .overrideTemplate(OnsCargaEnergiaMensalUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsCargaEnergiaMensalUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsCargaEnergiaMensalFormService = TestBed.inject(OnsCargaEnergiaMensalFormService);
    onsCargaEnergiaMensalService = TestBed.inject(OnsCargaEnergiaMensalService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsCargaEnergiaMensal: IOnsCargaEnergiaMensal = { id: 15863 };

      activatedRoute.data = of({ onsCargaEnergiaMensal });
      comp.ngOnInit();

      expect(comp.onsCargaEnergiaMensal).toEqual(onsCargaEnergiaMensal);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCargaEnergiaMensal>>();
      const onsCargaEnergiaMensal = { id: 16554 };
      jest.spyOn(onsCargaEnergiaMensalFormService, 'getOnsCargaEnergiaMensal').mockReturnValue(onsCargaEnergiaMensal);
      jest.spyOn(onsCargaEnergiaMensalService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCargaEnergiaMensal });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCargaEnergiaMensal }));
      saveSubject.complete();

      // THEN
      expect(onsCargaEnergiaMensalFormService.getOnsCargaEnergiaMensal).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsCargaEnergiaMensalService.update).toHaveBeenCalledWith(expect.objectContaining(onsCargaEnergiaMensal));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCargaEnergiaMensal>>();
      const onsCargaEnergiaMensal = { id: 16554 };
      jest.spyOn(onsCargaEnergiaMensalFormService, 'getOnsCargaEnergiaMensal').mockReturnValue({ id: null });
      jest.spyOn(onsCargaEnergiaMensalService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCargaEnergiaMensal: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCargaEnergiaMensal }));
      saveSubject.complete();

      // THEN
      expect(onsCargaEnergiaMensalFormService.getOnsCargaEnergiaMensal).toHaveBeenCalled();
      expect(onsCargaEnergiaMensalService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCargaEnergiaMensal>>();
      const onsCargaEnergiaMensal = { id: 16554 };
      jest.spyOn(onsCargaEnergiaMensalService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCargaEnergiaMensal });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsCargaEnergiaMensalService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
