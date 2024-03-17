#!/bin/bash

JAR_FILE=target/*.jar
REMOTE_DIRECTORY=/opt/gamedoora/
REMOTE_JAR_FILE=gamedoora-activity-streams.jar

echo $SSH_USERNAME
echo $SSH_HOST

# connect to the Server instance and set up the remote directory 
 sshpass -p $SSH_PASS ssh -o StrictHostKeyChecking=no "$SSH_USERNAME@$SSH_HOST" "
  if [ -d $REMOTE_DIRECTORY ]; then
    cd $REMOTE_DIRECTORY
    if [ -f $REMOTE_JAR_FILE ]; then
      rm -f $REMOTE_JAR_FILE
    fi
  else
    mkdir -p $REMOTE_DIRECTORY
  fi"

#COPY SYSTEMD INTO LOCAL
DEST_PATH="/usr/lib/systemd/system/"
echo "=====Copying files========="
pwd
ls
sshpass -p $SSH_PASS scp -o StrictHostKeyChecking=no  ./gamedoora-activity-streams.service "$SSH_USERNAME@$SSH_HOST:$DEST_PATH"
echo "==========================="
echo "==Stopping service======="
# Stop the service
sshpass -p $SSH_PASS ssh -o StrictHostKeyChecking=no "$SSH_USERNAME@$SSH_HOST" 'sudo systemctl daemon-reload; sudo systemctl stop gamedoora-activity-streams'
echo "========================="

echo "=====Copying jars========="

 # copy the new JAR file to the remote directory and rename it

sshpass -p $SSH_PASS  scp -v -o StrictHostKeyChecking=no  $JAR_FILE "$SSH_USERNAME@$SSH_HOST:$REMOTE_DIRECTORY/$REMOTE_JAR_FILE"
echo "=========================="
echo "==Starting service======="
sshpass -p $SSH_PASS ssh "$SSH_USERNAME@$SSH_HOST" 'chown -R gamedoora:gamedoora /opt/gamedoora; chmod ug+rwx /opt/gamedoora/*.jar; systemctl restart gamedoora-activity-streams; systemctl is-active --quiet service && echo Service is running'
echo "========================="
# # Check the exit status of the previous command and set an output variable accordingly

if [ $? -eq 0 ]; then
  echo "::set-output name=status::success"
else
  echo "::set-output name=status::failure"
fi
