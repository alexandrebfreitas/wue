import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsTaxasTeifaETeipService } from '../service/ons-taxas-teifa-e-teip.service';
import { IOnsTaxasTeifaETeip } from '../ons-taxas-teifa-e-teip.model';
import { OnsTaxasTeifaETeipFormService } from './ons-taxas-teifa-e-teip-form.service';

import { OnsTaxasTeifaETeipUpdateComponent } from './ons-taxas-teifa-e-teip-update.component';

describe('OnsTaxasTeifaETeip Management Update Component', () => {
  let comp: OnsTaxasTeifaETeipUpdateComponent;
  let fixture: ComponentFixture<OnsTaxasTeifaETeipUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsTaxasTeifaETeipFormService: OnsTaxasTeifaETeipFormService;
  let onsTaxasTeifaETeipService: OnsTaxasTeifaETeipService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsTaxasTeifaETeipUpdateComponent],
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
      .overrideTemplate(OnsTaxasTeifaETeipUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsTaxasTeifaETeipUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsTaxasTeifaETeipFormService = TestBed.inject(OnsTaxasTeifaETeipFormService);
    onsTaxasTeifaETeipService = TestBed.inject(OnsTaxasTeifaETeipService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsTaxasTeifaETeip: IOnsTaxasTeifaETeip = { id: 16755 };

      activatedRoute.data = of({ onsTaxasTeifaETeip });
      comp.ngOnInit();

      expect(comp.onsTaxasTeifaETeip).toEqual(onsTaxasTeifaETeip);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsTaxasTeifaETeip>>();
      const onsTaxasTeifaETeip = { id: 9188 };
      jest.spyOn(onsTaxasTeifaETeipFormService, 'getOnsTaxasTeifaETeip').mockReturnValue(onsTaxasTeifaETeip);
      jest.spyOn(onsTaxasTeifaETeipService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsTaxasTeifaETeip });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsTaxasTeifaETeip }));
      saveSubject.complete();

      // THEN
      expect(onsTaxasTeifaETeipFormService.getOnsTaxasTeifaETeip).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsTaxasTeifaETeipService.update).toHaveBeenCalledWith(expect.objectContaining(onsTaxasTeifaETeip));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsTaxasTeifaETeip>>();
      const onsTaxasTeifaETeip = { id: 9188 };
      jest.spyOn(onsTaxasTeifaETeipFormService, 'getOnsTaxasTeifaETeip').mockReturnValue({ id: null });
      jest.spyOn(onsTaxasTeifaETeipService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsTaxasTeifaETeip: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsTaxasTeifaETeip }));
      saveSubject.complete();

      // THEN
      expect(onsTaxasTeifaETeipFormService.getOnsTaxasTeifaETeip).toHaveBeenCalled();
      expect(onsTaxasTeifaETeipService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsTaxasTeifaETeip>>();
      const onsTaxasTeifaETeip = { id: 9188 };
      jest.spyOn(onsTaxasTeifaETeipService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsTaxasTeifaETeip });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsTaxasTeifaETeipService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
