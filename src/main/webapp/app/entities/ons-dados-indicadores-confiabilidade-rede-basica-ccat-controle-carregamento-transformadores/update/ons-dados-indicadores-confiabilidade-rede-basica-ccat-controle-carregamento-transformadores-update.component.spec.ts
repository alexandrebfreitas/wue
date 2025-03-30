import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService } from '../service/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.service';
import { IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores } from '../ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.model';
import { OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService } from './ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-form.service';

import { OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresUpdateComponent } from './ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores-update.component';

describe('OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores Management Update Component', () => {
  let comp: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresUpdateComponent;
  let fixture: ComponentFixture<OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService;
  let onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresUpdateComponent],
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
      .overrideTemplate(OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService = TestBed.inject(
      OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService,
    );
    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService = TestBed.inject(
      OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
        { id: 1611 };

      activatedRoute.data = of({ onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores });
      comp.ngOnInit();

      expect(comp.onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores).toEqual(
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
      );
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>>();
      const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = { id: 28122 };
      jest
        .spyOn(
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService,
          'getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores',
        )
        .mockReturnValue(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores);
      jest
        .spyOn(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService, 'update')
        .mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>>();
      const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = { id: 28122 };
      jest
        .spyOn(
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService,
          'getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores',
        )
        .mockReturnValue({ id: null });
      jest
        .spyOn(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService, 'create')
        .mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores }));
      saveSubject.complete();

      // THEN
      expect(
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
      ).toHaveBeenCalled();
      expect(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>>();
      const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = { id: 28122 };
      jest
        .spyOn(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService, 'update')
        .mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
