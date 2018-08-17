ASSUME CS:codigo, DS:datos, SS:pila


datos	SEGMENT
	operando1 DB 10h
	operando2 DB 20h
	resultado DW 0000h
datos	ENDS

pila	SEGMENT STACK
	DB 256 DUP(?)
pila	ENDS

codigo	SEGMENT
	inicio:	MOV AX,DATOS
		MOV DS,AX


		xor ax,ax
		mov al,operando1
		add al,operando2
		mov resultado,ax

		MOV AX,4C00h
		INT 21h
codigo	ENDS

END	inicio