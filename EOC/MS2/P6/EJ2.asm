
DOSSEG
.MODEL SMALL

	INCLUDE BIB.inc

	.STACK 100h
	.DATA

	esqsupizq	DB	218
	esqsupder	DB	191
	esqinfizq	DB	192
	esqinfder	DB	217
	barravert	DB	179
	barrahori	DB	196
	espacio		DB	20h

	len_h		DB	23	;(0-24)-pos_x
	len_v		DB	10	;(0-79)-pos_y
	pos_x		DB	1
	pos_y		DB	1
	color		DB	46h

	.CODE

inicio:	

	@iniciarDS
	
;INICIO
	MOV AH,00h	
	MOV AL,07h
	int 10h

;ESQUINAS
	MOV BH,0
	MOV CX,1
	MOV BL,color

	MOV DL,pos_x
	MOV DH,pos_y
	MOV AL,esqsupizq
	MOV AH,02h
	int 10h
	MOV AH,09h
	int 10h

	MOV DL,len_h
	MOV DH,pos_y
	MOV AL,esqsupder
	MOV AH,02h
	int 10h
	MOV AH,09h
	int 10h

	MOV DL,pos_x
	MOV DH,len_v
	MOV AL,esqinfizq
	MOV AH,02h
	int 10h
	MOV AH,09h
	int 10h

	MOV DL,len_h
	MOV DH,Len_v
	MOV AL,esqinfder
	MOV AH,02h
	int 10h
	MOV AH,09h
	int 10h

;HORIZONTAL
	MOV CL,len_h
	MOV CH,pos_x
	SUB CL,CH
	dec CL
	XOR CH,CH
	MOV AL,barrahori

	MOV DL,pos_x
	INC DL
	MOV DH,pos_y
	MOV AH,02h
	int 10h
	MOV AH,09h
	int 10h

	MOV DL,pos_x
	INC DL
	MOV DH,Len_v
	MOV AH,02h
	int 10h
	MOV AH,09h
	int 10h
	
;VERTICAL
	
	MOV DH,pos_y
	inc DH
	vertical:

	MOV AL,barravert
	MOV CX,1
	MOV DL,pos_x
	MOV AH,02h
	int 10h
	MOV AH,09h
	int 10h
	INC DL
	MOV CL,len_h
	MOV CH,pos_x
	SUB CL,CH
	dec CL
	XOR CH,CH
	MOV AL,espacio
	MOV AH,02h
	int 10h
	MOV AH,09h
	int 10h
	MOV AL,barravert
	MOV DL,CL
	inc DL
	inc DL
	MOV CL,1
	MOV AH,02h
	int 10h
	MOV AH,09h
	int 10h

	inc DH
	CMP DH,len_v
	JNZ vertical
	

	@pedir_caracter AL
	@fincodigo

	END