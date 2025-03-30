import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsGeracaoTermicaMotivoDespachoService } from '../service/ons-geracao-termica-motivo-despacho.service';
import { IOnsGeracaoTermicaMotivoDespacho } from '../ons-geracao-termica-motivo-despacho.model';
import { OnsGeracaoTermicaMotivoDespachoFormService } from './ons-geracao-termica-motivo-despacho-form.service';

import { OnsGeracaoTermicaMotivoDespachoUpdateComponent } from './ons-geracao-termica-motivo-despacho-update.component';

describe('OnsGeracaoTermicaMotivoDespacho Management Update Component', () => {
  let comp: OnsGeracaoTermicaMotivoDespachoUpdateComponent;
  let fixture: ComponentFixture<OnsGeracaoTermicaMotivoDespachoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsGeracaoTermicaMotivoDespachoFormService: OnsGeracaoTermicaMotivoDespachoFormService;
  let onsGeracaoTermicaMotivoDespachoService: OnsGeracaoTermicaMotivoDespachoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsGeracaoTermicaMotivoDespachoUpdateComponent],
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
      .overrideTemplate(OnsGeracaoTermicaMotivoDespachoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsGeracaoTermicaMotivoDespachoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsGeracaoTermicaMotivoDespachoFormService = TestBed.inject(OnsGeracaoTermicaMotivoDespachoFormService);
    onsGeracaoTermicaMotivoDespachoService = TestBed.inject(OnsGeracaoTermicaMotivoDespachoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsGeracaoTermicaMotivoDespacho: IOnsGeracaoTermicaMotivoDespacho = { id: 29605 };

      activatedRoute.data = of({ onsGeracaoTermicaMotivoDespacho });
      comp.ngOnInit();

      expect(comp.onsGeracaoTermicaMotivoDespacho).toEqual(onsGeracaoTermicaMotivoDespacho);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsGeracaoTermicaMotivoDespacho>>();
      const onsGeracaoTermicaMotivoDespacho = { id: 18788 };
      jest
        .spyOn(onsGeracaoTermicaMotivoDespachoFormService, 'getOnsGeracaoTermicaMotivoDespacho')
        .mockReturnValue(onsGeracaoTermicaMotivoDespacho);
      jest.spyOn(onsGeracaoTermicaMotivoDespachoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsGeracaoTermicaMotivoDespacho });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsGeracaoTermicaMotivoDespacho }));
      saveSubject.complete();

      // THEN
      expect(onsGeracaoTermicaMotivoDespachoFormService.getOnsGeracaoTermicaMotivoDespacho).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsGeracaoTermicaMotivoDespachoService.update).toHaveBeenCalledWith(expect.objectContaining(onsGeracaoTermicaMotivoDespacho));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsGeracaoTermicaMotivoDespacho>>();
      const onsGeracaoTermicaMotivoDespacho = { id: 18788 };
      jest.spyOn(onsGeracaoTermicaMotivoDespachoFormService, 'getOnsGeracaoTermicaMotivoDespacho').mockReturnValue({ id: null });
      jest.spyOn(onsGeracaoTermicaMotivoDespachoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsGeracaoTermicaMotivoDespacho: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsGeracaoTermicaMotivoDespacho }));
      saveSubject.complete();

      // THEN
      expect(onsGeracaoTermicaMotivoDespachoFormService.getOnsGeracaoTermicaMotivoDespacho).toHaveBeenCalled();
      expect(onsGeracaoTermicaMotivoDespachoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsGeracaoTermicaMotivoDespacho>>();
      const onsGeracaoTermicaMotivoDespacho = { id: 18788 };
      jest.spyOn(onsGeracaoTermicaMotivoDespachoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsGeracaoTermicaMotivoDespacho });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsGeracaoTermicaMotivoDespachoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
