jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsCargaEnergiaVerificadaService } from '../service/ons-carga-energia-verificada.service';

import { OnsCargaEnergiaVerificadaDeleteDialogComponent } from './ons-carga-energia-verificada-delete-dialog.component';

describe('OnsCargaEnergiaVerificada Management Delete Component', () => {
  let comp: OnsCargaEnergiaVerificadaDeleteDialogComponent;
  let fixture: ComponentFixture<OnsCargaEnergiaVerificadaDeleteDialogComponent>;
  let service: OnsCargaEnergiaVerificadaService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCargaEnergiaVerificadaDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsCargaEnergiaVerificadaDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsCargaEnergiaVerificadaDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsCargaEnergiaVerificadaService);
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
