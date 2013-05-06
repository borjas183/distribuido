# uso del cpu: 
# ps -eo pcpu,user,pmem,pid,cmd | sort -r | head -10
# uso de la memoria: 
# ps -eo pmem,user,pcpu,pid,cmd | sort -r | head -10
import subprocess
import time
import os
import re


SECONDS=1

def run_command(command):    
    return os.popen(command).read()

def top():
    while True:
        
        str = run_command("ps -eo pcpu,user,pmem,pid,cmd | sort -r | head -10")
        iter = str.split('\n')
        iter.remove(iter[0])
        for it in iter:
            result = it.split(" ")
            try:
                cpu= result[1]
                user= result[2]
                mem= result[5]
                name= result[7]
                
                print ( cpu, user , mem, name )
            except:
                pass
        time.sleep(SECONDS)
        


if __name__ == '__main__':
    top()
