jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsTaxasTeifaETeipService } from '../service/ons-taxas-teifa-e-teip.service';

import { OnsTaxasTeifaETeipDeleteDialogComponent } from './ons-taxas-teifa-e-teip-delete-dialog.component';

describe('OnsTaxasTeifaETeip Management Delete Component', () => {
  let comp: OnsTaxasTeifaETeipDeleteDialogComponent;
  let fixture: ComponentFixture<OnsTaxasTeifaETeipDeleteDialogComponent>;
  let service: OnsTaxasTeifaETeipService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsTaxasTeifaETeipDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsTaxasTeifaETeipDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsTaxasTeifaETeipDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsTaxasTeifaETeipService);
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
