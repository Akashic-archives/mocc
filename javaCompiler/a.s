.globl _start
_start:
	movl	$2, %eax
	movl	%eax, %edi
	movl	$60, %eax
	syscall
