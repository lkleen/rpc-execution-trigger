logman start "NT Kernel Logger" -p "Windows Kernel Trace" (profile,proccntr,dpc,isr,syscall) -bs 1024 -nb 128 128 -mode Circular -o kernel.etl -ct cycle -max 1024 -ets -rt
