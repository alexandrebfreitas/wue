jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsEarDiarioReservatorioService } from '../service/ons-ear-diario-reservatorio.service';

import { OnsEarDiarioReservatorioDeleteDialogComponent } from './ons-ear-diario-reservatorio-delete-dialog.component';

describe('OnsEarDiarioReservatorio Management Delete Component', () => {
  let comp: OnsEarDiarioReservatorioDeleteDialogComponent;
  let fixture: ComponentFixture<OnsEarDiarioReservatorioDeleteDialogComponent>;
  let service: OnsEarDiarioReservatorioService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsEarDiarioReservatorioDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsEarDiarioReservatorioDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsEarDiarioReservatorioDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsEarDiarioReservatorioService);
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
