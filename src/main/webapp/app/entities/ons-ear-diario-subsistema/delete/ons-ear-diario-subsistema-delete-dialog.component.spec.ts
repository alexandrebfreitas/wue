jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsEarDiarioSubsistemaService } from '../service/ons-ear-diario-subsistema.service';

import { OnsEarDiarioSubsistemaDeleteDialogComponent } from './ons-ear-diario-subsistema-delete-dialog.component';

describe('OnsEarDiarioSubsistema Management Delete Component', () => {
  let comp: OnsEarDiarioSubsistemaDeleteDialogComponent;
  let fixture: ComponentFixture<OnsEarDiarioSubsistemaDeleteDialogComponent>;
  let service: OnsEarDiarioSubsistemaService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsEarDiarioSubsistemaDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsEarDiarioSubsistemaDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsEarDiarioSubsistemaDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsEarDiarioSubsistemaService);
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
