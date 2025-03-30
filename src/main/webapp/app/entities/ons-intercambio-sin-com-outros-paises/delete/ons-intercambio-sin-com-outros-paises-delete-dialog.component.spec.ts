jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsIntercambioSinComOutrosPaisesService } from '../service/ons-intercambio-sin-com-outros-paises.service';

import { OnsIntercambioSinComOutrosPaisesDeleteDialogComponent } from './ons-intercambio-sin-com-outros-paises-delete-dialog.component';

describe('OnsIntercambioSinComOutrosPaises Management Delete Component', () => {
  let comp: OnsIntercambioSinComOutrosPaisesDeleteDialogComponent;
  let fixture: ComponentFixture<OnsIntercambioSinComOutrosPaisesDeleteDialogComponent>;
  let service: OnsIntercambioSinComOutrosPaisesService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIntercambioSinComOutrosPaisesDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsIntercambioSinComOutrosPaisesDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsIntercambioSinComOutrosPaisesDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsIntercambioSinComOutrosPaisesService);
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
