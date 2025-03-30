import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.service';
import { IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService } from './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-form.service';

import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesUpdateComponent } from './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-update.component';

describe('OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes Management Update Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesUpdateComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService;
  let onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesUpdateComponent],
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
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService = TestBed.inject(
      OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService,
    );
    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService = TestBed.inject(
      OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
        { id: 10204 };

      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes });
      comp.ngOnInit();

      expect(comp.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes).toEqual(
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
      );
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>>();
      const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = { id: 25854 };
      jest
        .spyOn(
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService,
          'getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes',
        )
        .mockReturnValue(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes);
      jest.spyOn(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes }));
      saveSubject.complete();

      // THEN
      expect(
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>>();
      const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = { id: 25854 };
      jest
        .spyOn(
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService,
          'getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes',
        )
        .mockReturnValue({ id: null });
      jest.spyOn(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes }));
      saveSubject.complete();

      // THEN
      expect(
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesFormService.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
      ).toHaveBeenCalled();
      expect(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>>();
      const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = { id: 25854 };
      jest.spyOn(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
