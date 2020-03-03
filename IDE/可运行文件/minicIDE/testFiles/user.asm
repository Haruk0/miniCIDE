.DATA 0X10
LEDDATA: .WORD 0x12345678
.TEXT
start: 
     addi $a0,$zero,0x2020
     jal 0x2200
     j start
     