jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsDadosIntercambioEnergiaModalidadeService } from '../service/ons-dados-intercambio-energia-modalidade.service';

import { OnsDadosIntercambioEnergiaModalidadeDeleteDialogComponent } from './ons-dados-intercambio-energia-modalidade-delete-dialog.component';

describe('OnsDadosIntercambioEnergiaModalidade Management Delete Component', () => {
  let comp: OnsDadosIntercambioEnergiaModalidadeDeleteDialogComponent;
  let fixture: ComponentFixture<OnsDadosIntercambioEnergiaModalidadeDeleteDialogComponent>;
  let service: OnsDadosIntercambioEnergiaModalidadeService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosIntercambioEnergiaModalidadeDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsDadosIntercambioEnergiaModalidadeDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsDadosIntercambioEnergiaModalidadeDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsDadosIntercambioEnergiaModalidadeService);
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
