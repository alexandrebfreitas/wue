jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsGeracaoComercialParaExportacaoInternacionalService } from '../service/ons-geracao-comercial-para-exportacao-internacional.service';

import { OnsGeracaoComercialParaExportacaoInternacionalDeleteDialogComponent } from './ons-geracao-comercial-para-exportacao-internacional-delete-dialog.component';

describe('OnsGeracaoComercialParaExportacaoInternacional Management Delete Component', () => {
  let comp: OnsGeracaoComercialParaExportacaoInternacionalDeleteDialogComponent;
  let fixture: ComponentFixture<OnsGeracaoComercialParaExportacaoInternacionalDeleteDialogComponent>;
  let service: OnsGeracaoComercialParaExportacaoInternacionalService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsGeracaoComercialParaExportacaoInternacionalDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsGeracaoComercialParaExportacaoInternacionalDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsGeracaoComercialParaExportacaoInternacionalDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsGeracaoComercialParaExportacaoInternacionalService);
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
