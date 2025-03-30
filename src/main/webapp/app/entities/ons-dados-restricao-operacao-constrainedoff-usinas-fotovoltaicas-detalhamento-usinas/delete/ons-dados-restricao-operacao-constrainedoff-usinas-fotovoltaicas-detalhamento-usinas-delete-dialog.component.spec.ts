jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas.service';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasDeleteDialogComponent } from './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas-delete-dialog.component';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas Management Delete Component', () => {
  let comp: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasDeleteDialogComponent;
  let fixture: ComponentFixture<OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasDeleteDialogComponent>;
  let service: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasService);
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
