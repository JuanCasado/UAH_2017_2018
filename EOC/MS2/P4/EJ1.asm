
DOSSEG
.MODEL SMALL

	INCLUDE BIB.inc

	.STACK 100h
	.DATA

mensaje DB "Hola a todos",13,10,'$'

	.CODE

inicio:	

	@iniciarDS
	@mostrarcadena mensaje
	@fincodigo

	END