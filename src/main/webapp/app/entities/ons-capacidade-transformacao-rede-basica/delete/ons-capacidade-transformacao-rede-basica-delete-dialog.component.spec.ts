jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsCapacidadeTransformacaoRedeBasicaService } from '../service/ons-capacidade-transformacao-rede-basica.service';

import { OnsCapacidadeTransformacaoRedeBasicaDeleteDialogComponent } from './ons-capacidade-transformacao-rede-basica-delete-dialog.component';

describe('OnsCapacidadeTransformacaoRedeBasica Management Delete Component', () => {
  let comp: OnsCapacidadeTransformacaoRedeBasicaDeleteDialogComponent;
  let fixture: ComponentFixture<OnsCapacidadeTransformacaoRedeBasicaDeleteDialogComponent>;
  let service: OnsCapacidadeTransformacaoRedeBasicaService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsCapacidadeTransformacaoRedeBasicaDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsCapacidadeTransformacaoRedeBasicaDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsCapacidadeTransformacaoRedeBasicaDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsCapacidadeTransformacaoRedeBasicaService);
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
