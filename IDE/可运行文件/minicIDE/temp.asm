#;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#;                   Minisys-1 测试程序1，测试CPU           ;
#;			  硬件平台：Nexys 4 			     ; 
#;			  该程序用于仿真测试                       ;
#;                                2016.12                                 ;
#;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#;本程序中约定，$28中放端口地址的高20位，            ;
#;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

.data  0000					#数据定义的首地址
	NUM1  	.word 0x00000055          		  
	NUM2  	.word 0x000000AA       			
	NUM3  	.word 0x00000000    			
			      			#代码段定义开始
.text	 0000					#代码开始的首地址
start:lui   $1,0xFFFF				#让$28为0xFFFF0000作为端口地址的高16位
	ori $28,$1,0xF000
k1:	lw $2,NUM1($0)
	lw $3,NUM2($0)
	add $1,$2,$3
	sw $1,NUM3($0)
	subu $4,$3,$2
	slt $4,$2,$1
	and $1,$3,$7
	or $6,$2,$1
	xor $7,$2,$3
	nor $6,$5,$1
	beq $3,$2,-4
gh:	sub $2,$2,$5
	sw   $2, 0xC50($28)				#喂看门狗
	bne $5,$2,gh
	beq $1,$1,ty
	nop
ty:	jal jj
	j mm
jj:	jr $31
mm:	addi $2,$0,0x99
	ori $3,$0,0x77
	sll $3,$2,4
	srl $3,$2,4
	srlv $3,$2,$1
	lui $6,0x9988
	sra $7,$6,4
	addi $2,$0,0
	sw   $2, 0xC50($28)				#喂看门狗
	addi $3,$0,2
	sub  $1,$2,$3
	j k1
.text 0xFFF8	
	jr  $26
.text 0xFFFC	
	jr  $27
