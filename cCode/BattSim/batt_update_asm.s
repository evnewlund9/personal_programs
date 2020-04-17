#b batt_update_asm.s:set_display_from_batt
.text
.global  set_batt_from_ports

set_batt_from_ports:
        movzwl  BATT_VOLTAGE_PORT(%rip), %eax   ##eax is voltage
        testw	  %ax, %ax                        ##Make sure eax is not 0
        js      .ERROR
        cmpl    $0, %eax                        ##Make sure eax is not less than 0
        jl      .ERROR

        movswl	%ax, %edx                       ##edx is also voltage
        movw	  %ax, (%rdi)                     ##Move voltage into first field of the struct

        cmpl	  $3800, %eax
        jge     .VOLTAGE_OVER_100_PERCENT       ##Cases where voltage is vhigher or lower than the battery capacity
        cmpl	  $3000, %eax
        jl      .VOLTAGE_UNDER_0_PERCENT

        subl	  $3000, %edx                     ##Subtract 3000 from voltage and divide by 8
        movl    $8, %esi
        testl	  %edx, %edx
      	cmovns	%edx, %eax
        cqto
        idiv    %esi
        movb	  %al, 2(%rdi)                    ##The quotient is moved into the percent field in the struct

.PERCENTAGE_IS_SET:
        movzwl    BATT_STATUS_PORT(%rip), %ecx
        andl      $0x0001, %ecx                ##Checks only last bit in the status port when determining the mode
        cmpl      $0, %ecx
        jne       .PERCENTAGE_MODE

        movb	   $0, 3(%rdi)                   ##If the mode is not percentage mode, 0 is moved into the mode field of the struct
      	movl	   $0, %eax
      	ret


.VOLTAGE_OVER_100_PERCENT:
        movb    $100,2(%rdi)
        jmp     .PERCENTAGE_IS_SET

.VOLTAGE_UNDER_0_PERCENT:
        movb    $0,2(%rdi)
        jmp     .PERCENTAGE_IS_SET

.PERCENTAGE_MODE:
        movb	  $1, 3(%rdi)
        movl	  $0, %eax
        ret

.ERROR:
        movl    $1, %eax                    ##Returns 1 if unsucessfull
        ret


.data

BLANK:
        .int 0b00000000000000000000000000000000

POINT_MASK:
        .int  0b1000000000000000000000
VOLT_MASK:
        .int  0b10000000000000000000000
PERCENT_MASK:
        .int  0b100000000000000000000000

batt_level_masks:
        .int  0b00000
        .int  0b10000
        .int  0b11000
        .int  0b11100
        .int  0b11110
        .int  0b11111

digit_masks:
        .int  0b0111111
        .int  0b0000011
        .int  0b1101101
        .int  0b1100111
        .int  0b1010011
        .int  0b1110110
        .int  0b1111110
        .int  0b0100011
        .int  0b1111111
        .int  0b1110111

.text
.global  set_display_from_batt

set_display_from_batt:
      movl     BLANK(%rip), %ecx                 #ecx holds the final display bitstring and will be modified throughout set_display_from_batt.

      movl     %edi, %eax
      andl     $0xFFFF, %eax                     #eax is volts.
      sarl     $16, %edi
      movl     %edi, %r8d
      andl     $0xFF, %r8d                       #r8 is percentage.
      sarl     $8, %edi
      movl     %edi, %r9d
      andl     $0xFF, %r9d                       #r9 is mode.

      cmp      $0, %r9d
      je       .VOLTAGE_MODE

      movl      %r8d, %eax

.SET_NUMBERS:
      movl      $10, %r12d
      cqto
      idiv      %r12d
      movl      %edx, %ebx                      #ebx is rightmost num
      cqto
      idiv      %r12d                           #edx (remainder) is middle num and eax (quotient) is leftmost num

      cmpl      $0, %r9d                        #Mode is flipped for use in OR operators
      je        .CHANGE_MODE_TO_ONE
      jne       .CHANGE_MODE_TO_ZERO

.MODE_SWITCHED:
      leaq	    digit_masks(%rip), %r12         #r12 now points to the array of digit masks

      movl      %eax, %r10d
      orl       %r9d, %r10d
      cmpl      $0, %r10d
      jne       .FIRST_NUM_NOT_ZERO             ##Case where the first number is nonzero. This case is not exclusionary as the other two can also be triggered.

      orl       %edx, %r10d                     #r10 is logical OR of first and second digits
      cmpl      $0, %r10d
      jne       .SECOND_NUM_NOT_ZERO

.LEFT_AND_MIDDLE_NUMS_SET:
      movl      (%r12, %rbx, 4), %r11d
      orl       %r11d, %ecx

      movl      POINT_MASK(%rip), %r10d         #r10 is now the mask holding the decimal location
      movl      VOLT_MASK(%rip), %r11d          #r11 is now the mask holding the location of the voltage symbol
      movl      PERCENT_MASK(%rip), %edi        #rdi is now the mask holding the location of the percentage symbol

      cmpl      $1, %r9d
      je        .SET_DECIMAL_AND_V_MARKER

      orl       %edi, %ecx

.SYMBOLS_SET:
      movl     BLANK(%rip), %edi                #edi is now a temporarily empty mask that will become the battery level section of the final bitstring
      leaq	   batt_level_masks(%rip), %r12     #r12 points to start of array containing the battery level masks
      movl     $0, %r10d                        #r10, initially 0, will be set to the number of bars in the battery display

      cmpl     $90, %r8d
      jge      .LEVEL_5_BATTERY
      cmpl     $70, %r8d
      jge      .LEVEL_4_BATTERY
      cmpl     $50, %r8d
      jge      .LEVEL_3_BATTERY
      cmpl     $30, %r8d
      jge      .LEVEL_2_BATTERY
      cmpl     $5, %r8d
      jge      .LEVEL_1_BATTERY

.BATTERY_LEVEL_FOUND:
      movl    (%r12, %r10, 4), %edi
      sall    $24, %edi
      orl     %edi, %ecx
      movl    %ecx, (%rsi)

      movl      $0, %eax
      ret

##End of function. Assembly code below are all jump points for the code above to use.

.CHANGE_MODE_TO_ONE:
      movl      $1, %r9d
      jmp       .MODE_SWITCHED
.CHANGE_MODE_TO_ZERO:
      movl      $0, %r9d
      jmp       .MODE_SWITCHED

.VOLTAGE_MODE:
      movl      $10, %r12d
      cqto
      idiv      %r12d
      cmpl      $5, %edx
      jge       .ROUND_VOLTAGE_UP
      jl        .SET_NUMBERS

.ROUND_VOLTAGE_UP:
    addl      $1, %eax
    jmp       .SET_NUMBERS

.FIRST_NUM_NOT_ZERO:
    movl      (%r12, %rax, 4), %r11d          #r11 is used as a temporary register
    orl       %r11d, %ecx
    sall      $7, %ecx

.SECOND_NUM_NOT_ZERO:
    movl      (%r12, %rdx, 4), %r11d
    orl       %r11d, %ecx
    sall      $7, %ecx
    jmp       .LEFT_AND_MIDDLE_NUMS_SET

.SET_DECIMAL_AND_V_MARKER:
    orl       %r10d, %ecx
    orl       %r11d, %ecx
    jmp       .SYMBOLS_SET

.LEVEL_5_BATTERY:
    movl     $5, %r10d
    jmp      .BATTERY_LEVEL_FOUND
.LEVEL_4_BATTERY:
    movl     $4, %r10d
    jmp      .BATTERY_LEVEL_FOUND
.LEVEL_3_BATTERY:
    movl     $3, %r10d
    jmp      .BATTERY_LEVEL_FOUND
.LEVEL_2_BATTERY:
    movl     $2, %r10d
    jmp      .BATTERY_LEVEL_FOUND
.LEVEL_1_BATTERY:
    movl     $1, %r10d
    jmp      .BATTERY_LEVEL_FOUND


.text
.global batt_update

batt_update:
    pushq    $0                           #32-bit int is large enough for a packed struct
    leaq     0(%rsp), %rdi                #Pass a pointer to the struct in as the first argument

    call	   set_batt_from_ports

    cmpl    $0, %eax                      #If set_batt_from_ports returns 1, the function was unsucessfull in setting the struct fields
    jne     .UNSUCESSFULL

    movq (%rsp), %rdi                     #First argument is, again, a pointer to the struct
    movq %rsp, %rsi                       #Second argument the stack pointer, which will be set to the display bit string

  	call	  set_display_from_batt

    movl    (%rsp), %ecx
    movl    %ecx, BATT_DISPLAY_PORT(%rip)     #After being moved into ecx, the display string is used to update the display port

    popq    %rcx                          #Stack is aligned to a 16-bit boundary.
    movl    $0, %eax                      
    ret

.UNSUCESSFULL:
    popq    %rcx
    movl    $1, %eax
    ret
