
DOSSEG
.MODEL SMALL

	INCLUDE BIB.inc

	.STACK 100h
	.DATA
	
operando	DB 	00h
resultado 	DW	0000h
codigo_error	DB	10,13,"ERROR",10,13,"$"

	.CODE

inicio:	

	@iniciarDS

	@pedirnumero operando,codigo_error
	@mul10 operando,resultado

	@fincodigo

	END