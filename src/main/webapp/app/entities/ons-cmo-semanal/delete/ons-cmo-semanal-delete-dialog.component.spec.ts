jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsCmoSemanalService } from '../service/ons-cmo-semanal.service';

import { OnsCmoSemanalDeleteDialogComponent } from './ons-cmo-semanal-delete-dialog.component';

describe('OnsCmoSemanal Management Delete Component', () => {
  let comp: OnsCmoSemanalDeleteDialogComponent;
  let fixture: ComponentFixture<OnsCmoSemanalDeleteDialogComponent>;
  let service: OnsCmoSemanalService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCmoSemanalDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsCmoSemanalDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsCmoSemanalDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsCmoSemanalService);
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
