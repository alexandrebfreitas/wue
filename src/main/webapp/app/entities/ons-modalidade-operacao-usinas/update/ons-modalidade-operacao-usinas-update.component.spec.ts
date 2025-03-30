import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsModalidadeOperacaoUsinasService } from '../service/ons-modalidade-operacao-usinas.service';
import { IOnsModalidadeOperacaoUsinas } from '../ons-modalidade-operacao-usinas.model';
import { OnsModalidadeOperacaoUsinasFormService } from './ons-modalidade-operacao-usinas-form.service';

import { OnsModalidadeOperacaoUsinasUpdateComponent } from './ons-modalidade-operacao-usinas-update.component';

describe('OnsModalidadeOperacaoUsinas Management Update Component', () => {
  let comp: OnsModalidadeOperacaoUsinasUpdateComponent;
  let fixture: ComponentFixture<OnsModalidadeOperacaoUsinasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsModalidadeOperacaoUsinasFormService: OnsModalidadeOperacaoUsinasFormService;
  let onsModalidadeOperacaoUsinasService: OnsModalidadeOperacaoUsinasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsModalidadeOperacaoUsinasUpdateComponent],
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
      .overrideTemplate(OnsModalidadeOperacaoUsinasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsModalidadeOperacaoUsinasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsModalidadeOperacaoUsinasFormService = TestBed.inject(OnsModalidadeOperacaoUsinasFormService);
    onsModalidadeOperacaoUsinasService = TestBed.inject(OnsModalidadeOperacaoUsinasService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsModalidadeOperacaoUsinas: IOnsModalidadeOperacaoUsinas = { id: 30323 };

      activatedRoute.data = of({ onsModalidadeOperacaoUsinas });
      comp.ngOnInit();

      expect(comp.onsModalidadeOperacaoUsinas).toEqual(onsModalidadeOperacaoUsinas);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsModalidadeOperacaoUsinas>>();
      const onsModalidadeOperacaoUsinas = { id: 6450 };
      jest.spyOn(onsModalidadeOperacaoUsinasFormService, 'getOnsModalidadeOperacaoUsinas').mockReturnValue(onsModalidadeOperacaoUsinas);
      jest.spyOn(onsModalidadeOperacaoUsinasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsModalidadeOperacaoUsinas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsModalidadeOperacaoUsinas }));
      saveSubject.complete();

      // THEN
      expect(onsModalidadeOperacaoUsinasFormService.getOnsModalidadeOperacaoUsinas).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsModalidadeOperacaoUsinasService.update).toHaveBeenCalledWith(expect.objectContaining(onsModalidadeOperacaoUsinas));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsModalidadeOperacaoUsinas>>();
      const onsModalidadeOperacaoUsinas = { id: 6450 };
      jest.spyOn(onsModalidadeOperacaoUsinasFormService, 'getOnsModalidadeOperacaoUsinas').mockReturnValue({ id: null });
      jest.spyOn(onsModalidadeOperacaoUsinasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsModalidadeOperacaoUsinas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsModalidadeOperacaoUsinas }));
      saveSubject.complete();

      // THEN
      expect(onsModalidadeOperacaoUsinasFormService.getOnsModalidadeOperacaoUsinas).toHaveBeenCalled();
      expect(onsModalidadeOperacaoUsinasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsModalidadeOperacaoUsinas>>();
      const onsModalidadeOperacaoUsinas = { id: 6450 };
      jest.spyOn(onsModalidadeOperacaoUsinasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsModalidadeOperacaoUsinas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsModalidadeOperacaoUsinasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
