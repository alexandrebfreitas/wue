jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsOfertasPrecoParaImportacaoService } from '../service/ons-ofertas-preco-para-importacao.service';

import { OnsOfertasPrecoParaImportacaoDeleteDialogComponent } from './ons-ofertas-preco-para-importacao-delete-dialog.component';

describe('OnsOfertasPrecoParaImportacao Management Delete Component', () => {
  let comp: OnsOfertasPrecoParaImportacaoDeleteDialogComponent;
  let fixture: ComponentFixture<OnsOfertasPrecoParaImportacaoDeleteDialogComponent>;
  let service: OnsOfertasPrecoParaImportacaoService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsOfertasPrecoParaImportacaoDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsOfertasPrecoParaImportacaoDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsOfertasPrecoParaImportacaoDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsOfertasPrecoParaImportacaoService);
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
