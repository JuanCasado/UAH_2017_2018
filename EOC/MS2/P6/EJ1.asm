
DOSSEG
.MODEL SMALL

	INCLUDE BIB.inc

	.STACK 100h
	.DATA

	PEDIR 		DB 	"ESCRIBE UN CARACTER: ","$"
	caracter	DB 	00h

	.CODE

inicio:	

	@iniciarDS
	
	@mostrarcadena PEDIR
	@pedir_caracter caracter

	@dibujar_d caracter,15h
	
	@pedir_caracter caracter
	@fincodigo

	END