xperf -on PROC_THREAD+LOADER+DISK_IO+HARD_FAULTS+CSWITCH+INTERRUPT+DPC+PROFILE+MEMINFO+MEMINFO_WS+PERF_COUNTER+FILE_IO+FILE_IO_INIT+POWER+VAMAP+VIRT_ALLOC+FOOTPRINT+SESSION+REFSET+NETWORKTRACE+ALL_FAULTS+KE_CLOCK+IPI+CLOCKINT+TIMER+CPU_CONFIG+POOL+CONTMEMGEN+MEMORY+CC+DRIVERS+ALPC+KQUEUE+SPINLOCK+PRIORITY+DISPATCHER -stackwalk CSWITCH -Buffering -buffersize %1 -minbuffers 128 -maxbuffers 128
