import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.service';
import { IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.model';
import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService } from './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-form.service';

import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesUpdateComponent } from './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes-update.component';

describe('OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes Management Update Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesUpdateComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService;
  let onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesUpdateComponent],
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
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService = TestBed.inject(
      OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService,
    );
    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService = TestBed.inject(
      OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService,
    );

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
        { id: 30044 };

      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes });
      comp.ngOnInit();

      expect(comp.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes).toEqual(
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
      );
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>>();
      const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = { id: 511 };
      jest
        .spyOn(
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService,
          'getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes',
        )
        .mockReturnValue(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes);
      jest.spyOn(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes }));
      saveSubject.complete();

      // THEN
      expect(
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
      ).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>>();
      const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = { id: 511 };
      jest
        .spyOn(
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService,
          'getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes',
        )
        .mockReturnValue({ id: null });
      jest.spyOn(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes }));
      saveSubject.complete();

      // THEN
      expect(
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesFormService.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
      ).toHaveBeenCalled();
      expect(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>>();
      const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = { id: 511 };
      jest.spyOn(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
