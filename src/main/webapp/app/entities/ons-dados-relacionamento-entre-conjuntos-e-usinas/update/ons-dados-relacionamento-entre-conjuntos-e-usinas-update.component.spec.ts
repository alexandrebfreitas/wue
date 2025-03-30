import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosRelacionamentoEntreConjuntosEUsinasService } from '../service/ons-dados-relacionamento-entre-conjuntos-e-usinas.service';
import { IOnsDadosRelacionamentoEntreConjuntosEUsinas } from '../ons-dados-relacionamento-entre-conjuntos-e-usinas.model';
import { OnsDadosRelacionamentoEntreConjuntosEUsinasFormService } from './ons-dados-relacionamento-entre-conjuntos-e-usinas-form.service';

import { OnsDadosRelacionamentoEntreConjuntosEUsinasUpdateComponent } from './ons-dados-relacionamento-entre-conjuntos-e-usinas-update.component';

describe('OnsDadosRelacionamentoEntreConjuntosEUsinas Management Update Component', () => {
  let comp: OnsDadosRelacionamentoEntreConjuntosEUsinasUpdateComponent;
  let fixture: ComponentFixture<OnsDadosRelacionamentoEntreConjuntosEUsinasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosRelacionamentoEntreConjuntosEUsinasFormService: OnsDadosRelacionamentoEntreConjuntosEUsinasFormService;
  let onsDadosRelacionamentoEntreConjuntosEUsinasService: OnsDadosRelacionamentoEntreConjuntosEUsinasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosRelacionamentoEntreConjuntosEUsinasUpdateComponent],
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
      .overrideTemplate(OnsDadosRelacionamentoEntreConjuntosEUsinasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosRelacionamentoEntreConjuntosEUsinasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosRelacionamentoEntreConjuntosEUsinasFormService = TestBed.inject(OnsDadosRelacionamentoEntreConjuntosEUsinasFormService);
    onsDadosRelacionamentoEntreConjuntosEUsinasService = TestBed.inject(OnsDadosRelacionamentoEntreConjuntosEUsinasService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosRelacionamentoEntreConjuntosEUsinas: IOnsDadosRelacionamentoEntreConjuntosEUsinas = { id: 1430 };

      activatedRoute.data = of({ onsDadosRelacionamentoEntreConjuntosEUsinas });
      comp.ngOnInit();

      expect(comp.onsDadosRelacionamentoEntreConjuntosEUsinas).toEqual(onsDadosRelacionamentoEntreConjuntosEUsinas);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRelacionamentoEntreConjuntosEUsinas>>();
      const onsDadosRelacionamentoEntreConjuntosEUsinas = { id: 30752 };
      jest
        .spyOn(onsDadosRelacionamentoEntreConjuntosEUsinasFormService, 'getOnsDadosRelacionamentoEntreConjuntosEUsinas')
        .mockReturnValue(onsDadosRelacionamentoEntreConjuntosEUsinas);
      jest.spyOn(onsDadosRelacionamentoEntreConjuntosEUsinasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRelacionamentoEntreConjuntosEUsinas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosRelacionamentoEntreConjuntosEUsinas }));
      saveSubject.complete();

      // THEN
      expect(onsDadosRelacionamentoEntreConjuntosEUsinasFormService.getOnsDadosRelacionamentoEntreConjuntosEUsinas).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosRelacionamentoEntreConjuntosEUsinasService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosRelacionamentoEntreConjuntosEUsinas),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRelacionamentoEntreConjuntosEUsinas>>();
      const onsDadosRelacionamentoEntreConjuntosEUsinas = { id: 30752 };
      jest
        .spyOn(onsDadosRelacionamentoEntreConjuntosEUsinasFormService, 'getOnsDadosRelacionamentoEntreConjuntosEUsinas')
        .mockReturnValue({ id: null });
      jest.spyOn(onsDadosRelacionamentoEntreConjuntosEUsinasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRelacionamentoEntreConjuntosEUsinas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosRelacionamentoEntreConjuntosEUsinas }));
      saveSubject.complete();

      // THEN
      expect(onsDadosRelacionamentoEntreConjuntosEUsinasFormService.getOnsDadosRelacionamentoEntreConjuntosEUsinas).toHaveBeenCalled();
      expect(onsDadosRelacionamentoEntreConjuntosEUsinasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRelacionamentoEntreConjuntosEUsinas>>();
      const onsDadosRelacionamentoEntreConjuntosEUsinas = { id: 30752 };
      jest.spyOn(onsDadosRelacionamentoEntreConjuntosEUsinasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRelacionamentoEntreConjuntosEUsinas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosRelacionamentoEntreConjuntosEUsinasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
