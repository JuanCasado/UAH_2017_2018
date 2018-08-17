ASSUME CS:codigo, DS:datos, SS:pila

datos	SEGMENT

	cadena DB 11,?,11 dup(?)

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

		XOR CX,CX
		CLD

		MOV AH,0Ah;
		LEA DX,cadena
		int 21h		;Pido cadena
		
		MOV CL,cadena[1];
		MOV SI,CX
		MOV cadena[SI+2],'$'
		MOV SI,2h

		bucle:

		XOR cadena[SI],100000b
		INC SI

		LOOP bucle		
	
		

		LEA DX,cadena[2]	
		MOV AH,09h

		int 21h		;Muestro cadena


		RET
	main 	ENDP
codigo	ENDS

END	main