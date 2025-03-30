jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsDadosHidraulicosReservatorioBaseHorariaService } from '../service/ons-dados-hidraulicos-reservatorio-base-horaria.service';

import { OnsDadosHidraulicosReservatorioBaseHorariaDeleteDialogComponent } from './ons-dados-hidraulicos-reservatorio-base-horaria-delete-dialog.component';

describe('OnsDadosHidraulicosReservatorioBaseHoraria Management Delete Component', () => {
  let comp: OnsDadosHidraulicosReservatorioBaseHorariaDeleteDialogComponent;
  let fixture: ComponentFixture<OnsDadosHidraulicosReservatorioBaseHorariaDeleteDialogComponent>;
  let service: OnsDadosHidraulicosReservatorioBaseHorariaService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosHidraulicosReservatorioBaseHorariaDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsDadosHidraulicosReservatorioBaseHorariaDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsDadosHidraulicosReservatorioBaseHorariaDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsDadosHidraulicosReservatorioBaseHorariaService);
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
