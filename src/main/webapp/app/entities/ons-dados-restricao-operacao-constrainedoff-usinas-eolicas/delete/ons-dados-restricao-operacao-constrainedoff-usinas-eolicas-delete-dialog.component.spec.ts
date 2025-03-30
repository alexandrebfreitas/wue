jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService } from '../service/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.service';

import { OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDeleteDialogComponent } from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-delete-dialog.component';

describe('OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas Management Delete Component', () => {
  let comp: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDeleteDialogComponent;
  let fixture: ComponentFixture<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDeleteDialogComponent>;
  let service: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService);
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
