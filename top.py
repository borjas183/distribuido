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

def uso_cpu():
        str = run_command("ps -eo pcpu,user,pmem,pid,cmd | sort -rk 1 | head -10")
        iter = str.split('\n')
        iter.remove(iter[0])
        for it in iter:
            it=re.sub(r'\s+', ' ', it.strip())
            result = it.split(" ")
            print result
            try:
                cpu= result[0]
                user= result[1]
                mem= result[2]
                pid= result[3]
                name= " ".join(result[4:])
                
                
            except:
                pass

def uso_memoria():
        str = run_command("ps -eo pmem,user,pcpu,pid,cmd | sort -rk 1 | head -10")
        iter = str.split('\n')
        iter.remove(iter[0])
        for it in iter:
            it=re.sub(r'\s+', ' ', it.strip())
            result = it.split(" ")
            print result
            try:
                mem= result[0]
                user= result[1]
                cpu= result[2]
                pid= result[3]
                name= " ".join(result[4:])
                
                
            except:
                pass
    
def top():
    while True:
        uso_cpu()
        uso_memoria()
        time.sleep(SECONDS)
        


if __name__ == '__main__':
    top()

