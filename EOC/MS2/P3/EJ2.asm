ASSUME CS:codigo, DS:datos, SS:pila

datos	SEGMENT

	salir DB 10,13,"FIN",10,13,"$"
	enter DB 10,13,"$"

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

		XOR AX,AX
		XOR DX,DX
		XOR BX,BX
		xor CX,CX
		
	etiqueta:
		MOV AH,06h
		MOV DL,0FFh
		int 21h
		
		JZ etiqueta

		MOV BL,AL

		MOV DL,AL
		MOV AH,02h
		int 21h

		LEA DX,enter
		MOV AH,09h
		int 21h	

		CMP BL,'F'
		JNE etiqueta
		

		LEA DX,salir
		MOV AH,09h
		int 21h



		RET
	main 	ENDP
codigo	ENDS

END	main