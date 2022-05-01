#!/bin/bash

echo "" > temp.txt
echo "####Statistics####" >> temp.txt
date +'%d/%m/%Y %H:%M:%S' >> temp.txt
echo "" >> temp.txt
echo "=============================================CPU=======================================" >> temp.txt
lscpu |awk 'FNR==1 {printf "Architecture: "; print $2}' >> temp.txt
lscpu |awk 'FNR==3 {printf "Byte Order: "; print $3$4}' >> temp.txt
lscpu |awk 'FNR==5 {printf "CPUs: "; print $2}' >> temp.txt
lscpu |awk 'FNR==7 {printf "Threads per core: "; print $4}' >> temp.txt
lscpu |awk 'FNR==8 {printf "Threads per socket: "; print $4}' >> temp.txt
lscpu |awk 'FNR==9 {printf "Sockets: "; print $2}' >> temp.txt
lscpu |awk 'FNR==14 {printf "Model: "; print $3$4$5$6$7$8}' >> temp.txt
lscpu |awk 'FNR==16 {printf "MHz: "; print $3}' >> temp.txt
top -n 1| awk 'FNR == 3{print "CPU%: %"$2+$4+$6+$10;}' >> temp.txt
echo "=========================================PROCESESS=====================================" >> temp.txt
top -b -n 1| awk 'FNR>6 {print $1"\t"$2"\t"$9"\t"$10"\t"$12}' >> temp.txt
echo "=============================================RAM=======================================" >> temp.txt
vmstat -S mb | awk 'FNR==3 {printf "Free RAM: "; printf $4; print "MB";}' >> temp.txt
 vmstat -S mb | awk 'FNR==3 {printf "Cache: "; printf $6; print "MB";}' >> temp.txt
free -m | awk 'FNR==2{printf "Free RAM: "; printf $4;print "MB"}' >> temp.txt
free -m | awk 'FNR==2{printf "Total RAM: "; printf $2;print "MB"}' >> temp.txt
free -m | awk 'FNR==2{printf "Available: "; printf $7;print "MB"}' >> temp.txt
free -m | awk 'FNR==2{printf "Used RAM: "; printf $3;print "MB"}' >> temp.txt
free -m | awk 'FNR==2{printf "Cache: "; printf $6;print "MB"}' >> temp.txt
free -m | awk 'FNR==2{printf "RAM Usage: "; printf ($2/$3); print "%"}' >> temp.txt
echo "============================================DISK======================================" >> temp.txt
sudo lshw -short -C disk | awk 'FNR==4 {printf "Disk Free Space: "; print $4}' >> temp.txt
df | grep -i sda | awk 'FNR==1 {printf "Disk Usage: ";print $5}' >> temp.txt
sudo mkdir -p /home/Estadisticas 
sudo \cp -r temp.txt /home/Estadisticas/stats.txt
rm temp.txt

