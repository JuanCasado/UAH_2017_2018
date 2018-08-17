ASSUME CS:codigo, DS:datos, SS:pila

datos	SEGMENT

	error DB "ERROR",10,13,"$"
	resultado DW 0000h

datos	ENDS

pila	SEGMENT STACK
	DB 256 DUP(?)
pila	ENDS

codigo	SEGMENT
	main	PROC FAR
		PUSH DS
		XOR AX,AX
		PUSH AX
		MOV AX,DATOS
		MOV DS,AX

		CLD
		XOR CX,CX
		etiqueta:	;REINICIO

		CMP CL,0h
		JE primera	;ERROR
		LEA DX,error
		MOV AH,09h
		int 21h

		primera:
		XOR AX,AX
		XOR DX,DX
		XOR BX,BX
		xor CX,CX
		MOV CL,4h

		repetir:	;FOR
		MOV AH,08h	
		int 21h	
		CMP AL,30h
		JL etiqueta
		CMP AL,39h
		JG etiqueta	;#OBTENIDOS
		
		SUB AL,30h	;ASCII->#N
		MOV BL,AL
		MOV AX,10h
		MUL DX
		MOV DX,AX
		ADD DX,BX	;CALCULO
		LOOP repetir
		
		MOV resultado,DX


		RET
	main 	ENDP
codigo	ENDS

END	main