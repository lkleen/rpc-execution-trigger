#!/bin/bash


WPR_START="xperf -on PROC_THREAD+LOADER+DISK_IO+HARD_FAULTS+CSWITCH+INTERRUPT+DPC+PROFILE+MEMINFO+MEMINFO_WS+PERF_COUNTER+FILE_IO+FILE_IO_INIT+POWER+VAMAP+VIRT_ALLOC+FOOTPRINT+SESSION+REFSET+NETWORKTRACE+ALL_FAULTS+KE_CLOCK+IPI+CLOCKINT+TIMER+CPU_CONFIG+POOL+CONTMEMGEN+MEMORY+CC+DRIVERS+ALPC+KQUEUE+SPINLOCK+PRIORITY+DISPATCHER -stackwalk CSWITCH+PROFILE+HardFault+HeapAlloc+HeapRealloc+HeapCreate+HeapFree+HeapDestroy+ReadyThread+PmcInterrupt+PagefaultTransition+PagefaultDemandZero+PagefaultCopyOnWrite+PagefaultGuard+PagefaultHard+PagefaultAV+PageRemovedFromWorkingset+DpcEnqueue+DpcExecute+ShouldYield+SplitIO+VirtualAlloc+VirtualFree+PageAccess+PageRelease+PageAccessEx+PageRemovedFromWorkingSet+ContiguousMemoryGeneration+AlpcSendMessage+AlpcReceiveMessage+AlpcWaitForReply+AlpcWaitForNewMessage+AlpcUnwait+AlpcConnectRequest+AlpcConnectFail -Buffering -buffersize 1024 -minbuffers 128 -maxbuffers 128"
WPR_DELAY=5
WPR_STOP="xperf -flush -f"
TRACE_NAME="JD_"

# default settings

if [ "x$SSH_SERVER" == "x" ]; then
	SSH_SERVER="10.129.74.115"
fi

if [ "x$SSH_USER" == "x" ]; then
	SSH_USER="qa"
fi

if [ "x$AUDIO_SAMPLE_RATE" == "x" ]; then
        AUDIO_SAMPLE_RATE=44100
fi

if [ "x$AUDIO_INPUT_CARD_ID" == "x" ]; then
        AUDIO_INPUT_CARD_ID=0
fi

if [ "x$AUDIO_OUTPUT_CARD_ID" == "x" ]; then
        AUDIO_OUTPUT_CARD_ID=1
fi

if [ "x$AUDIO_FIRST_CHANNEL" == "x" ]; then
        AUDIO_FIRST_CHANNEL=1
fi

if [ "x$AUDIO_CHANNEL_COUNT" == "x" ]; then
        AUDIO_CHANNEL_COUNT=8
fi


# glitch read loop
echo "connecting to remote machine ssh://$SSH_USER@$SSH_SERVER"
SSH_CMD="AUDIO_SAMPLE_RATE=$AUDIO_SAMPLE_RATE AUDIO_INPUT_CARD_ID=$AUDIO_INPUT_CARD_ID AUDIO_OUTPUT_CARD_ID=$AUDIO_OUTPUT_CARD_ID AUDIO_FIRST_CHANNEL=$AUDIO_FIRST_CHANNEL AUDIO_CHANNEL_COUNT=$AUDIO_CHANNEL_COUNT ./glitch_ssh.sh"
while [ 1 ]; do
	echo "starting WPR..."
	$WPR_START
	echo "connection to glitch test..."
	ssh $SSH_USER@$SSH_SERVER -C $SSH_CMD
	echo
	echo
	echo "Glitch detected, capturing WPR data."
	sleep $WPR_DELAY
	DATE=`date +%Y-%m-%d__%H_%M_%S`
	$WPR_STOP $TRACE_NAME$DATE.etl
	xperf -stop
	echo "glitch recorded... continuing."
	echo
	echo
done
