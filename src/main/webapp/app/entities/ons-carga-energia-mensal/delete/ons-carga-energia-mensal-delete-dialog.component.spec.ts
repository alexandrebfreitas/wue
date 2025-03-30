jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsCargaEnergiaMensalService } from '../service/ons-carga-energia-mensal.service';

import { OnsCargaEnergiaMensalDeleteDialogComponent } from './ons-carga-energia-mensal-delete-dialog.component';

describe('OnsCargaEnergiaMensal Management Delete Component', () => {
  let comp: OnsCargaEnergiaMensalDeleteDialogComponent;
  let fixture: ComponentFixture<OnsCargaEnergiaMensalDeleteDialogComponent>;
  let service: OnsCargaEnergiaMensalService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCargaEnergiaMensalDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsCargaEnergiaMensalDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsCargaEnergiaMensalDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsCargaEnergiaMensalService);
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
