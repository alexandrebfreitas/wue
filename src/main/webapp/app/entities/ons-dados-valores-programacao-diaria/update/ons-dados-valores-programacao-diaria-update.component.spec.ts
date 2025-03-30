import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosValoresProgramacaoDiariaService } from '../service/ons-dados-valores-programacao-diaria.service';
import { IOnsDadosValoresProgramacaoDiaria } from '../ons-dados-valores-programacao-diaria.model';
import { OnsDadosValoresProgramacaoDiariaFormService } from './ons-dados-valores-programacao-diaria-form.service';

import { OnsDadosValoresProgramacaoDiariaUpdateComponent } from './ons-dados-valores-programacao-diaria-update.component';

describe('OnsDadosValoresProgramacaoDiaria Management Update Component', () => {
  let comp: OnsDadosValoresProgramacaoDiariaUpdateComponent;
  let fixture: ComponentFixture<OnsDadosValoresProgramacaoDiariaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosValoresProgramacaoDiariaFormService: OnsDadosValoresProgramacaoDiariaFormService;
  let onsDadosValoresProgramacaoDiariaService: OnsDadosValoresProgramacaoDiariaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosValoresProgramacaoDiariaUpdateComponent],
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
      .overrideTemplate(OnsDadosValoresProgramacaoDiariaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosValoresProgramacaoDiariaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosValoresProgramacaoDiariaFormService = TestBed.inject(OnsDadosValoresProgramacaoDiariaFormService);
    onsDadosValoresProgramacaoDiariaService = TestBed.inject(OnsDadosValoresProgramacaoDiariaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosValoresProgramacaoDiaria: IOnsDadosValoresProgramacaoDiaria = { id: 2639 };

      activatedRoute.data = of({ onsDadosValoresProgramacaoDiaria });
      comp.ngOnInit();

      expect(comp.onsDadosValoresProgramacaoDiaria).toEqual(onsDadosValoresProgramacaoDiaria);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosValoresProgramacaoDiaria>>();
      const onsDadosValoresProgramacaoDiaria = { id: 9203 };
      jest
        .spyOn(onsDadosValoresProgramacaoDiariaFormService, 'getOnsDadosValoresProgramacaoDiaria')
        .mockReturnValue(onsDadosValoresProgramacaoDiaria);
      jest.spyOn(onsDadosValoresProgramacaoDiariaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosValoresProgramacaoDiaria });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosValoresProgramacaoDiaria }));
      saveSubject.complete();

      // THEN
      expect(onsDadosValoresProgramacaoDiariaFormService.getOnsDadosValoresProgramacaoDiaria).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosValoresProgramacaoDiariaService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosValoresProgramacaoDiaria),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosValoresProgramacaoDiaria>>();
      const onsDadosValoresProgramacaoDiaria = { id: 9203 };
      jest.spyOn(onsDadosValoresProgramacaoDiariaFormService, 'getOnsDadosValoresProgramacaoDiaria').mockReturnValue({ id: null });
      jest.spyOn(onsDadosValoresProgramacaoDiariaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosValoresProgramacaoDiaria: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosValoresProgramacaoDiaria }));
      saveSubject.complete();

      // THEN
      expect(onsDadosValoresProgramacaoDiariaFormService.getOnsDadosValoresProgramacaoDiaria).toHaveBeenCalled();
      expect(onsDadosValoresProgramacaoDiariaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosValoresProgramacaoDiaria>>();
      const onsDadosValoresProgramacaoDiaria = { id: 9203 };
      jest.spyOn(onsDadosValoresProgramacaoDiariaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosValoresProgramacaoDiaria });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosValoresProgramacaoDiariaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
