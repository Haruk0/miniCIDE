.data
.text	0x0008
	j main

delay:
	addi $sp, $sp, -4
	sw $s0, 0($sp)
	addi $t0, $zero, 30000
	add $s0, $t0, $zero
L1:
	addi $t0, $zero, 0
	add $t1, $t0, $zero
	add $t0, $s0, $zero
	slt $t0, $t1, $t0
	beq $t0, $zero, L2
	nop
	addi $t0, $zero, 1
	add $t1, $t0, $zero
	add $t0, $s0, $zero
	sub $t0, $t0, $t1
	add $s0, $t0, $zero
	nop
	j L1
	nop
L2:
	lw $s0, 0($sp)
	addi $sp, $sp, 4
	nop
	jr $ra
	nop

main:
	addi $t0, $zero, 0
	add $s0, $t0, $zero
L3:
	addi $t0, $zero, 1
	beq $t0, $zero, L4
	nop
	addi $t0, $zero, 1
	add $t1, $t0, $zero
	add $t0, $s0, $zero
	add $t0, $t0, $t1
	add $s0, $t0, $zero
	add $t0, $s0, $zero
	sw $t0, 0xfffffc60($zero)
	addi $t0, $zero, 16
	add $t1, $t0, $zero
	add $t0, $s0, $zero
	srlv $t0, $t0, $t1
	sw $t0, 0xfffffc62($zero)
	addi $t0, $zero, 10
	add $t1, $t0, $zero
	add $t0, $s0, $zero
	slt $t0, $t1, $t0
	beq $t0, $zero, L5
	nop
	addi $t0, $zero, 0
	add $s0, $t0, $zero
L5:
	addi $sp, $sp, -8
	sw $ra, 4($sp)
	sw $t9, 0($sp)
	nop
	jal delay
	nop
	lw $t9, 0($sp)
	lw $ra, 4($sp)
	addi $sp, $sp, 8
	nop
	j L3
	nop
L4:
	nop
	jr $ra
	nop
