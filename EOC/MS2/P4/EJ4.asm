
DOSSEG
.MODEL SMALL

	INCLUDE BIB.inc

	.STACK 100h
	.DATA
	
dato_inicial	DB	92h
ASCII	 	DW	0000h

	.CODE

inicio:	

	@iniciarDS

	@byte_to_ASCII dato_inicial,ASCII
	@show_w_in_ASCII ASCII

	@fincodigo

	END