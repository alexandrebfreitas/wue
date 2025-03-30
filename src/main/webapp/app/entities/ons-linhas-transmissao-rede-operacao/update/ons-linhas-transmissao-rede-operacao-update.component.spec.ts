import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { OnsLinhasTransmissaoRedeOperacaoService } from '../service/ons-linhas-transmissao-rede-operacao.service';
import { IOnsLinhasTransmissaoRedeOperacao } from '../ons-linhas-transmissao-rede-operacao.model';
import { OnsLinhasTransmissaoRedeOperacaoFormService } from './ons-linhas-transmissao-rede-operacao-form.service';

import { OnsLinhasTransmissaoRedeOperacaoUpdateComponent } from './ons-linhas-transmissao-rede-operacao-update.component';

describe('OnsLinhasTransmissaoRedeOperacao Management Update Component', () => {
  let comp: OnsLinhasTransmissaoRedeOperacaoUpdateComponent;
  let fixture: ComponentFixture<OnsLinhasTransmissaoRedeOperacaoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let onsLinhasTransmissaoRedeOperacaoFormService: OnsLinhasTransmissaoRedeOperacaoFormService;
  let onsLinhasTransmissaoRedeOperacaoService: OnsLinhasTransmissaoRedeOperacaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsLinhasTransmissaoRedeOperacaoUpdateComponent],
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
      .overrideTemplate(OnsLinhasTransmissaoRedeOperacaoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsLinhasTransmissaoRedeOperacaoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    onsLinhasTransmissaoRedeOperacaoFormService = TestBed.inject(OnsLinhasTransmissaoRedeOperacaoFormService);
    onsLinhasTransmissaoRedeOperacaoService = TestBed.inject(OnsLinhasTransmissaoRedeOperacaoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const onsLinhasTransmissaoRedeOperacao: IOnsLinhasTransmissaoRedeOperacao = { id: 31406 };

      activatedRoute.data = of({ onsLinhasTransmissaoRedeOperacao });
      comp.ngOnInit();

      expect(comp.onsLinhasTransmissaoRedeOperacao).toEqual(onsLinhasTransmissaoRedeOperacao);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsLinhasTransmissaoRedeOperacao>>();
      const onsLinhasTransmissaoRedeOperacao = { id: 30390 };
      jest
        .spyOn(onsLinhasTransmissaoRedeOperacaoFormService, 'getOnsLinhasTransmissaoRedeOperacao')
        .mockReturnValue(onsLinhasTransmissaoRedeOperacao);
      jest.spyOn(onsLinhasTransmissaoRedeOperacaoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsLinhasTransmissaoRedeOperacao });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsLinhasTransmissaoRedeOperacao }));
      saveSubject.complete();

      // THEN
      expect(onsLinhasTransmissaoRedeOperacaoFormService.getOnsLinhasTransmissaoRedeOperacao).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(onsLinhasTransmissaoRedeOperacaoService.update).toHaveBeenCalledWith(
        expect.objectContaining(onsLinhasTransmissaoRedeOperacao),
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsLinhasTransmissaoRedeOperacao>>();
      const onsLinhasTransmissaoRedeOperacao = { id: 30390 };
      jest.spyOn(onsLinhasTransmissaoRedeOperacaoFormService, 'getOnsLinhasTransmissaoRedeOperacao').mockReturnValue({ id: null });
      jest.spyOn(onsLinhasTransmissaoRedeOperacaoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsLinhasTransmissaoRedeOperacao: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: onsLinhasTransmissaoRedeOperacao }));
      saveSubject.complete();

      // THEN
      expect(onsLinhasTransmissaoRedeOperacaoFormService.getOnsLinhasTransmissaoRedeOperacao).toHaveBeenCalled();
      expect(onsLinhasTransmissaoRedeOperacaoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOnsLinhasTransmissaoRedeOperacao>>();
      const onsLinhasTransmissaoRedeOperacao = { id: 30390 };
      jest.spyOn(onsLinhasTransmissaoRedeOperacaoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ onsLinhasTransmissaoRedeOperacao });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(onsLinhasTransmissaoRedeOperacaoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
