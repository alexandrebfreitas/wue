jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsModalidadeOperacaoUsinasService } from '../service/ons-modalidade-operacao-usinas.service';

import { OnsModalidadeOperacaoUsinasDeleteDialogComponent } from './ons-modalidade-operacao-usinas-delete-dialog.component';

describe('OnsModalidadeOperacaoUsinas Management Delete Component', () => {
  let comp: OnsModalidadeOperacaoUsinasDeleteDialogComponent;
  let fixture: ComponentFixture<OnsModalidadeOperacaoUsinasDeleteDialogComponent>;
  let service: OnsModalidadeOperacaoUsinasService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsModalidadeOperacaoUsinasDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsModalidadeOperacaoUsinasDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsModalidadeOperacaoUsinasDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsModalidadeOperacaoUsinasService);
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
