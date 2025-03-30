import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpHeaders, HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subject, of } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { sampleWithRequiredData } from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.test-samples';
import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService } from '../service/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.service';

import { OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalComponent } from './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.component';
import SpyInstance = jest.SpyInstance;

describe('OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal Management Component', () => {
  let comp: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalComponent;
  let fixture: ComponentFixture<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalComponent>;
  let service: OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService;
  let routerNavigateSpy: SpyInstance<Promise<boolean>>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalComponent],
      providers: [
        provideHttpClient(),
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              }),
            ),
            snapshot: {
              queryParams: {},
              queryParamMap: jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              }),
            },
          },
        },
      ],
    })
      .overrideTemplate(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService);
    routerNavigateSpy = jest.spyOn(comp.router, 'navigate');

    jest
      .spyOn(service, 'query')
      .mockReturnValueOnce(
        of(
          new HttpResponse({
            body: [{ id: 18913 }],
            headers: new HttpHeaders({
              link: '<http://localhost/api/foo?page=1&size=20>; rel="next"',
            }),
          }),
        ),
      )
      .mockReturnValueOnce(
        of(
          new HttpResponse({
            body: [{ id: 10386 }],
            headers: new HttpHeaders({
              link: '<http://localhost/api/foo?page=0&size=20>; rel="prev",<http://localhost/api/foo?page=2&size=20>; rel="next"',
            }),
          }),
        ),
      );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensals()[0]).toEqual(expect.objectContaining({ id: 18913 }));
  });

  describe('trackId', () => {
    it('Should forward to onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService', () => {
      const entity = { id: 18913 };
      jest.spyOn(service, 'getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalIdentifier');
      const id = comp.trackId(entity);
      expect(service.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });

  it('should calculate the sort attribute for a non-id attribute', () => {
    // WHEN
    comp.navigateToWithComponentValues({ predicate: 'non-existing-column', order: 'asc' });

    // THEN
    expect(routerNavigateSpy).toHaveBeenLastCalledWith(
      expect.anything(),
      expect.objectContaining({
        queryParams: expect.objectContaining({
          sort: ['non-existing-column,asc'],
        }),
      }),
    );
  });

  it('should calculate the sort attribute for an id', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenLastCalledWith(expect.objectContaining({ sort: ['id,desc'] }));
  });

  describe('delete', () => {
    let ngbModal: NgbModal;
    let deleteModalMock: any;

    beforeEach(() => {
      deleteModalMock = { componentInstance: {}, closed: new Subject() };
      // NgbModal is not a singleton using TestBed.inject.
      // ngbModal = TestBed.inject(NgbModal);
      ngbModal = (comp as any).modalService;
      jest.spyOn(ngbModal, 'open').mockReturnValue(deleteModalMock);
    });

    it('on confirm should call load', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(comp, 'load');

        // WHEN
        comp.delete(sampleWithRequiredData);
        deleteModalMock.closed.next('deleted');
        tick();

        // THEN
        expect(ngbModal.open).toHaveBeenCalled();
        expect(comp.load).toHaveBeenCalled();
      }),
    ));

    it('on dismiss should call load', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(comp, 'load');

        // WHEN
        comp.delete(sampleWithRequiredData);
        deleteModalMock.closed.next();
        tick();

        // THEN
        expect(ngbModal.open).toHaveBeenCalled();
        expect(comp.load).not.toHaveBeenCalled();
      }),
    ));
  });
});
