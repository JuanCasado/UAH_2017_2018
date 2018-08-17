
;------------------------------------------------

@hay_datos	MACRO e
		
		IN AL,64h
		AND AL,01h
		MOV e,AL
		XOR AX,AX

		ENDM
;------------------------------------------------

@obtener_scan_code	MACRO sc

		IN AL,60h
		MOV sc,AL
		XOR AX,AX

		ENDM
;------------------------------------------------

@byte_to_ASCII	MACRO b,w
		
		;DIVIDIR W EN DOS BYTES
		XOR AX,AX
		MOV AL,b
		MOV CL,4
		ROR AX,CL
		MOV CL,4
		SHR AH,CL
		
		;CONVERTIR CADA BYTE A ASCII
		
		CMP AH,9h
		JG ah_letra
		;ah_numero
		ADD AH,30h
		JMP fin_ah
		ah_letra:
		SUB AH,0Ah
		ADD AH,41h
		fin_ah:

		CMP AL,9h
		JG al_letra
		;al_numero
		ADD Al,30h
		JMP fin_al
		al_letra:
		SUB AL,0Ah
		ADD AL,41h
		fin_al:

		MOV w,AX
		

		ENDM
;------------------------------------------------

@byte_to_ASCIIX	MACRO b,w
		
		;DIVIDIR W EN DOS BYTES
		XOR AX,AX
		MOV AL,b
		MOV CL,4
		ROR AX,CL
		MOV CL,4
		SHR AH,CL
		
		;CONVERTIR CADA BYTE A ASCII
		
		CMP AH,9h
		JG ah_letraX
		;ah_numero
		ADD AH,30h
		JMP fin_ahX
		ah_letraX:
		SUB AH,0Ah
		ADD AH,41h
		fin_ahX:

		CMP AL,9h
		JG al_letraX
		;al_numero
		ADD AL,30h
		JMP fin_alX
		al_letraX:
		SUB AL,0Ah
		ADD AL,41h
		fin_alX:

		MOV w,AX
		

		ENDM
;------------------------------------------------

@show_w_in_ASCII MACRO word

		XOR BX,BX
		MOV BX,word
		
		XOR AH,AH
		MOV AH,02h
		MOV DL,BL
		int 21h
		MOV DL,BH
		int 21h

		XOR BX,BX
		XOR DX,DX

		ENDM
;------------------------------------------------

@iniciarDS 	MACRO

		MOV AX,@data
		MOV DS,AX

		XOR AX,AX
		XOR CX,CX
		XOR BX,BX

		ENDM
;------------------------------------------------

@fincodigo	MACRO

		MOV AX,4C00h
		int 21h

		ENDM
;------------------------------------------------

@mostrarcadena	MACRO cadena

		MOV AH,09h
		LEA DX,cadena
		int 21h

		ENDM
;------------------------------------------------

@mostrarnumero	MACRO numero
		
		MOV AH,02h
		MOV DL,numero
		int 21h
		
		XOR AX,AX
		XOR DL,DL

		ENDM
;------------------------------------------------

@pedirnumero	MACRO numero,codigo_error
		
		JMP primera
		error:

		MOV AH,09h
		LEA DX,codigo_error
		int 21h

		primera:
		MOV AH,08h
		int 21h
		XOR AH,AH		

		CMP AL,30h
		JL error
		CMP AL,39h
		JG error

		SUB AL,30h
		
		MOV numero,AL

		ENDM
;------------------------------------------------

@mul10		MACRO numero,guarda

		MOV AL,numero
		MOV BL,AL

		MOV CL,3
		SHL AX,CL
		MOV CL,1
		SHL BX,CL

		ADD AX,BX
		MOV guarda,AX

		ENDM
;------------------------------------------------

@dividir256 	MACRO dividendo,c,r
		
		MOV AX,dividendo
		
		MOV CL,8h
		ROR AX,CL
		
		MOV c,AL
		MOV r,AH

		ENDM
;------------------------------------------------

@pedir_caracter MACRO guarda
		
		XOR AX,AX

		MOV AH,08h
		int 21h
		MOV guarda,AL

		XOR AX,AX

		ENDM
;------------------------------------------------

@dibujar_d 	MACRO dato,color

		XOR AX,AX
		XOR CX,CX
		XOR DX,DX
		XOR BX,BX
			
		MOV AH,0Fh
		int 10h
		PUSH AL		

		MOV AH,00h	
		MOV AL,07h
		int 10h
		
		MOV BH,0
		MOV CX,1
		MOV BL,color
		MOV AL,dato
		MOV DL,24

		sig_car:

		MOV AH,02h
		MOV DH,DL
		int 10h

		MOV AH,09h		
		int 10h

		DEC DL
		JNZ sig_car

		POP AL
		MOV AH,00h
		int 10h

		XOR AX,AX
		XOR CX,CX
		XOR DX,DX
		XOR BX,BX

		ENDM
