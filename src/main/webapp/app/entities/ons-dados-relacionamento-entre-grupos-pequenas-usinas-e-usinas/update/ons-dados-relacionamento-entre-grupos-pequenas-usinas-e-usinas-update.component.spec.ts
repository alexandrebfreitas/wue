import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService } from '../service/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.service';
import { IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas } from '../ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.model';
import { OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService } from './ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-form.service';

import { OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasUpdateComponent } from './ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-update.component';

describe('OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas Management Update Component', () => {
  let comp: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasUpdateComponent;
  let fixture: ComponentFixture<OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService;
  let onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasUpdateComponent],
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
      .overrideTemplate(OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService = TestBed.inject(
      OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService,
    );
    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService = TestBed.inject(
      OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = { id: 26709 };

      activatedRoute.data = of({ onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas });
      comp.ngOnInit();

      expect(comp.onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas).toEqual(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas>>();
      const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = { id: 3963 };
      jest
        .spyOn(
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService,
          'getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas',
        )
        .mockReturnValue(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas);
      jest.spyOn(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService.getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas>>();
      const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = { id: 3963 };
      jest
        .spyOn(
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService,
          'getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas',
        )
        .mockReturnValue({ id: null });
      jest.spyOn(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService.getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
      ).toHaveBeenCalled();
      expect(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas>>();
      const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = { id: 3963 };
      jest.spyOn(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
