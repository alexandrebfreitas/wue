import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosIntercambioEnergiaModalidadeService } from '../service/ons-dados-intercambio-energia-modalidade.service';
import { IOnsDadosIntercambioEnergiaModalidade } from '../ons-dados-intercambio-energia-modalidade.model';
import { OnsDadosIntercambioEnergiaModalidadeFormService } from './ons-dados-intercambio-energia-modalidade-form.service';

import { OnsDadosIntercambioEnergiaModalidadeUpdateComponent } from './ons-dados-intercambio-energia-modalidade-update.component';

describe('OnsDadosIntercambioEnergiaModalidade Management Update Component', () => {
  let comp: OnsDadosIntercambioEnergiaModalidadeUpdateComponent;
  let fixture: ComponentFixture<OnsDadosIntercambioEnergiaModalidadeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosIntercambioEnergiaModalidadeFormService: OnsDadosIntercambioEnergiaModalidadeFormService;
  let onsDadosIntercambioEnergiaModalidadeService: OnsDadosIntercambioEnergiaModalidadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosIntercambioEnergiaModalidadeUpdateComponent],
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
      .overrideTemplate(OnsDadosIntercambioEnergiaModalidadeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosIntercambioEnergiaModalidadeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosIntercambioEnergiaModalidadeFormService = TestBed.inject(OnsDadosIntercambioEnergiaModalidadeFormService);
    onsDadosIntercambioEnergiaModalidadeService = TestBed.inject(OnsDadosIntercambioEnergiaModalidadeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosIntercambioEnergiaModalidade: IOnsDadosIntercambioEnergiaModalidade = { id: 24650 };

      activatedRoute.data = of({ onsDadosIntercambioEnergiaModalidade });
      comp.ngOnInit();

      expect(comp.onsDadosIntercambioEnergiaModalidade).toEqual(onsDadosIntercambioEnergiaModalidade);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosIntercambioEnergiaModalidade>>();
      const onsDadosIntercambioEnergiaModalidade = { id: 15687 };
      jest
        .spyOn(onsDadosIntercambioEnergiaModalidadeFormService, 'getOnsDadosIntercambioEnergiaModalidade')
        .mockReturnValue(onsDadosIntercambioEnergiaModalidade);
      jest.spyOn(onsDadosIntercambioEnergiaModalidadeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosIntercambioEnergiaModalidade });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosIntercambioEnergiaModalidade }));
      saveSubject.complete();

      // THEN
      expect(onsDadosIntercambioEnergiaModalidadeFormService.getOnsDadosIntercambioEnergiaModalidade).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosIntercambioEnergiaModalidadeService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosIntercambioEnergiaModalidade),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosIntercambioEnergiaModalidade>>();
      const onsDadosIntercambioEnergiaModalidade = { id: 15687 };
      jest.spyOn(onsDadosIntercambioEnergiaModalidadeFormService, 'getOnsDadosIntercambioEnergiaModalidade').mockReturnValue({ id: null });
      jest.spyOn(onsDadosIntercambioEnergiaModalidadeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosIntercambioEnergiaModalidade: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosIntercambioEnergiaModalidade }));
      saveSubject.complete();

      // THEN
      expect(onsDadosIntercambioEnergiaModalidadeFormService.getOnsDadosIntercambioEnergiaModalidade).toHaveBeenCalled();
      expect(onsDadosIntercambioEnergiaModalidadeService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosIntercambioEnergiaModalidade>>();
      const onsDadosIntercambioEnergiaModalidade = { id: 15687 };
      jest.spyOn(onsDadosIntercambioEnergiaModalidadeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosIntercambioEnergiaModalidade });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosIntercambioEnergiaModalidadeService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
