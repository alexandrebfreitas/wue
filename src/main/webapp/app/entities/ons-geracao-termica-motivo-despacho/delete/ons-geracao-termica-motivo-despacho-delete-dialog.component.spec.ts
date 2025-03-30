jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsGeracaoTermicaMotivoDespachoService } from '../service/ons-geracao-termica-motivo-despacho.service';

import { OnsGeracaoTermicaMotivoDespachoDeleteDialogComponent } from './ons-geracao-termica-motivo-despacho-delete-dialog.component';

describe('OnsGeracaoTermicaMotivoDespacho Management Delete Component', () => {
  let comp: OnsGeracaoTermicaMotivoDespachoDeleteDialogComponent;
  let fixture: ComponentFixture<OnsGeracaoTermicaMotivoDespachoDeleteDialogComponent>;
  let service: OnsGeracaoTermicaMotivoDespachoService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsGeracaoTermicaMotivoDespachoDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsGeracaoTermicaMotivoDespachoDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsGeracaoTermicaMotivoDespachoDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsGeracaoTermicaMotivoDespachoService);
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
