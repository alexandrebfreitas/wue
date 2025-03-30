jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsEnaDiarioReeReservatorioEquivalenteEnergiaService } from '../service/ons-ena-diario-ree-reservatorio-equivalente-energia.service';

import { OnsEnaDiarioReeReservatorioEquivalenteEnergiaDeleteDialogComponent } from './ons-ena-diario-ree-reservatorio-equivalente-energia-delete-dialog.component';

describe('OnsEnaDiarioReeReservatorioEquivalenteEnergia Management Delete Component', () => {
  let comp: OnsEnaDiarioReeReservatorioEquivalenteEnergiaDeleteDialogComponent;
  let fixture: ComponentFixture<OnsEnaDiarioReeReservatorioEquivalenteEnergiaDeleteDialogComponent>;
  let service: OnsEnaDiarioReeReservatorioEquivalenteEnergiaService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsEnaDiarioReeReservatorioEquivalenteEnergiaDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsEnaDiarioReeReservatorioEquivalenteEnergiaDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsEnaDiarioReeReservatorioEquivalenteEnergiaDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsEnaDiarioReeReservatorioEquivalenteEnergiaService);
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
