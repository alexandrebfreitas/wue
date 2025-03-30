jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.service';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDeleteDialogComponent } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal-delete-dialog.component';

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal Management Delete Component', () => {
  let comp: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDeleteDialogComponent;
  let fixture: ComponentFixture<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDeleteDialogComponent>;
  let service: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      }),
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
