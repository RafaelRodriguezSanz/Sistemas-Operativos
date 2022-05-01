#!/bin/bash
rewrite=false
while getopts u:r flag
do
    case "${flag}" in
        u) username=${OPTARG};;
        r) rewrite=true;;
        *) echo "Not a valid flag: -"${OPTARG};return;
    esac
done
cd "/home";
sudo mkdir -p "./backups/$username";
echo "Scaning...";
files=$(cd "/home" && find ./$username -print | wc -l)
size=$(cd "/home" && du -sh ./$username |awk '{print $1;}')
start=$(date +%s%N)
echo "Backuping...";
if [ "${rewrite}" = true ]; then
    cd "/home" && sudo -S tar --create --auto-compress --verbose --file=./backups/$username/backup.tar.gz ./$username | awk -v progress=0 -v counter=0 -v files=$files '{
    counter=counter+1;
    if(progress<int((counter/files)*50)){
        print "\033[36m" "\033c";
        bar = "PROGRESS: [ "
        for(i=0;i<=progress;i++){
            bar = bar "█";
        }
        for (i=0;i<=50-progress;i++){
            bar = bar "░";
        }
        bar = bar " ]";
        print "\033[36m" bar;
    }
    progress= int((counter/files)*50);
}'
else
    subfix=`date +%N`;
    cd "/home" && sudo -S tar --create --auto-compress --verbose --file=./backups/$username/backup-$subfix.tar.gz ./$username | awk -v progress=0 -v counter=0 -v files=$files '{
    counter=counter+1;
    if(progress<int((counter/files)*50)){
        print "\033[36m" "\033c";
        bar = "PROGRESS: [ "
        for(i=0;i<=progress;i++){
            bar = bar "█";
        }
        for (i=0;i<=50-progress;i++){
            bar = bar "░";
        }
        bar = bar " ]";
        print "\033[36m" bar;
    }
    progress= int((counter/files)*50);
}'
fi
echo "\033[36m" "\033c";
end=$(date +%s%N)
echo "\e[1;36mPROGRESS: [ ██████████████████████████████████████████████████████ ]\e[0m \r"
time=$(((end-start)/1000000000));
echo "Elapsed time: $time sec.";
echo $files " backuped files ($size)"
echo "Backup succesfully created!" 
echo "true"
