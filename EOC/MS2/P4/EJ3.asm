
DOSSEG
.MODEL SMALL

	INCLUDE BIB.inc

	.STACK 100h
	.DATA
	
cociente	DB 	00h
resto		DB	00h
operando 	DW	546Ah
codigo_error	DB	10,13,"ERROR",10,13,"$"

	.CODE

inicio:	

	@iniciarDS

	@dividir256 operando,cociente,resto

	@fincodigo

	END