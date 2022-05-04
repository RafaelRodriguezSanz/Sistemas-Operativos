#!/bin/sh

sudo crontab -l > cron_bkp 2>/dev/null
sudo echo "*/1 * * * * sudo $PWD/stats.sh >/dev/null 2>&1" > cron_bkp 2>/dev/null
sudo crontab cron_bkp 2>/dev/null
sudo rm cron_bkp 2>/dev/null

