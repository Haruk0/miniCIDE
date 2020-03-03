.DATA 0x1000
.TEXT 0X2000
start:
    lui $1,0xFFFF
    ori $28,$1,0xF000
    addi $29,$zero,0xFFF
    addi $8,$zero,4
    addi $9,$zero,8
    addi $10,$zero,0x55AA
    addi $11,$zero,0xAA55
    addi $16,$zero,0xE1 
chkram: 
    lw $24, 0($8)
    sw $9, 0($8)
    lw $12, 0($8)
    bne $12, $9, error
    sw $11, 0($8)
    lw $12,0($8)
    bne $12, $11 ,error
    sw $24, 0($8)
    beq $8, $9, chkkey
    sw $25,0xc50($28)
    addi $8, $8, 4
    j chkram
chkkey:
    addi $16, $zero, 0E2
    lw $8 ,0xc12($28)
    andi $8,$t0,1
    bne $8,$zero,error
    j 0x4
error:
    ori $4,$16,0
    jal 0x2200
lp: 
    sw $25, 0xc50($28)
    j lp
