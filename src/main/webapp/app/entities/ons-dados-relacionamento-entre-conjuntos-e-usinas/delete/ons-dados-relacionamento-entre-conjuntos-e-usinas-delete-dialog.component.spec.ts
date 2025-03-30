jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsDadosRelacionamentoEntreConjuntosEUsinasService } from '../service/ons-dados-relacionamento-entre-conjuntos-e-usinas.service';

import { OnsDadosRelacionamentoEntreConjuntosEUsinasDeleteDialogComponent } from './ons-dados-relacionamento-entre-conjuntos-e-usinas-delete-dialog.component';

describe('OnsDadosRelacionamentoEntreConjuntosEUsinas Management Delete Component', () => {
  let comp: OnsDadosRelacionamentoEntreConjuntosEUsinasDeleteDialogComponent;
  let fixture: ComponentFixture<OnsDadosRelacionamentoEntreConjuntosEUsinasDeleteDialogComponent>;
  let service: OnsDadosRelacionamentoEntreConjuntosEUsinasService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosRelacionamentoEntreConjuntosEUsinasDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsDadosRelacionamentoEntreConjuntosEUsinasDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsDadosRelacionamentoEntreConjuntosEUsinasDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsDadosRelacionamentoEntreConjuntosEUsinasService);
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
