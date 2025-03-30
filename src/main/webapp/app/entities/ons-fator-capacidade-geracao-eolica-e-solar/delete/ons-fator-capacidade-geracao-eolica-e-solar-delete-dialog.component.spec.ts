jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsFatorCapacidadeGeracaoEolicaESolarService } from '../service/ons-fator-capacidade-geracao-eolica-e-solar.service';

import { OnsFatorCapacidadeGeracaoEolicaESolarDeleteDialogComponent } from './ons-fator-capacidade-geracao-eolica-e-solar-delete-dialog.component';

describe('OnsFatorCapacidadeGeracaoEolicaESolar Management Delete Component', () => {
  let comp: OnsFatorCapacidadeGeracaoEolicaESolarDeleteDialogComponent;
  let fixture: ComponentFixture<OnsFatorCapacidadeGeracaoEolicaESolarDeleteDialogComponent>;
  let service: OnsFatorCapacidadeGeracaoEolicaESolarService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsFatorCapacidadeGeracaoEolicaESolarDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsFatorCapacidadeGeracaoEolicaESolarDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsFatorCapacidadeGeracaoEolicaESolarDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsFatorCapacidadeGeracaoEolicaESolarService);
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
