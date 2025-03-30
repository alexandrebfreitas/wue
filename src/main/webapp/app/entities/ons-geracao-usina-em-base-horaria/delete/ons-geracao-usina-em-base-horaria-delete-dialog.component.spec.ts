jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsGeracaoUsinaEmBaseHorariaService } from '../service/ons-geracao-usina-em-base-horaria.service';

import { OnsGeracaoUsinaEmBaseHorariaDeleteDialogComponent } from './ons-geracao-usina-em-base-horaria-delete-dialog.component';

describe('OnsGeracaoUsinaEmBaseHoraria Management Delete Component', () => {
  let comp: OnsGeracaoUsinaEmBaseHorariaDeleteDialogComponent;
  let fixture: ComponentFixture<OnsGeracaoUsinaEmBaseHorariaDeleteDialogComponent>;
  let service: OnsGeracaoUsinaEmBaseHorariaService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsGeracaoUsinaEmBaseHorariaDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsGeracaoUsinaEmBaseHorariaDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsGeracaoUsinaEmBaseHorariaDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsGeracaoUsinaEmBaseHorariaService);
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
