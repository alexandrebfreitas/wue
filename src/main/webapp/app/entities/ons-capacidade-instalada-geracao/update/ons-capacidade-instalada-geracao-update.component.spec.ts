import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsCapacidadeInstaladaGeracaoService } from '../service/ons-capacidade-instalada-geracao.service';
import { IOnsCapacidadeInstaladaGeracao } from '../ons-capacidade-instalada-geracao.model';
import { OnsCapacidadeInstaladaGeracaoFormService } from './ons-capacidade-instalada-geracao-form.service';

import { OnsCapacidadeInstaladaGeracaoUpdateComponent } from './ons-capacidade-instalada-geracao-update.component';

describe('OnsCapacidadeInstaladaGeracao Management Update Component', () => {
  let comp: OnsCapacidadeInstaladaGeracaoUpdateComponent;
  let fixture: ComponentFixture<OnsCapacidadeInstaladaGeracaoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsCapacidadeInstaladaGeracaoFormService: OnsCapacidadeInstaladaGeracaoFormService;
  let onsCapacidadeInstaladaGeracaoService: OnsCapacidadeInstaladaGeracaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCapacidadeInstaladaGeracaoUpdateComponent],
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
      .overrideTemplate(OnsCapacidadeInstaladaGeracaoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsCapacidadeInstaladaGeracaoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsCapacidadeInstaladaGeracaoFormService = TestBed.inject(OnsCapacidadeInstaladaGeracaoFormService);
    onsCapacidadeInstaladaGeracaoService = TestBed.inject(OnsCapacidadeInstaladaGeracaoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsCapacidadeInstaladaGeracao: IOnsCapacidadeInstaladaGeracao = { id: 76 };

      activatedRoute.data = of({ onsCapacidadeInstaladaGeracao });
      comp.ngOnInit();

      expect(comp.onsCapacidadeInstaladaGeracao).toEqual(onsCapacidadeInstaladaGeracao);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCapacidadeInstaladaGeracao>>();
      const onsCapacidadeInstaladaGeracao = { id: 5332 };
      jest
        .spyOn(onsCapacidadeInstaladaGeracaoFormService, 'getOnsCapacidadeInstaladaGeracao')
        .mockReturnValue(onsCapacidadeInstaladaGeracao);
      jest.spyOn(onsCapacidadeInstaladaGeracaoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCapacidadeInstaladaGeracao });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCapacidadeInstaladaGeracao }));
      saveSubject.complete();

      // THEN
      expect(onsCapacidadeInstaladaGeracaoFormService.getOnsCapacidadeInstaladaGeracao).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsCapacidadeInstaladaGeracaoService.update).toHaveBeenCalledWith(expect.objectContaining(onsCapacidadeInstaladaGeracao));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCapacidadeInstaladaGeracao>>();
      const onsCapacidadeInstaladaGeracao = { id: 5332 };
      jest.spyOn(onsCapacidadeInstaladaGeracaoFormService, 'getOnsCapacidadeInstaladaGeracao').mockReturnValue({ id: null });
      jest.spyOn(onsCapacidadeInstaladaGeracaoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCapacidadeInstaladaGeracao: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsCapacidadeInstaladaGeracao }));
      saveSubject.complete();

      // THEN
      expect(onsCapacidadeInstaladaGeracaoFormService.getOnsCapacidadeInstaladaGeracao).toHaveBeenCalled();
      expect(onsCapacidadeInstaladaGeracaoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsCapacidadeInstaladaGeracao>>();
      const onsCapacidadeInstaladaGeracao = { id: 5332 };
      jest.spyOn(onsCapacidadeInstaladaGeracaoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsCapacidadeInstaladaGeracao });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsCapacidadeInstaladaGeracaoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
