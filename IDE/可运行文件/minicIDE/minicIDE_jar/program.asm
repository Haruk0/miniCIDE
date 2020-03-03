.data
.text	0x0008
	j main

delay:
	addi $sp, $sp, -4
	sw $s0, 0($sp)
	addi $t0, $zero, 500
	add $s0, $t0, $zero
	addi $t0, $zero, 1
	sw $t0, 0xfffffc50($zero)
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
	addi $t0, $zero, 65280
	sw $t0, 0xfffffc04($zero)
	addi $t0, $zero, 2020
	sw $t0, 0xfffffc02($zero)
L3:
	addi $t0, $zero, 1
	beq $t0, $zero, L4
	nop
	addi $t0, $zero, 65535
	add $t2, $t0, $zero
	add $t0, $s0, $zero
	add $t1, $t0, $zero
	addi $t0, $zero, 0
	beq $t1, $t2, L6
	nop
	j L7
	nop
L6:
	addi $t0, $zero, 1
L7:
	beq $t0, $zero, L5
	nop
	addi $t0, $zero, 0
	add $s0, $t0, $zero
L5:
	addi $t0, $zero, 1
	add $t1, $t0, $zero
	add $t0, $s0, $zero
	add $t0, $t0, $t1
	add $s0, $t0, $zero
	addi $t0, $zero, 1
	sw $t0, 0xfffffc50($zero)
	add $t0, $s0, $zero
	sw $t0, 0xfffffc00($zero)
	lw $t0, 0xfffffc70($zero)
	add $s1, $t0, $zero
	lw $t0, 0xfffffc72($zero)
	add $s2, $t0, $zero
	add $t0, $s1, $zero
	sw $t0, 0xfffffc60($zero)
	add $t0, $s2, $zero
	sw $t0, 0xfffffc62($zero)
	addi $sp, $sp, -8
	sw $ra, 4($sp)
	sw $t9, 0($sp)
	nop
	jal delay
	nop
	lw $t9, 0($sp)
	lw $ra, 4($sp)
	addi $sp, $sp, 8
	lw $t0, 0xfffffc70($zero)
	add $s1, $t0, $zero
	lw $t0, 0xfffffc72($zero)
	add $s2, $t0, $zero
	add $t0, $s1, $zero
	sw $t0, 0xfffffc60($zero)
	add $t0, $s2, $zero
	sw $t0, 0xfffffc62($zero)
	addi $sp, $sp, -8
	sw $ra, 4($sp)
	sw $t9, 0($sp)
	nop
	jal delay
	nop
	lw $t9, 0($sp)
	lw $ra, 4($sp)
	addi $sp, $sp, 8
	lw $t0, 0xfffffc70($zero)
	add $s1, $t0, $zero
	lw $t0, 0xfffffc72($zero)
	add $s2, $t0, $zero
	add $t0, $s1, $zero
	sw $t0, 0xfffffc60($zero)
	add $t0, $s2, $zero
	sw $t0, 0xfffffc62($zero)
	addi $sp, $sp, -8
	sw $ra, 4($sp)
	sw $t9, 0($sp)
	nop
	jal delay
	nop
	lw $t9, 0($sp)
	lw $ra, 4($sp)
	addi $sp, $sp, 8
	lw $t0, 0xfffffc70($zero)
	add $s1, $t0, $zero
	lw $t0, 0xfffffc72($zero)
	add $s2, $t0, $zero
	add $t0, $s1, $zero
	sw $t0, 0xfffffc60($zero)
	add $t0, $s2, $zero
	sw $t0, 0xfffffc62($zero)
	addi $sp, $sp, -8
	sw $ra, 4($sp)
	sw $t9, 0($sp)
	nop
	jal delay
	nop
	lw $t9, 0($sp)
	lw $ra, 4($sp)
	addi $sp, $sp, 8
	lw $t0, 0xfffffc70($zero)
	add $s1, $t0, $zero
	lw $t0, 0xfffffc72($zero)
	add $s2, $t0, $zero
	add $t0, $s1, $zero
	sw $t0, 0xfffffc60($zero)
	add $t0, $s2, $zero
	sw $t0, 0xfffffc62($zero)
	addi $sp, $sp, -8
	sw $ra, 4($sp)
	sw $t9, 0($sp)
	nop
	jal delay
	nop
	lw $t9, 0($sp)
	lw $ra, 4($sp)
	addi $sp, $sp, 8
	lw $t0, 0xfffffc70($zero)
	add $s1, $t0, $zero
	lw $t0, 0xfffffc72($zero)
	add $s2, $t0, $zero
	add $t0, $s1, $zero
	sw $t0, 0xfffffc60($zero)
	add $t0, $s2, $zero
	sw $t0, 0xfffffc62($zero)
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
