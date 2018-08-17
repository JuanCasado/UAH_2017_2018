
DOSSEG
.MODEL SMALL

	INCLUDE BIB.inc

	.STACK 100h
	.DATA

	ASCII 		DB 	"ASCII: ","$"
	scan_code 	DB 	"   ScanCode: ","$"
	estado 		DB	"   Estado: ","$"
	enter		DB	10,13,"$"
	letra		DB 	00h
	cod		DB 	00h
	wor1		DW	0000h
	est		DB	00h
	wor2		DW	0000h

	.CODE

inicio:	

	@iniciarDS
	
	bucle:
	MOV AH,11h
	int 16h
	JZ bucle
	MOV AH,10h
	int 16h

	MOV letra,AL
	MOV cod,AH

	@mostrarcadena ASCII
	@mostrarnumero letra
	@mostrarcadena scan_code
	@byte_to_ASCII cod,wor1
	@show_w_in_ASCII wor1

	MOV AH,12h
	int 16h
	MOV est,AL
	@mostrarcadena estado
	@byte_to_ASCIIX est,wor2
	@show_w_in_ASCII wor2
	@mostrarcadena enter

	MOV BL,letra
	CMP BL,'S'
	JE terminar
	JMP bucle
	terminar:

	@fincodigo

	END