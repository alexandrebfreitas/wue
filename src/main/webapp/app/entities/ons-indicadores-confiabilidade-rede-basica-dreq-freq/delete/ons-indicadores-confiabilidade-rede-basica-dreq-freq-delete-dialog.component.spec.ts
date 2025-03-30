jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService } from '../service/ons-indicadores-confiabilidade-rede-basica-dreq-freq.service';

import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDeleteDialogComponent } from './ons-indicadores-confiabilidade-rede-basica-dreq-freq-delete-dialog.component';

describe('OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq Management Delete Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDeleteDialogComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDeleteDialogComponent>;
  let service: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService);
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
