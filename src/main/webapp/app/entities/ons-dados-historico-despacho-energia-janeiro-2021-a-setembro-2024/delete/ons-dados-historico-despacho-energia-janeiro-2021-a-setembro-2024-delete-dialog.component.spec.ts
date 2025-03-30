jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service } from '../service/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.service';

import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DeleteDialogComponent } from './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-delete-dialog.component';

describe('OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 Management Delete Component', () => {
  let comp: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DeleteDialogComponent;
  let fixture: ComponentFixture<OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DeleteDialogComponent>;
  let service: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service);
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
