jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.service';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDeleteDialogComponent } from './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-delete-dialog.component';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas Management Delete Component', () => {
  let comp: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDeleteDialogComponent;
  let fixture: ComponentFixture<OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDeleteDialogComponent>;
  let service: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService);
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
