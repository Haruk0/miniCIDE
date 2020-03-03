.DATA 0x1400
.TEXT 0X2400 #
start:
    sw $s0, 0($sp)
    addi $sp,$sp,-4
    sw $s1,0($sp)
    addi $sp,$sp,-4
    addi $s0,$zero,1
    beq $a0,$s0,kfun1
klop:
    lw $s1,0xFFFFFC12($zero) #andi $s1,$s1,1
    beq $s1,$zero,klop
    lw $s1,0xFFFFFC10($zero)
    j coded
kfun1:
    lw $s1,0XFFFFFC12($zero) # readkey state  to $s1
    andi $s1,$s1,1
    beq $s1,$zero,nokey
    j readkey
nokey:
    addi $v0,$zero,0FF
    j keyexit
readkey:
    lw $s1,0XFFFFFC10($zero)
coded:
    addi $s0,$zero,1
    bne $s1,$s0,key1
    addi $v0, $zero,1
    j keyexit
key1:
    addi $s0,$zero,2
    bne $s1,$s0,key2
    addi $v0,$zero,2
    j keyexit
key2:
    addi $s0,$zero,3
    bne $s1,$s0,key3
    addi $v0,$zero,3
    j keyexit
key3:
    addi $s0,$zero,4
    bne $s1,$s0,key4
    addi $v0,$zero,0xA
    j keyexit
key4:
    addi $s0,$zero,5
    bne $s1,$s0,key5
    addi $v0,$zero,4
    j keyexit
key5:
    addi $s0,$zero,6
    bne $s1,$s0,key6
    addi $v0,$zero,5
    j keyexit
key6:
    addi $s0,$zero,7
    bne $s1,$s0,key7
    addi $v0,$zero,6
    j keyexit
key7:
    addi $s0,$zero,8
    bne $s1,$s0,key8
    addi $v0,$zero,0xB
    j keyexit
key8:
    addi $s0,$zero,9
    bne $s1,$s0,key9
    addi $v0,$zero,7
    j keyexit
key9:
    addi $s0,$zero,0A
    bne $s1,$s0,key10
    addi $v0,$zero,8
    j keyexit
key10:
    addi $s0,$zero,0B
    bne $s1,$s0,key11
    addi $v0,$zero,9
    j keyexit
key11:
    addi $s0,$zero,0C
    bne $s1,$s0,key12
    addi $v0,$zero,0C
    j keyexit
key12:
    addi $s0,$zero,0D
    bne $s1,$s0,key13
    addi $v0,$zero,0E
    j keyexit
key13:
    addi $s0,$zero,0E
    bne $s1,$s0,key14
    addi $v0,$zero,0
    j keyexit
key14:
    addi $s0,$zero,0F
    bne $s1,$s0,key15
    addi $v0,$zero,0F
    j keyexit
key15:
    addi $s0,$zero,0x10
    bne $s1,$s0,key16
    addi $v0,$zero,0D
    j keyexit
key16:
    addi $v0,$zero,0FF
keyexit:
    addi $sp,$sp,4
    lw $s1,0($sp)
    addi $sp,$sp,1
    lw $s0, 0($sp)
    jr $ra
    