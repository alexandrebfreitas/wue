jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.service';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDeleteDialogComponent } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual-delete-dialog.component';

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual Management Delete Component', () => {
  let comp: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDeleteDialogComponent;
  let fixture: ComponentFixture<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDeleteDialogComponent>;
  let service: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService);
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
