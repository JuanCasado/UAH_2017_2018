ASSUME CS:codigo, DS:datos, SS:pila

datos	SEGMENT

	resultado DB 00h

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
		MOV CX,2
		bucle:

		MOV AH,08h

		mal:			
		int 21h		

		CMP AL,'F'
		JNG noconvertir
		SUB AL,20h	;A/F <-- a/f
		noconvertir:
		CMP AL,'F'	
		JG mal		;superior
		CMP AL,'0'
		JL mal		;inferior

		CMP AL,'9'	;en medio
		JLE bien
		CMP AL,'A'
		JGE bien
		JMP mal

		bien:		;#correcto

		ADD DL,AL
		MOV AH,02h
		int 21h		;mostrar
		CMP CX,1
		JE nodesplazo
		MOV CL,8h
		SHL DX,CL
		MOV CL,2h
		nodesplazo:		


		LOOP bucle

		MOV resultado,DL

		RET
	main 	ENDP
codigo	ENDS

END	main