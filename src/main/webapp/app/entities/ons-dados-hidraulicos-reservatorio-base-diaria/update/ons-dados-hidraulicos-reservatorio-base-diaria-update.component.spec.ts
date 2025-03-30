import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosHidraulicosReservatorioBaseDiariaService } from '../service/ons-dados-hidraulicos-reservatorio-base-diaria.service';
import { IOnsDadosHidraulicosReservatorioBaseDiaria } from '../ons-dados-hidraulicos-reservatorio-base-diaria.model';
import { OnsDadosHidraulicosReservatorioBaseDiariaFormService } from './ons-dados-hidraulicos-reservatorio-base-diaria-form.service';

import { OnsDadosHidraulicosReservatorioBaseDiariaUpdateComponent } from './ons-dados-hidraulicos-reservatorio-base-diaria-update.component';

describe('OnsDadosHidraulicosReservatorioBaseDiaria Management Update Component', () => {
  let comp: OnsDadosHidraulicosReservatorioBaseDiariaUpdateComponent;
  let fixture: ComponentFixture<OnsDadosHidraulicosReservatorioBaseDiariaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosHidraulicosReservatorioBaseDiariaFormService: OnsDadosHidraulicosReservatorioBaseDiariaFormService;
  let onsDadosHidraulicosReservatorioBaseDiariaService: OnsDadosHidraulicosReservatorioBaseDiariaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosHidraulicosReservatorioBaseDiariaUpdateComponent],
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
      .overrideTemplate(OnsDadosHidraulicosReservatorioBaseDiariaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosHidraulicosReservatorioBaseDiariaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosHidraulicosReservatorioBaseDiariaFormService = TestBed.inject(OnsDadosHidraulicosReservatorioBaseDiariaFormService);
    onsDadosHidraulicosReservatorioBaseDiariaService = TestBed.inject(OnsDadosHidraulicosReservatorioBaseDiariaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosHidraulicosReservatorioBaseDiaria: IOnsDadosHidraulicosReservatorioBaseDiaria = { id: 7299 };

      activatedRoute.data = of({ onsDadosHidraulicosReservatorioBaseDiaria });
      comp.ngOnInit();

      expect(comp.onsDadosHidraulicosReservatorioBaseDiaria).toEqual(onsDadosHidraulicosReservatorioBaseDiaria);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosHidraulicosReservatorioBaseDiaria>>();
      const onsDadosHidraulicosReservatorioBaseDiaria = { id: 29132 };
      jest
        .spyOn(onsDadosHidraulicosReservatorioBaseDiariaFormService, 'getOnsDadosHidraulicosReservatorioBaseDiaria')
        .mockReturnValue(onsDadosHidraulicosReservatorioBaseDiaria);
      jest.spyOn(onsDadosHidraulicosReservatorioBaseDiariaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosHidraulicosReservatorioBaseDiaria });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosHidraulicosReservatorioBaseDiaria }));
      saveSubject.complete();

      // THEN
      expect(onsDadosHidraulicosReservatorioBaseDiariaFormService.getOnsDadosHidraulicosReservatorioBaseDiaria).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosHidraulicosReservatorioBaseDiariaService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosHidraulicosReservatorioBaseDiaria),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosHidraulicosReservatorioBaseDiaria>>();
      const onsDadosHidraulicosReservatorioBaseDiaria = { id: 29132 };
      jest
        .spyOn(onsDadosHidraulicosReservatorioBaseDiariaFormService, 'getOnsDadosHidraulicosReservatorioBaseDiaria')
        .mockReturnValue({ id: null });
      jest.spyOn(onsDadosHidraulicosReservatorioBaseDiariaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosHidraulicosReservatorioBaseDiaria: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosHidraulicosReservatorioBaseDiaria }));
      saveSubject.complete();

      // THEN
      expect(onsDadosHidraulicosReservatorioBaseDiariaFormService.getOnsDadosHidraulicosReservatorioBaseDiaria).toHaveBeenCalled();
      expect(onsDadosHidraulicosReservatorioBaseDiariaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosHidraulicosReservatorioBaseDiaria>>();
      const onsDadosHidraulicosReservatorioBaseDiaria = { id: 29132 };
      jest.spyOn(onsDadosHidraulicosReservatorioBaseDiariaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosHidraulicosReservatorioBaseDiaria });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosHidraulicosReservatorioBaseDiariaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
