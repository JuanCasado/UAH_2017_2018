
DOSSEG
.MODEL SMALL

	INCLUDE BIB.inc

	.STACK 100h
	.DATA

	estado 		DB 	00h
	scan_code 	DB 	00h
	Wcode 		DW	0000h

	.CODE

inicio:	

	@iniciarDS
	CLI	

	bucle:

	@hay_datos estado
	CMP estado,01h
	JNE bucle

	@obtener_scan_code scan_code
	@byte_to_ASCII scan_code, Wcode
	@show_w_in_ASCII Wcode

	MOV AL,scan_code
	CMP AL,1Fh
	JNE bucle	

	STI
	@fincodigo

	END